# AWS Resources Referenced in Code

This document tracks all AWS resources referenced in the GhostProtocol codebase.

## S3 Buckets
- `ghostprotocol-dev-profiles`: User profile data
- `ghostprotocol-dev-vault`: Secure vault storage
- `ghostprotocol-dev-media`: Media content
- `ghostprotocol-dev-attachments`: Message attachments

## DynamoDB Tables
- `ghostprotocol-dev-accounts`: User account information
- `ghostprotocol-dev-devices`: User device information
- `ghostprotocol-dev-messages`: Message storage
- `ghostprotocol-dev-groups`: Group chat information
- `ghostprotocol-dev-crypto-wallets`: Cryptocurrency wallet data
- `ghostprotocol-dev-storage-usage`: Storage usage tracking
- `ghostprotocol-dev-admin-roles`: Admin role assignments
- `ghostprotocol-dev-vault-items`: Vault item storage
- `ghostprotocol-dev-subscriptions`: User subscription data

## SNS Topics
- `ghostprotocol-dev-SNSNotificationTopic-fLfS7KHRT6ja`: Notification topic

## CloudWatch
- Namespace: `GhostProtocol/Development`
- Alarm prefix: `ghostprotocol-dev`

## IAM Roles
- `ghostprotocol-dev-ServiceRole`: Service execution role
- `ghostprotocol-dev-S3AccessRole`: S3 access role
- `ghostprotocol-dev-DynamoDBAccessRole`: DynamoDB access role
