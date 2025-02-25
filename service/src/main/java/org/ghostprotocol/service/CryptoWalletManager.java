package org.ghostprotocol.service.storage;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class CryptoWalletManager {
  private static final Logger logger = LoggerFactory.getLogger(CryptoWalletManager.class);

  private final Table walletTable;
  private final Table transactionTable;
  private final String walletTableName = "ghostprotocol-dev-crypto-wallets";
  private final String transactionTableName = "ghostprotocol-dev-crypto-transactions";

  public CryptoWalletManager(DynamoDB db) {
    this.walletTable = db.getTable(walletTableName);
    this.transactionTable = db.getTable(transactionTableName);
  }

  public void create(CryptoWallet wallet) {
    try {
      walletTable.putItem(new PutItemSpec()
          .withItem(new Item()
              .withPrimaryKey("wallet_id", wallet.getWalletId().toString())
              .withString("account_id", wallet.getAccountId().toString())
              .withString("currency", wallet.getCurrency())
              .withString("address", wallet.getAddress())
              .withLong("expires_at", wallet.getExpiresAt().getEpochSecond()))
          .withConditionExpression("attribute_not_exists(wallet_id)"));
    } catch (ConditionalCheckFailedException e) {
      throw new IllegalStateException("Wallet already exists: " + wallet.getWalletId());
    }
  }

  public Optional<CryptoWallet> get(UUID walletId) {
    Item item = walletTable.getItem(new GetItemSpec()
        .withPrimaryKey("wallet_id", walletId.toString()));

    if (item == null) {
      return Optional.empty();
    }

    return Optional.of(itemToWallet(item));
  }

  public Optional<CryptoWallet> getByAddress(String currency, String address) {
    QuerySpec spec = new QuerySpec()
        .withKeyConditionExpression("currency = :currency and address = :address")
        .withValueMap(new ValueMap()
            .withString(":currency", currency)
            .withString(":address", address));

    Iterator<Item> result = walletTable.getIndex("currency_address_index").query(spec).iterator();
    if (!result.hasNext()) {
      return Optional.empty();
    }

    return Optional.of(itemToWallet(result.next()));
  }

  public void delete(UUID walletId) {
    walletTable.deleteItem(new DeleteItemSpec()
        .withPrimaryKey("wallet_id", walletId.toString()));
  }

  public void addTransaction(CryptoTransaction transaction) {
    try {
      transactionTable.putItem(new PutItemSpec()
          .withItem(new Item()
              .withPrimaryKey("transaction_id", transaction.getTransactionId().toString())
              .withString("wallet_id", transaction.getWalletId().toString())
              .withString("amount", transaction.getAmount().toString())
              .withString("status", transaction.getStatus().name())
              .withLong("created_at", transaction.getCreatedAt().getEpochSecond()))
          .withConditionExpression("attribute_not_exists(transaction_id)"));
    } catch (ConditionalCheckFailedException e) {
      throw new IllegalStateException("Transaction already exists: " + transaction.getTransactionId());
    }
  }

  private CryptoWallet itemToWallet(Item item) {
    return new CryptoWallet(
        UUID.fromString(item.getString("wallet_id")),
        UUID.fromString(item.getString("account_id")),
        item.getString("currency"),
        item.getString("address"),
        Instant.ofEpochSecond(item.getLong("expires_at")));
  }
}
