#!/bin/bash
# Script to deploy GhostProtocol to AWS development environment

# Set variables
REGION="us-west-1"
STACK_NAME="ghostprotocol-dev"
TEMPLATE_FILE="aws/development/cloudformation.yaml"
EC2_KEY_NAME="ghostprotocol-dev-key"

# Create CloudFormation stack
echo "Creating CloudFormation stack..."
aws cloudformation create-stack \
  --stack-name $STACK_NAME \
  --template-body file://$TEMPLATE_FILE \
  --parameters ParameterKey=KeyName,ParameterValue=$EC2_KEY_NAME \
  --capabilities CAPABILITY_IAM \
  --region $REGION

# Wait for stack creation to complete
echo "Waiting for stack creation to complete..."
aws cloudformation wait stack-create-complete \
  --stack-name $STACK_NAME \
  --region $REGION

# Get EC2 instance public IP
EC2_IP=$(aws cloudformation describe-stacks \
  --stack-name $STACK_NAME \
  --query "Stacks[0].Outputs[?OutputKey=='EC2InstancePublicIP'].OutputValue" \
  --output text \
  --region $REGION)

echo "EC2 instance public IP: $EC2_IP"

# Deploy application to EC2 instance
echo "Deploying application to EC2 instance..."
scp -i ~/.ssh/$EC2_KEY_NAME.pem -r service/target/ghostprotocol-service.jar ec2-user@$EC2_IP:/home/ec2-user/
scp -i ~/.ssh/$EC2_KEY_NAME.pem -r web/build/* ec2-user@$EC2_IP:/home/ec2-user/web/

# Configure and start services
ssh -i ~/.ssh/$EC2_KEY_NAME.pem ec2-user@$EC2_IP << ENDSSH
  sudo systemctl start ghostprotocol-service
  sudo systemctl start nginx
ENDSSH

echo "Deployment complete!"
echo "Web Client: http://$EC2_IP"
echo "Backend API: http://$EC2_IP/api"
echo "Admin Panel: http://$EC2_IP/admin"
