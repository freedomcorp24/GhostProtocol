#!/bin/bash
# GhostProtocol Web Client Deployment Script

echo "Deploying GhostProtocol web client to AWS..."

# Set variables
AWS_REGION="us-west-1"
S3_BUCKET="ghostprotocol-dev-web"
CLOUDFRONT_DISTRIBUTION_ID="E123456789ABCDE"  # Mock distribution ID

# Build the web client
echo "Building the web client..."
cd ~/GhostProtocol/web
npm install
npm run build

# Upload to S3
echo "Uploading to S3..."
aws s3 sync build/ s3://$S3_BUCKET/ --delete

# Invalidate CloudFront cache
echo "Invalidating CloudFront cache..."
aws cloudfront create-invalidation --distribution-id $CLOUDFRONT_DISTRIBUTION_ID --paths "/*"

echo "Web client deployment completed successfully!"
echo ""
echo "GhostProtocol Web Client Access Information"
echo "=========================================="
echo "Web Client: http://54.123.45.67"
