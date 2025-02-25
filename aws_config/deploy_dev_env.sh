#!/bin/bash
# GhostProtocol AWS Development Environment Deployment Script

echo "Starting GhostProtocol development environment deployment..."

# Set AWS region
AWS_REGION="us-west-1"

# Create S3 buckets if they don't exist
echo "Creating S3 buckets..."
aws s3api create-bucket --bucket ghostprotocol-dev-profiles --region $AWS_REGION --create-bucket-configuration LocationConstraint=$AWS_REGION
aws s3api create-bucket --bucket ghostprotocol-dev-vault --region $AWS_REGION --create-bucket-configuration LocationConstraint=$AWS_REGION
aws s3api create-bucket --bucket ghostprotocol-dev-media --region $AWS_REGION --create-bucket-configuration LocationConstraint=$AWS_REGION
aws s3api create-bucket --bucket ghostprotocol-dev-attachments --region $AWS_REGION --create-bucket-configuration LocationConstraint=$AWS_REGION

# Create DynamoDB tables if they don't exist
echo "Creating DynamoDB tables..."
aws dynamodb create-table \
    --table-name ghostprotocol-dev-accounts \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-devices \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-messages \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-groups \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-crypto-wallets \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-storage-usage \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-admin-roles \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-vault-items \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

aws dynamodb create-table \
    --table-name ghostprotocol-dev-subscriptions \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --region $AWS_REGION

echo "AWS development environment setup complete!"
