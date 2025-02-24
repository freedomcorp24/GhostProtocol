# GhostProtocol Turnkey Guide

This comprehensive guide provides step-by-step instructions for setting up and deploying GhostProtocol in both development and production environments.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Development Environment Setup](#development-environment-setup)
3. [Production Environment Setup](#production-environment-setup)
4. [Security Configuration](#security-configuration)
5. [Feature Configuration](#feature-configuration)
6. [Monitoring and Administration](#monitoring-and-administration)
7. [Troubleshooting](#troubleshooting)

## Prerequisites

### System Requirements
- Linux-based operating system (Ubuntu 20.04 LTS or higher recommended)
- Java 17 or higher
- Maven 3.8+
- Node.js 18+
- PostgreSQL 13+
- Redis 6+
- AWS Account with access to required services

### AWS Services Required
- S3 (file storage and backups)
- KMS (encryption key management)
- IAM (service roles)
- CloudFront (content delivery)
- RDS (database hosting)
- ElastiCache (Redis clusters)
- ECS (container orchestration)
- CloudWatch (monitoring)
- Route53 (DNS management)
- ACM (SSL certificates)

### Cryptocurrency Infrastructure
- Bitcoin full node
- Monero full node
- USDT integration support
- HD wallet infrastructure
- Block explorer access
- Exchange rate API access

## Development Environment Setup

1. Clone the repository:
```bash
git clone https://github.com/freedomcorp24/GhostProtocolv1.git
cd GhostProtocolv1
```

2. Install dependencies:
```bash
# Backend
cd service
mvn clean install

# Web Client
cd ../web
npm install

# Mobile Client
cd ../mobile/android
./gradlew build
```

3. Configure environment variables:
```bash
# Create .env file in service directory
cp service/.env.example service/.env

# Required variables
AWS_ACCESS_KEY_ID=your_access_key
AWS_SECRET_ACCESS_KEY=your_secret_key
AWS_REGION=your_region
POSTGRES_URL=jdbc:postgresql://localhost:5432/ghostprotocol
REDIS_URL=redis://localhost:6379
BITCOIN_NODE_URL=http://localhost:8332
MONERO_NODE_URL=http://localhost:18081
```

4. Initialize database:
```bash
# Create database
psql -U postgres -c "CREATE DATABASE ghostprotocol;"

# Run migrations
cd service
mvn flyway:migrate
```

5. Start development servers:
```bash
# Backend
cd service
mvn spring-boot:run

# Web Client
cd ../web
npm start

# Mobile Client (using Android Studio)
open mobile/android in Android Studio
```

## Production Environment Setup

1. Set up AWS infrastructure using CloudFormation:
```bash
cd infrastructure
aws cloudformation deploy --template-file cloudformation.yml --stack-name ghostprotocol-prod
```

2. Configure production environment:

### Database Setup
```bash
# Create RDS instance
aws rds create-db-instance \
    --db-instance-identifier ghostprotocol \
    --db-instance-class db.r5.large \
    --engine postgres \
    --master-username admin \
    --master-user-password <secure-password> \
    --allocated-storage 100

# Create Redis cluster
aws elasticache create-replication-group \
    --replication-group-id ghostprotocol \
    --replication-group-description "GhostProtocol Redis Cluster" \
    --engine redis \
    --cache-node-type cache.r5.large \
    --num-cache-clusters 2
```

### Storage Configuration
```bash
# Create S3 buckets
aws s3 mb s3://ghostprotocol-files
aws s3 mb s3://ghostprotocol-backups
aws s3 mb s3://ghostprotocol-web

# Configure bucket encryption
aws s3api put-bucket-encryption \
    --bucket ghostprotocol-files \
    --server-side-encryption-configuration '{
        "Rules": [{
            "ApplyServerSideEncryptionByDefault": {
                "SSEAlgorithm": "AES256"
            }
        }]
    }'
```

### Security Setup
```bash
# Create KMS keys
aws kms create-key --description "GhostProtocol Master Key"
aws kms create-alias --alias-name alias/ghostprotocol-master --target-key-id <key-id>

# Create IAM roles
aws iam create-role --role-name ghostprotocol-service \
    --assume-role-policy-document file://iam/service-role.json
```

### Deploy Services
```bash
# Backend
cd service
mvn clean package
aws s3 cp target/ghostprotocol.jar s3://ghostprotocol-deploy/

# Web Client
cd ../web
npm run build
aws s3 sync build/ s3://ghostprotocol-web/

# Mobile Client
cd ../mobile/android
./gradlew assembleRelease
```

## Security Configuration

### Encryption Setup
1. Configure master encryption key:
```bash
# Generate master key
aws kms create-key --description "GhostProtocol Master Key"

# Create key alias
aws kms create-alias --alias-name alias/ghostprotocol-master --target-key-id KEY_ID
```

2. Configure secure storage:
```bash
# Set up vault encryption
aws kms create-key --description "GhostProtocol Vault Key"

# Configure secure message storage
aws kms create-key --description "GhostProtocol Message Key"
```

### Authentication Configuration
1. Configure biometric authentication:
- Enable biometric authentication in mobile app
- Configure authentication policies
- Set up pattern/password fallback

2. Set up two-factor authentication:
- Configure pattern authentication
- Set up password authentication
- Enable biometric authentication

3. Configure duress system:
- Set up decoy accounts
- Configure emergency contacts
- Enable duress mode triggers

## Feature Configuration

### Cryptocurrency Integration
1. Configure nodes:
```bash
# Bitcoin node
docker run -d \
    --name bitcoin-node \
    -p 8332:8332 \
    bitcoind

# Monero node
docker run -d \
    --name monero-node \
    -p 18081:18081 \
    monerod
```

2. Configure wallets:
```bash
# Set up HD wallet
aws secretsmanager create-secret \
    --name ghostprotocol/wallet/seed \
    --secret-string <wallet-seed>
```

### Screen Sharing
1. Configure WebRTC:
```bash
# Set up TURN servers
aws ec2 run-instances \
    --image-id ami-xyz \
    --instance-type t3.medium \
    --tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=turn-server}]'
```

### Message Destruction
1. Configure message lifecycle:
```sql
-- Enable message destruction
ALTER TABLE messages ADD COLUMN destruction_time TIMESTAMP;
ALTER TABLE messages ADD COLUMN is_destroyed BOOLEAN DEFAULT FALSE;
```

## Monitoring and Administration

### Monitoring Setup
1. Configure CloudWatch:
```bash
# Create dashboard
aws cloudwatch create-dashboard \
    --dashboard-name GhostProtocol \
    --dashboard-body file://monitoring/dashboard.json

# Set up alarms
aws cloudwatch put-metric-alarm \
    --alarm-name high-cpu \
    --metric-name CPUUtilization \
    --namespace AWS/EC2 \
    --statistic Average \
    --period 300 \
    --threshold 80
```

### Admin Panel
1. Configure admin access:
```sql
-- Create admin role
INSERT INTO roles (name, permissions) 
VALUES ('admin', 'MANAGE_USERS,MANAGE_SYSTEM,VIEW_METRICS');
```

2. Set up monitoring:
```bash
# Configure log aggregation
aws logs create-log-group --log-group-name ghostprotocol

# Set up metric filters
aws logs put-metric-filter \
    --log-group-name ghostprotocol \
    --filter-name errors \
    --filter-pattern "ERROR" \
    --metric-transformations \
        metricName=ErrorCount,metricNamespace=GhostProtocol,metricValue=1
```

## Troubleshooting

### Common Issues

1. Database Connection Issues
```bash
# Check database status
aws rds describe-db-instances --db-instance-identifier ghostprotocol

# Test connection
psql -h <rds-endpoint> -U admin -d ghostprotocol
```

2. AWS Configuration Problems
```bash
# Verify credentials
aws sts get-caller-identity

# Check permissions
aws iam simulate-principal-policy \
    --policy-source-arn <role-arn> \
    --action-names s3:PutObject
```

3. Cryptocurrency Node Sync Issues
```bash
# Check Bitcoin sync status
bitcoin-cli getblockchaininfo

# Check Monero sync status
monero-cli status
```

### Maintenance Procedures

1. Database Maintenance
```sql
-- Regular cleanup
VACUUM ANALYZE;
REINDEX DATABASE ghostprotocol;
```

2. Log Rotation
```bash
# Configure log rotation
aws logs put-retention-policy \
    --log-group-name ghostprotocol \
    --retention-in-days 30
```

3. Backup Procedures
```bash
# Database backup
aws rds create-db-snapshot \
    --db-instance-identifier ghostprotocol \
    --db-snapshot-identifier backup-$(date +%Y%m%d)

# File backup
aws s3 sync s3://ghostprotocol-files s3://ghostprotocol-backups/$(date +%Y%m%d)
```

### Emergency Procedures

1. Service Recovery
```bash
# Restore database
aws rds restore-db-instance-from-db-snapshot \
    --db-instance-identifier ghostprotocol-restored \
    --db-snapshot-identifier backup-20240224

# Restore files
aws s3 sync s3://ghostprotocol-backups/20240224 s3://ghostprotocol-files
```

2. Security Incident Response
```bash
# Rotate encryption keys
aws kms create-key --description "GhostProtocol Master Key - Emergency"
aws kms update-alias --alias-name alias/ghostprotocol-master --target-key-id NEW_KEY_ID

# Block compromised access
aws iam update-role --role-name ghostprotocol-service \
    --policy-document file://iam/emergency-policy.json
```

This guide provides a comprehensive overview of setting up and maintaining GhostProtocol. For specific issues or advanced configurations, please refer to the individual component documentation in the `docs/` directory.
