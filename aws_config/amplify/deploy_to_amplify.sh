#!/bin/bash
# GhostProtocol Deployment Script for AWS Amplify

echo "Deploying GhostProtocol to AWS Amplify..."

# Set variables
AWS_REGION="us-west-1"
AMPLIFY_APP_NAME="ghostprotocol-dev"
GITHUB_REPO="https://github.com/freedomcorp24/GhostProtocol"
GITHUB_TOKEN="$1"

if [ -z "$GITHUB_TOKEN" ]; then
  echo "Usage: $0 <github-token>"
  exit 1
fi

# Create Amplify app
echo "Creating Amplify app..."
APP_ID=$(aws amplify create-app \
  --name $AMPLIFY_APP_NAME \
  --repository $GITHUB_REPO \
  --access-token $GITHUB_TOKEN \
  --region $AWS_REGION \
  --query "app.appId" \
  --output text)

echo "Created Amplify app with ID: $APP_ID"

# Create branch
echo "Creating branch..."
aws amplify create-branch \
  --app-id $APP_ID \
  --branch-name main \
  --region $AWS_REGION

# Start job
echo "Starting deployment job..."
aws amplify start-job \
  --app-id $APP_ID \
  --branch-name main \
  --job-type RELEASE \
  --region $AWS_REGION

# Get app URL
APP_URL=$(aws amplify get-app \
  --app-id $APP_ID \
  --region $AWS_REGION \
  --query "app.defaultDomain" \
  --output text)

echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Web Client URL: https://main.$APP_URL"
echo ""
echo "Note: It may take a few minutes for the application to be fully deployed."
