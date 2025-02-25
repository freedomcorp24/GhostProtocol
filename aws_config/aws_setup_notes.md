# AWS Development Environment Setup Documentation

## Overview
This document provides comprehensive details about the AWS development environment setup for GhostProtocol. The environment is designed to be fully functional with all features working, including crypto payments, and will serve as a template for the production environment.

## AWS Configuration
- **Region**: us-west-1
- **Environment**: Development
- **Resource Prefix**: ghostprotocol-dev
- **AWS Account ID**: 982534358587

## Resources Created

### S3 Buckets
| Bucket Name | Purpose | Configuration |
|-------------|---------|---------------|
| ghostprotocol-dev-profiles | Stores user profile data and images | Server-side encryption, versioning enabled |
| ghostprotocol-dev-vault | Secure storage for vault items | Server-side encryption, versioning enabled |
| ghostprotocol-dev-media | Stores media content like images and videos | Server-side encryption, versioning enabled |
| ghostprotocol-dev-attachments | Stores message attachments | Server-side encryption, versioning enabled |

All buckets are configured with:
- Server-side encryption using AES-256
- Versioning enabled for data protection
- CORS configuration to allow cross-origin requests
- Appropriate access policies

### DynamoDB Tables
| Table Name | Primary Key | Purpose | Configuration |
|------------|-------------|---------|---------------|
| ghostprotocol-dev-accounts | id (Hash) | User account information | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-devices | accountId (Hash), deviceId (Range) | User device information | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-messages | accountId (Hash), messageId (Range) | Message storage | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-groups | id (Hash) | Group chat information | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-crypto-wallets | id (Hash) | Cryptocurrency wallet data | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-storage-usage | userId (Hash) | Storage usage tracking | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-admin-roles | id (Hash) | Admin role assignments | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-vault-items | userId (Hash), itemId (Range) | Vault item storage | SSE enabled, PAY_PER_REQUEST billing |
| ghostprotocol-dev-subscriptions | userId (Hash) | User subscription data | SSE enabled, PAY_PER_REQUEST billing |

All tables are configured with:
- Server-side encryption enabled
- PAY_PER_REQUEST billing mode for cost efficiency
- Appropriate attribute definitions and key schema

### IAM Roles
| Role Name | Purpose | Permissions |
|-----------|---------|------------|
| ghostprotocol-dev-GhostProtocolServiceRole-vbd0jO2qmkKQ | Service role for EC2 instances | AmazonS3FullAccess, AmazonDynamoDBFullAccess, CloudWatchFullAccess |

### SNS Topics
| Topic Name | Purpose | Subscriptions |
|------------|---------|--------------|
| GhostProtocolDevAlerts | Notifications for system alerts | CloudWatch alarms |

### CloudWatch Alarms
| Alarm Name | Metric | Threshold | Action |
|------------|--------|-----------|--------|
| ghostprotocol-dev-CPUUtilizationAlarm | CPUUtilization | >80% for 5 minutes | Notify GhostProtocolDevAlerts SNS topic |

## Deployment Plan

### EC2 Instance Setup
- Create EC2 instance with tag "ghostprotocol-dev-instance"
- Configure security group to allow ports 22, 80, 443, 8080, and 3000
- Use Amazon Linux with Java 11, Maven, Node.js, and npm installed
- Create SSH key pair for secure access

### Application Deployment
1. Deploy backend service to EC2 instance
   - Build and deploy Java backend service
   - Configure to use AWS resources

2. Deploy web client to EC2 instance
   - Build and deploy React web client
   - Configure to connect to backend API

3. Deploy admin panel to EC2 instance
   - Build and deploy admin interface
   - Configure access controls

4. Configure nginx as reverse proxy
   - Set up routing for web client, API, and admin panel
   - Configure SSL/TLS for secure communication

### URL Configuration
The following URLs will be configured for access:
- Web Client: http://54.123.45.67/
- Backend API: http://54.123.45.67/api
- Admin Panel: http://54.123.45.67/admin

## Testing Plan
- Verify all URLs are accessible
- Test user registration and authentication
- Test messaging functionality
- Test group chat functionality
- Test crypto payment system
- Test premium features
- Test vault storage
- Test admin functionality

## Production Migration Plan
Once the development environment is fully functional and tested, it will be cloned to create the production environment. This will involve:
1. Creating production resources with "ghostprotocol-prod" prefix
2. Migrating configuration with production-specific settings
3. Deploying application components to production environment
4. Performing final verification tests

## Current Status
- AWS infrastructure resources created
- EC2 instance setup script prepared
- Next step: Create and configure EC2 instance
