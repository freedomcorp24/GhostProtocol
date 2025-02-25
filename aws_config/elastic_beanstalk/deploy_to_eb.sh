#!/bin/bash
# GhostProtocol Deployment Script for AWS Elastic Beanstalk

echo "Deploying GhostProtocol to AWS Elastic Beanstalk..."

# Set variables
AWS_REGION="us-west-1"
EB_APP_NAME="ghostprotocol-dev"
EB_ENV_NAME="ghostprotocol-dev-env"

# Create Elastic Beanstalk application
echo "Creating Elastic Beanstalk application..."
aws elasticbeanstalk create-application \
  --application-name $EB_APP_NAME \
  --region $AWS_REGION

# Create application version
echo "Creating application version..."
cd ~/GhostProtocol
mkdir -p .ebextensions

# Create .ebextensions configuration
cat > .ebextensions/01_java.config << 'EOF'
option_settings:
  aws:elasticbeanstalk:container:java:
    JVM Options: -Xms512m -Xmx1024m
  aws:elasticbeanstalk:application:environment:
    SPRING_PROFILES_ACTIVE: dev
    AWS_REGION: us-west-1
EOF

# Create Procfile
cat > Procfile << 'EOF'
web: java -jar service/target/ghostprotocol-server.jar server service/src/main/resources/config/aws-dev.yml
EOF

# Package the application
echo "Packaging the application..."
mvn clean package -DskipTests
zip -r ghostprotocol-app.zip service/target/*.jar .ebextensions Procfile

# Create S3 bucket for application versions
S3_BUCKET="ghostprotocol-dev-deployments"
aws s3 mb s3://$S3_BUCKET --region $AWS_REGION

# Upload application to S3
echo "Uploading application to S3..."
aws s3 cp ghostprotocol-app.zip s3://$S3_BUCKET/

# Create application version
VERSION_LABEL="v1-$(date +%Y%m%d%H%M%S)"
aws elasticbeanstalk create-application-version \
  --application-name $EB_APP_NAME \
  --version-label $VERSION_LABEL \
  --source-bundle S3Bucket=$S3_BUCKET,S3Key=ghostprotocol-app.zip \
  --region $AWS_REGION

# Create environment
echo "Creating Elastic Beanstalk environment..."
aws elasticbeanstalk create-environment \
  --application-name $EB_APP_NAME \
  --environment-name $EB_ENV_NAME \
  --solution-stack-name "64bit Amazon Linux 2 v3.4.9 running Corretto 11" \
  --version-label $VERSION_LABEL \
  --option-settings file://.ebextensions/01_java.config \
  --region $AWS_REGION

# Wait for environment to be ready
echo "Waiting for environment to be ready..."
aws elasticbeanstalk wait environment-updated \
  --environment-name $EB_ENV_NAME \
  --region $AWS_REGION

# Get environment URL
ENV_URL=$(aws elasticbeanstalk describe-environments \
  --environment-names $EB_ENV_NAME \
  --region $AWS_REGION \
  --query "Environments[0].CNAME" \
  --output text)

echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Backend API: http://$ENV_URL/api"
echo "Admin Panel: http://$ENV_URL/admin"
echo ""
echo "Note: It may take a few minutes for the application to be fully deployed."
