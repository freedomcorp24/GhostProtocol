#!/bin/bash
# GhostProtocol Deployment Script for AWS App Runner

echo "Deploying GhostProtocol to AWS App Runner..."

# Set variables
AWS_REGION="us-west-1"
SERVICE_NAME="ghostprotocol-dev"
ECR_REPO_NAME="ghostprotocol-dev"

# Create ECR repository
echo "Creating ECR repository..."
aws ecr create-repository \
  --repository-name $ECR_REPO_NAME \
  --region $AWS_REGION

# Get ECR repository URI
ECR_REPO_URI=$(aws ecr describe-repositories \
  --repository-names $ECR_REPO_NAME \
  --region $AWS_REGION \
  --query "repositories[0].repositoryUri" \
  --output text)

# Login to ECR
echo "Logging in to ECR..."
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REPO_URI

# Build Docker image
echo "Building Docker image..."
cd ~/GhostProtocol
cat > Dockerfile << 'EOF'
FROM openjdk:11-jre-slim

WORKDIR /app

COPY service/target/ghostprotocol-server.jar /app/
COPY service/src/main/resources/config/aws-dev.yml /app/

EXPOSE 8080

CMD ["java", "-jar", "ghostprotocol-server.jar", "server", "aws-dev.yml"]
EOF

# Build and package the application
echo "Building and packaging the application..."
mvn clean package -DskipTests

# Build Docker image
docker build -t $ECR_REPO_URI:latest .

# Push Docker image to ECR
echo "Pushing Docker image to ECR..."
docker push $ECR_REPO_URI:latest

# Create App Runner service
echo "Creating App Runner service..."
SERVICE_ARN=$(aws apprunner create-service \
  --service-name $SERVICE_NAME \
  --source-configuration "ImageRepository={ImageIdentifier=$ECR_REPO_URI:latest,ImageConfiguration={Port=8080},ImageRepositoryType=ECR}" \
  --region $AWS_REGION \
  --query "Service.ServiceArn" \
  --output text)

echo "Created App Runner service with ARN: $SERVICE_ARN"

# Wait for service to be running
echo "Waiting for service to be running..."
aws apprunner wait service-updated \
  --service-arn $SERVICE_ARN \
  --region $AWS_REGION

# Get service URL
SERVICE_URL=$(aws apprunner describe-service \
  --service-arn $SERVICE_ARN \
  --region $AWS_REGION \
  --query "Service.ServiceUrl" \
  --output text)

echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Backend API: https://$SERVICE_URL/api"
echo "Admin Panel: https://$SERVICE_URL/admin"
echo ""
echo "Note: It may take a few minutes for the application to be fully deployed."
