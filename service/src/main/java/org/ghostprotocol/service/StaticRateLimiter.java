/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.ghostprotocol.limits;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.failedFuture;

import com.google.common.annotations.VisibleForTesting;
import io.lettuce.core.RedisException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import java.time.Clock;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletionStage;
import org.ghostprotocol.ghostprotocol.configuration.dynamic.DynamicConfiguration;
import org.ghostprotocol.ghostprotocol.controllers.RateLimitExceededException;
import org.ghostprotocol.ghostprotocol.metrics.MetricsUtil;
import org.ghostprotocol.ghostprotocol.redis.ClusterLuaScript;
import org.ghostprotocol.ghostprotocol.redis.FaultTolerantRedisClusterClient;
import org.ghostprotocol.ghostprotocol.storage.DynamicConfigurationManager;
import org.ghostprotocol.ghostprotocol.util.ExceptionUtils;
import org.ghostprotocol.ghostprotocol.util.Util;

public class StaticRateLimiter implements RateLimiter {

  protected final String name;

  private final RateLimiterConfig config;

  private final Counter counter;
  private final DynamicConfigurationManager<DynamicConfiguration> dynamicConfigurationManager;

  private final ClusterLuaScript validateScript;

  private final FaultTolerantRedisClusterClient cacheCluster;

  private final Clock clock;


  public StaticRateLimiter(
      final String name,
      final RateLimiterConfig config,
      final ClusterLuaScript validateScript,
      final FaultTolerantRedisClusterClient cacheCluster,
      final Clock clock,
      final DynamicConfigurationManager<DynamicConfiguration> dynamicConfigurationManager) {
    this.name = requireNonNull(name);
    this.config = requireNonNull(config);
    this.validateScript = requireNonNull(validateScript);
    this.cacheCluster = requireNonNull(cacheCluster);
    this.clock = requireNonNull(clock);
    this.counter = Metrics.counter(MetricsUtil.name(getClass(), "exceeded"), "rateLimiterName", name);
    this.dynamicConfigurationManager = dynamicConfigurationManager;
  }

  @Override
  public void validate(final String key, final int amount) throws RateLimitExceededException {
    try {
      final long deficitPermitsAmount = executeValidateScript(key, amount, true);
      if (deficitPermitsAmount > 0) {
        counter.increment();
        final Duration retryAfter = Duration.ofMillis(
            (long) Math.ceil((double) deficitPermitsAmount / config.leakRatePerMillis()));
        throw new RateLimitExceededException(retryAfter);
      }
    } catch (RedisException e) {
      if (!failOpen()) {
        throw e;
      }
    }
  }

  @Override
  public CompletionStage<Void> validateAsync(final String key, final int amount) {
    return executeValidateScriptAsync(key, amount, true)
        .thenCompose(deficitPermitsAmount -> {
          if (deficitPermitsAmount == 0) {
            return completedFuture((Void) null);
          }
          counter.increment();
          final Duration retryAfter = Duration.ofMillis(
              (long) Math.ceil((double) deficitPermitsAmount / config.leakRatePerMillis()));
          return failedFuture(new RateLimitExceededException(retryAfter));
        })
        .exceptionally(throwable -> {
          if (ExceptionUtils.unwrap(throwable) instanceof RedisException && failOpen()) {
            return null;
          }
          throw ExceptionUtils.wrap(throwable);
        });
  }

  @Override
  public boolean hasAvailablePermits(final String key, final int amount) {
    try {
      final long deficitPermitsAmount = executeValidateScript(key, amount, false);
      return deficitPermitsAmount == 0;
    } catch (RedisException e) {
      if (failOpen()) {
        return true;
      } else {
        throw e;
      }
    }
  }

  @Override
  public CompletionStage<Boolean> hasAvailablePermitsAsync(final String key, final int amount) {
    return executeValidateScriptAsync(key, amount, false)
        .thenApply(deficitPermitsAmount -> deficitPermitsAmount == 0)
        .exceptionally(throwable -> {
          if (ExceptionUtils.unwrap(throwable) instanceof RedisException && failOpen()) {
            return true;
          }
          throw ExceptionUtils.wrap(throwable);
        });
  }

  @Override
  public void clear(final String key) {
    cacheCluster.useCluster(connection -> connection.sync().del(bucketName(name, key)));
  }

  @Override
  public CompletionStage<Void> clearAsync(final String key) {
    return cacheCluster.withCluster(connection -> connection.async().del(bucketName(name, key)))
        .thenRun(Util.NOOP);
  }

  @Override
  public RateLimiterConfig config() {
    return config;
  }

  private boolean failOpen() {
    return this.dynamicConfigurationManager.getConfiguration().getRateLimitPolicy().failOpen();
  }

  private long executeValidateScript(final String key, final int amount, final boolean applyChanges) {
    final List<String> keys = List.of(bucketName(name, key));
    final List<String> arguments = List.of(
        String.valueOf(config.bucketSize()),
        String.valueOf(config.leakRatePerMillis()),
        String.valueOf(clock.millis()),
        String.valueOf(amount),
        String.valueOf(applyChanges)
    );
    return (Long) validateScript.execute(keys, arguments);
  }

  private CompletionStage<Long> executeValidateScriptAsync(final String key, final int amount, final boolean applyChanges) {
    final List<String> keys = List.of(bucketName(name, key));
    final List<String> arguments = List.of(
        String.valueOf(config.bucketSize()),
        String.valueOf(config.leakRatePerMillis()),
        String.valueOf(clock.millis()),
        String.valueOf(amount),
        String.valueOf(applyChanges)
    );
    return validateScript.executeAsync(keys, arguments).thenApply(o -> (Long) o);
  }

  @VisibleForTesting
  protected static String bucketName(final String name, final String key) {
    return "leaky_bucket::" + name + "::" + key;
  }
}
