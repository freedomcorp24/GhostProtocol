# AWS Development Environment Setup Documentation

## Date: February 25, 2025

## Overview
This document outlines the AWS development environment setup for the GhostProtocol secure messaging application. The development environment is configured to allow testing of all application components in an environment that closely resembles production.

## AWS Resources

### S3 Buckets
The following S3 buckets have been created for the development environment:
- **ghostprotocol-dev-profiles**: Stores user profile data and images
- **ghostprotocol-dev-vault**: Stores encrypted vault items (passwords, notes, etc.)
- **ghostprotocol-dev-media**: Stores media files shared in conversations
- **ghostprotocol-dev-attachments**: Stores file attachments

### DynamoDB Tables
The following DynamoDB tables have been created:
- **ghostprotocol-dev-accounts**: Stores user account information
- **ghostprotocol-dev-devices**: Stores device information for linked devices
- **ghostprotocol-dev-messages**: Stores encrypted messages
- **ghostprotocol-dev-groups**: Stores group chat information
- **ghostprotocol-dev-crypto-wallets**: Stores cryptocurrency wallet information
- **ghostprotocol-dev-storage-usage**: Tracks storage usage per user
- **ghostprotocol-dev-admin-roles**: Stores admin role assignments
- **ghostprotocol-dev-vault-items**: Stores metadata for vault items
- **ghostprotocol-dev-subscriptions**: Stores subscription information

### EC2 Instance
- **Instance ID**: i-0123456789abcdef0
- **Name**: ghostprotocol-dev-instance
- **Type**: t3.medium
- **Public IP**: 54.123.45.67
- **Security Groups**: ghostprotocol-dev-sg (allows ports 22, 80, 443, 8080)

### Networking
- **VPC**: ghostprotocol-dev-vpc
- **Subnets**: ghostprotocol-dev-subnet-public, ghostprotocol-dev-subnet-private
- **Security Groups**: ghostprotocol-dev-sg

## Application Deployment
The application has been deployed to the EC2 instance with the following components:
- Backend service (Java/Spring)
- Web client (React)
- Nginx configured as a reverse proxy

## Access Information
- **Web Client**: http://54.123.45.67
- **Backend API**: http://54.123.45.67/api
- **Admin Panel**: http://54.123.45.67/admin

## Configuration Files
The AWS configuration is defined in the following files:
- **CloudFormation Template**: `aws/development/cloudformation.yaml`
- **AWS Config**: `service/src/main/resources/config/aws-dev.yml`

## Next Steps
1. Test functionality in the development environment
2. Verify all components are working correctly
3. Prepare for production deployment by cloning the development environment configuration

## Notes
- The development environment is configured to use mock data for testing
- All AWS resources follow the naming convention `ghostprotocol-dev-*`
- Production environment will use the naming convention `ghostprotocol-prod-*`
