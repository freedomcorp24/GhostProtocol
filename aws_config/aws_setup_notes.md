# AWS Development Environment Setup Notes

## Overview
This document tracks the setup and configuration of the AWS development environment for GhostProtocol.

## AWS Configuration
- Region: us-west-1
- Environment: Development
- Resource Prefix: ghostprotocol-dev

## Resources Created
### S3 Buckets:
- ghostprotocol-dev-profiles: For user profile data
- ghostprotocol-dev-vault: For secure vault storage
- ghostprotocol-dev-media: For media content
- ghostprotocol-dev-attachments: For message attachments

### DynamoDB Tables:
- ghostprotocol-dev-accounts: User account information
- ghostprotocol-dev-devices: User device information
- ghostprotocol-dev-messages: Message storage
- ghostprotocol-dev-groups: Group chat information
- ghostprotocol-dev-crypto-wallets: Cryptocurrency wallet data
- ghostprotocol-dev-storage-usage: Storage usage tracking
- ghostprotocol-dev-admin-roles: Admin role assignments
- ghostprotocol-dev-vault-items: Vault item storage
- ghostprotocol-dev-subscriptions: User subscription data

## Deployment Steps
1. Configure AWS credentials
2. Deploy CloudFormation stack
3. Configure service with AWS resource information
4. Start backend service
5. Start web client
6. Expose services for testing

## Access URLs
- Backend API: TBD
- Web Client: TBD
- Admin Panel: TBD

## Notes
- Development environment will be used for testing before cloning to production
- All AWS resources use consistent naming with ghostprotocol-dev prefix
- CloudFormation template located at aws/development/cloudformation.yaml
