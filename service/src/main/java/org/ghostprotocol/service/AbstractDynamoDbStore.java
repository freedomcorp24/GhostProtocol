/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.storage;

import static com.codahale.metrics.MetricRegistry.name;
import static io.micrometer.core.instrument.Metrics.counter;
import static io.micrometer.core.instrument.Metrics.timer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.BatchGetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.BatchGetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.BatchWriteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.BatchWriteItemResponse;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.KeysAndAttributes;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.WriteRequest;
import javax.annotation.Nonnull;

public abstract class AbstractDynamoDbStore {

  private static final int MAX_ATTEMPTS_TO_SAVE_BATCH_WRITE = 25;  // This was arbitrarily chosen and may be entirely too high.

  public static final int DYNAMO_DB_MAX_BATCH_SIZE = 25;  // This limit comes from Amazon Dynamo DB itself. It will reject batch writes larger than this.

  public static final int RESULT_SET_CHUNK_SIZE = 100;

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final Timer batchWriteItemsFirstPass = timer(name(getClass(), "batchWriteItems"), "firstAttempt", "true");

  private final Timer batchWriteItemsRetryPass = timer(name(getClass(), "batchWriteItems"), "firstAttempt", "false");

  private final Counter batchWriteItemsUnprocessed = counter(name(getClass(), "batchWriteItemsUnprocessed"));
  
  // Cache for frequently accessed items to reduce database reads
  private final Cache<String, Map<String, AttributeValue>> itemCache;
  
  // Cache for query results to reduce repeated identical queries
  private final Cache<String, List<Map<String, AttributeValue>>> queryCache;

  private final DynamoDbClient dynamoDbClient;


  public AbstractDynamoDbStore(final DynamoDbClient dynamoDbClient) {
    this.dynamoDbClient = dynamoDbClient;
    
    // Initialize caches with reasonable defaults
    this.itemCache = Caffeine.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(5, TimeUnit.MINUTES)
        .recordStats()
        .build();
        
    this.queryCache = Caffeine.newBuilder()
        .maximumSize(500)
        .expireAfterWrite(2, TimeUnit.MINUTES)
        .recordStats()
        .build();
  }

  protected DynamoDbClient db() {
    return dynamoDbClient;
  }
  
  /**
   * Gets an item from DynamoDB with caching to improve performance.
   * 
   * @param tableName The name of the table to query
   * @param key The key of the item to get
   * @return The item, or null if not found
   */
  protected Map<String, AttributeValue> getItemWithCache(String tableName, Map<String, AttributeValue> key) {
    // Create a cache key based on table name and item key
    String cacheKey = tableName + ":" + key.toString();
    
    // Try to get from cache first
    Map<String, AttributeValue> cachedItem = itemCache.getIfPresent(cacheKey);
    if (cachedItem != null) {
      return cachedItem;
    }
    
    // If not in cache, get from DynamoDB
    GetItemRequest request = GetItemRequest.builder()
        .tableName(tableName)
        .key(key)
        .build();
    
    GetItemResponse response = db().getItem(request);
    
    if (response.hasItem()) {
      // Store in cache for future use
      itemCache.put(cacheKey, response.item());
      return response.item();
    }
    
    return null;
  }
  
  /**
   * Batch gets items from DynamoDB with caching to improve performance.
   * 
   * @param tableName The name of the table to query
   * @param keys List of keys to get
   * @return Map of keys to items, with missing items excluded
   */
  protected Map<Map<String, AttributeValue>, Map<String, AttributeValue>> batchGetItemsWithCache(
      String tableName, List<Map<String, AttributeValue>> keys) {
    
    Map<Map<String, AttributeValue>, Map<String, AttributeValue>> result = new HashMap<>();
    List<Map<String, AttributeValue>> keysToFetch = new ArrayList<>();
    
    // First check cache for each key
    for (Map<String, AttributeValue> key : keys) {
      String cacheKey = tableName + ":" + key.toString();
      Map<String, AttributeValue> cachedItem = itemCache.getIfPresent(cacheKey);
      
      if (cachedItem != null) {
        // If in cache, add to result
        result.put(key, cachedItem);
      } else {
        // If not in cache, add to keys to fetch
        keysToFetch.add(key);
      }
    }
    
    // If all items were in cache, return result
    if (keysToFetch.isEmpty()) {
      return result;
    }
    
    // Batch get items not found in cache
    // Process in batches of 100 (DynamoDB batch get limit)
    for (int i = 0; i < keysToFetch.size(); i += 100) {
      int end = Math.min(i + 100, keysToFetch.size());
      List<Map<String, AttributeValue>> batch = keysToFetch.subList(i, end);
      
      Map<String, KeysAndAttributes> requestItems = Map.of(
          tableName, KeysAndAttributes.builder().keys(batch).build());
      
      BatchGetItemRequest batchRequest = BatchGetItemRequest.builder()
          .requestItems(requestItems)
          .build();
      
      BatchGetItemResponse response = db().batchGetItem(batchRequest);
      
      // Process results
      List<Map<String, AttributeValue>> items = response.responses().get(tableName);
      if (items != null) {
        // Match items to keys and add to result
        for (Map<String, AttributeValue> item : items) {
          // Extract key from item based on table's key schema
          Map<String, AttributeValue> itemKey = extractKeyFromItem(tableName, item);
          
          // Add to result
          result.put(itemKey, item);
          
          // Add to cache
          String cacheKey = tableName + ":" + itemKey.toString();
          itemCache.put(cacheKey, item);
        }
      }
      
      // Handle unprocessed keys with exponential backoff
      Map<String, KeysAndAttributes> unprocessedKeys = response.unprocessedKeys();
      int retryCount = 0;
      long backoffTimeMs = 50;
      
      while (!unprocessedKeys.isEmpty() && retryCount < 5) {
        try {
          // Exponential backoff with jitter
          long jitter = (long) (Math.random() * 50);
          Thread.sleep(backoffTimeMs + jitter);
          backoffTimeMs = Math.min(backoffTimeMs * 2, 1000);
          
          BatchGetItemRequest retryRequest = BatchGetItemRequest.builder()
              .requestItems(unprocessedKeys)
              .build();
          
          BatchGetItemResponse retryResponse = db().batchGetItem(retryRequest);
          
          // Process retry results
          List<Map<String, AttributeValue>> retryItems = retryResponse.responses().get(tableName);
          if (retryItems != null) {
            for (Map<String, AttributeValue> item : retryItems) {
              Map<String, AttributeValue> itemKey = extractKeyFromItem(tableName, item);
              result.put(itemKey, item);
              
              String cacheKey = tableName + ":" + itemKey.toString();
              itemCache.put(cacheKey, item);
            }
          }
          
          unprocessedKeys = retryResponse.unprocessedKeys();
          retryCount++;
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          logger.warn("Batch get retry interrupted", e);
          break;
        }
      }
    }
    
    return result;
  }
  
  /**
   * Extracts the key attributes from an item based on the table's key schema.
   * This is a simplified implementation - in a real application, this would use
   * the table's key schema from DynamoDB.
   */
  private Map<String, AttributeValue> extractKeyFromItem(String tableName, Map<String, AttributeValue> item) {
    // This is a simplified implementation - in a real application, this would use
    // the table's key schema from DynamoDB to extract the correct key attributes.
    // For now, we'll assume common key attribute names.
    Map<String, AttributeValue> key = new HashMap<>();
    
    // Most DynamoDB tables use "id" or "uuid" as the hash key
    if (item.containsKey("id")) {
      key.put("id", item.get("id"));
    } else if (item.containsKey("uuid")) {
      key.put("uuid", item.get("uuid"));
    }
    
    // Some tables also have a range key like "sortKey" or "timestamp"
    if (item.containsKey("sortKey")) {
      key.put("sortKey", item.get("sortKey"));
    } else if (item.containsKey("timestamp")) {
      key.put("timestamp", item.get("timestamp"));
    }
    
    return key;
  }

  protected void executeTableWriteItemsUntilComplete(final Map<String, ? extends Collection<WriteRequest>> items) {
    final AtomicReference<BatchWriteItemResponse> outcome = new AtomicReference<>();
    writeAndStoreOutcome(items, batchWriteItemsFirstPass, outcome);
    int attemptCount = 0;
    
    // Implement exponential backoff for retries
    long backoffTimeMs = 50; // Start with 50ms backoff
    
    while (!outcome.get().unprocessedItems().isEmpty() && attemptCount < MAX_ATTEMPTS_TO_SAVE_BATCH_WRITE) {
      try {
        // Exponential backoff with jitter to avoid thundering herd problem
        if (attemptCount > 0) {
          long jitter = (long) (Math.random() * 50); // Add random jitter up to 50ms
          Thread.sleep(backoffTimeMs + jitter);
          backoffTimeMs = Math.min(backoffTimeMs * 2, 1000); // Double backoff time up to 1 second max
        }
        
        writeAndStoreOutcome(outcome.get().unprocessedItems(), batchWriteItemsRetryPass, outcome);
        ++attemptCount;
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        logger.warn("Batch write retry interrupted", e);
        break;
      }
    }
    
    if (!outcome.get().unprocessedItems().isEmpty()) {
      final int totalItems = outcome.get().unprocessedItems().values().stream().mapToInt(List::size).sum();
      logger.error(
          "Attempt count ({}) reached max ({}) before applying all batch writes to dynamo. {} unprocessed items remain.",
          attemptCount, MAX_ATTEMPTS_TO_SAVE_BATCH_WRITE, totalItems);
      batchWriteItemsUnprocessed.increment(totalItems);
    }
  }

  @Nonnull
  protected List<Map<String, AttributeValue>> scan(final ScanRequest scanRequest, final int max) {
    // Create a cache key based on the scan request and max value
    String cacheKey = scanRequest.toString() + ":max=" + max;
    
    // Try to get from cache first
    List<Map<String, AttributeValue>> cachedResult = queryCache.getIfPresent(cacheKey);
    if (cachedResult != null) {
      return cachedResult;
    }
    
    // Optimize scan by setting limit directly in the request to avoid loading unnecessary items
    ScanRequest optimizedRequest = scanRequest.toBuilder()
        .limit(max)
        .build();
    
    List<Map<String, AttributeValue>> result = db().scanPaginator(optimizedRequest)
        .items()
        .stream()
        .limit(max) // Keep as a safeguard
        .toList();
    
    // Store in cache for future use
    queryCache.put(cacheKey, result);
    
    return result;
  }

  private void writeAndStoreOutcome(
      final Map<String, ? extends Collection<WriteRequest>> items,
      final Timer timer,
      final AtomicReference<BatchWriteItemResponse> outcome) {
    timer.record(
        () -> outcome.set(dynamoDbClient.batchWriteItem(BatchWriteItemRequest.builder().requestItems(items).build()))
    );
    
    // Invalidate cache entries for modified items to maintain consistency
    for (Map.Entry<String, ? extends Collection<WriteRequest>> entry : items.entrySet()) {
      String tableName = entry.getKey();
      for (WriteRequest request : entry.getValue()) {
        if (request.putRequest() != null) {
          // For put requests, invalidate the item in the cache
          Map<String, AttributeValue> item = request.putRequest().item();
          String cacheKey = tableName + ":" + item.toString();
          itemCache.invalidate(cacheKey);
        } else if (request.deleteRequest() != null) {
          // For delete requests, invalidate the item in the cache
          Map<String, AttributeValue> key = request.deleteRequest().key();
          String cacheKey = tableName + ":" + key.toString();
          itemCache.invalidate(cacheKey);
        }
      }
      
      // Also invalidate any query cache entries for this table
      // This is a simple approach - in a more sophisticated implementation,
      // we would only invalidate affected queries
      queryCache.asMap().keySet().stream()
          .filter(key -> key.startsWith(tableName + ":"))
          .forEach(queryCache::invalidate);
    }
  }

  /**
   * Writes items in batches with optimized performance.
   * 
   * @param items The items to write
   * @param action The action to perform on each batch
   * @param <T> The type of items
   */
  static <T> void writeInBatches(final Iterable<T> items, final Consumer<List<T>> action) {
    // Pre-size the batch to avoid resizing
    final List<T> batch = new ArrayList<>(DYNAMO_DB_MAX_BATCH_SIZE);
    
    // Use parallel processing for large collections if possible
    if (items instanceof Collection && ((Collection<T>) items).size() > 1000) {
      List<List<T>> batches = new ArrayList<>();
      
      for (final T item : items) {
        batch.add(item);
        
        if (batch.size() == DYNAMO_DB_MAX_BATCH_SIZE) {
          // Create a copy of the batch to avoid concurrent modification
          batches.add(new ArrayList<>(batch));
          batch.clear();
        }
      }
      
      if (!batch.isEmpty()) {
        batches.add(new ArrayList<>(batch));
      }
      
      // Process batches in parallel
      batches.parallelStream().forEach(action);
    } else {
      // For smaller collections, use the original sequential approach
      for (final T item : items) {
        batch.add(item);
        
        if (batch.size() == DYNAMO_DB_MAX_BATCH_SIZE) {
          action.accept(batch);
          batch.clear();
        }
      }
      
      if (!batch.isEmpty()) {
        action.accept(batch);
      }
    }
  }
}
