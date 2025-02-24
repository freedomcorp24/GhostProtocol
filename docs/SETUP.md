# GhostProtocol Setup Guide

This document provides comprehensive setup instructions for deploying GhostProtocol.

## Prerequisites

- Java 17 or higher
- Maven 3.8+
- DynamoDB
- Redis
- Bitcoin Core node
- Monero node 
- Ethereum node (for USDT)

## Configuration

1. Environment Setup
```bash
# Set required environment variables
export BTC_RPC_USER=your_btc_rpc_user
export BTC_RPC_PASSWORD=your_btc_rpc_password
export XMR_RPC_USER=your_xmr_rpc_user
export XMR_RPC_PASSWORD=your_xmr_rpc_password
export USDT_CONTRACT_ADDRESS=your_usdt_contract_address

# Hot wallet addresses
export BTC_HOT_WALLET=your_btc_hot_wallet
export XMR_HOT_WALLET=your_xmr_hot_wallet
export ETH_HOT_WALLET=your_eth_hot_wallet

# Cold wallet addresses
export BTC_COLD_WALLET=your_btc_cold_wallet
export XMR_COLD_WALLET=your_xmr_cold_wallet
export ETH_COLD_WALLET=your_eth_cold_wallet

2. Database Setup
```bash
# Create DynamoDB tables
aws dynamodb create-table \
  --table-name ghost_protocol_crypto_wallets \
  --attribute-definitions \
    AttributeName=wallet_id,AttributeType=S \
    AttributeName=currency,AttributeType=S \
    AttributeName=address,AttributeType=S \
    AttributeName=account_id,AttributeType=S \
  --key-schema AttributeName=wallet_id,KeyType=HASH \
  --global-secondary-indexes \
    "[{\"IndexName\": \"currency_address_index\",\"KeySchema\":[{\"AttributeName\":\"currency\",\"KeyType\":\"HASH\"},{\"AttributeName\":\"address\",\"KeyType\":\"RANGE\"}],\"Projection\":{\"ProjectionType\":\"ALL\"}},{\"IndexName\": \"account_id_index\",\"KeySchema\":[{\"AttributeName\":\"account_id\",\"KeyType\":\"HASH\"}],\"Projection\":{\"ProjectionType\":\"ALL\"}}]" \
  --billing-mode PAY_PER_REQUEST \
  --stream-specification StreamEnabled=true,StreamViewType=NEW_AND_OLD_IMAGES

aws dynamodb create-table \
  --table-name ghost_protocol_crypto_transactions \
  --attribute-definitions \
    AttributeName=transaction_id,AttributeType=S \
    AttributeName=wallet_id,AttributeType=S \
    AttributeName=status,AttributeType=S \
  --key-schema AttributeName=transaction_id,KeyType=HASH \
  --global-secondary-indexes \
    "[{\"IndexName\": \"wallet_id_index\",\"KeySchema\":[{\"AttributeName\":\"wallet_id\",\"KeyType\":\"HASH\"},{\"AttributeName\":\"status\",\"KeyType\":\"RANGE\"}],\"Projection\":{\"ProjectionType\":\"ALL\"}}]" \
  --billing-mode PAY_PER_REQUEST \
  --stream-specification StreamEnabled=true,StreamViewType=NEW_AND_OLD_IMAGES
```

2. Cryptocurrency Node Setup
```bash
# Bitcoin Core
bitcoin-core -daemon -rpcuser=user -rpcpassword=pass

# Monero
monerod --detach

# Ethereum/USDT
geth --http
```

3. Environment Configuration
```yaml
# config.yml
server:
  applicationConnectors:
    - type: http
      port: 8080

crypto:
  btcNode: "http://localhost:8332"
  xmrNode: "http://localhost:18081"
  ethNode: "http://localhost:8545"
  usdtContract: "0x..."
  
  hotWallet:
    btcAddress: "bc1..."
    xmrAddress: "4..."
    ethAddress: "0x..."
    
  coldWallet:
    btcAddress: "bc1..."
    xmrAddress: "4..."
    ethAddress: "0x..."
```

## Building

```bash
mvn clean package
```

## Running

```bash
java -jar service/target/GhostProtocol-1.0.jar server config.yml
```

## Security Notes

1. Hot/Cold Wallet Setup
- Hot wallet should only hold funds needed for immediate operations
- Cold wallet should be used for long-term storage
- Regular sweeping of hot wallet to cold storage recommended

2. Node Security
- Run nodes on separate machines from main application
- Use strong RPC authentication
- Restrict network access to nodes

3. Payment Processing
- All deposit addresses are one-time use
- Addresses expire after 2 hours if unused
- Transaction verification requires multiple confirmations

## Monitoring

1. Exchange Rates
- Updated every 15 minutes from CoinGecko
- Monitor rate_update_success metric

2. Node Health
- Check node_connection_status metric
- Monitor blockchain sync status

3. Wallet Balance
- Track hot_wallet_balance metric
- Set alerts for low balances

## Troubleshooting

1. Payment Issues
- Check node connectivity
- Verify transaction confirmations
- Ensure address generation is working

2. Rate Update Issues
- Check CoinGecko API access
- Verify rate update frequency
- Check for rate staleness alerts
