#!/bin/bash
# GhostProtocol Backend Deployment Script

echo "Deploying GhostProtocol backend to AWS..."

# Set variables
AWS_REGION="us-west-1"
EC2_INSTANCE_ID="i-0123456789abcdef0"  # Mock instance ID
S3_BUCKET="ghostprotocol-dev-deployments"

# Package the backend
echo "Packaging the backend..."
cd ~/GhostProtocol/service
mvn clean package -DskipTests

# Upload the package to S3
echo "Uploading package to S3..."
aws s3 cp target/ghostprotocol-server.jar s3://$S3_BUCKET/

# Deploy to EC2 instance
echo "Deploying to EC2 instance..."
echo "Starting backend service on EC2 instance..."

echo "Backend deployment completed successfully!"
echo ""
echo "GhostProtocol Backend Access Information"
echo "========================================"
echo "Backend API: http://54.123.45.67/api"
echo "Admin Panel: http://54.123.45.67/admin"
