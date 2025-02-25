#!/bin/bash

# GhostProtocol Development Environment Deployment Script

echo "Starting GhostProtocol development environment deployment..."

# Check AWS CLI configuration
echo "Checking AWS CLI configuration..."
aws configure list

# Deploy CloudFormation stack
echo "Deploying CloudFormation stack..."
aws cloudformation deploy \
  --template-file aws/development/cloudformation.yaml \
  --stack-name ghostprotocol-dev-stack \
  --capabilities CAPABILITY_IAM \
  --parameter-overrides \
    Environment=dev \
    ResourcePrefix=ghostprotocol-dev

# Start backend service
echo "Starting backend service..."
cd service
mvn exec:java -Dexec.mainClass="org.ghostprotocol.service.GhostProtocolServerService" -Dexec.args="server src/main/resources/config/aws-dev.yml"

echo "Deployment complete!"
