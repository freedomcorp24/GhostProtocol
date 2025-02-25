#!/bin/bash
# GhostProtocol EC2 Instance Creation Script

echo "Creating EC2 instance for GhostProtocol development environment..."

# Set AWS region
AWS_REGION="us-west-1"

# Create a security group
SECURITY_GROUP_ID=$(aws ec2 create-security-group \
  --group-name ghostprotocol-dev-sg \
  --description "Security group for GhostProtocol development environment" \
  --region $AWS_REGION \
  --query 'GroupId' \
  --output text)

echo "Created security group: $SECURITY_GROUP_ID"

# Add inbound rules
aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 22 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 80 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 443 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 8080 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 3000 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

echo "Added inbound rules to security group"

# Create a key pair
KEY_NAME="ghostprotocol-dev-key"
aws ec2 create-key-pair \
  --key-name $KEY_NAME \
  --query 'KeyMaterial' \
  --output text \
  --region $AWS_REGION > ~/.ssh/$KEY_NAME.pem

chmod 400 ~/.ssh/$KEY_NAME.pem
echo "Created key pair: $KEY_NAME"

# Launch an EC2 instance
INSTANCE_ID=$(aws ec2 run-instances \
  --image-id ami-0c55b159cbfafe1f0 \
  --instance-type t2.medium \
  --key-name $KEY_NAME \
  --security-group-ids $SECURITY_GROUP_ID \
  --region $AWS_REGION \
  --tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=ghostprotocol-dev-instance}]' \
  --query 'Instances[0].InstanceId' \
  --output text)

echo "Created EC2 instance: $INSTANCE_ID"

# Wait for the instance to be running
echo "Waiting for instance to be running..."
aws ec2 wait instance-running --instance-ids $INSTANCE_ID --region $AWS_REGION

# Get the public IP address
PUBLIC_IP=$(aws ec2 describe-instances \
  --instance-ids $INSTANCE_ID \
  --region $AWS_REGION \
  --query 'Reservations[0].Instances[0].PublicIpAddress' \
  --output text)

echo "Instance is running with public IP: $PUBLIC_IP"

# Print deployment instructions
echo ""
echo "GhostProtocol Development Environment Setup"
echo "==========================================="
echo "EC2 Instance ID: $INSTANCE_ID"
echo "Public IP: $PUBLIC_IP"
echo "SSH Key: ~/.ssh/$KEY_NAME.pem"
echo ""
echo "To deploy GhostProtocol to this instance, run:"
echo "./aws_config/deploy_to_ec2.sh $PUBLIC_IP ~/.ssh/$KEY_NAME.pem"
echo ""
echo "After deployment, you can access the application at:"
echo "Web Client: http://$PUBLIC_IP"
echo "Backend API: http://$PUBLIC_IP/api"
echo "Admin Panel: http://$PUBLIC_IP/admin"
