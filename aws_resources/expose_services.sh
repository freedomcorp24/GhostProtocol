#!/bin/bash
# GhostProtocol Service Exposure Script

echo "Exposing GhostProtocol services..."

# Get the EC2 instance ID
INSTANCE_ID=$(aws ec2 describe-instances \
  --filters "Name=tag:Name,Values=ghostprotocol-dev-instance" \
  --query "Reservations[0].Instances[0].InstanceId" \
  --output text)

if [ -z "$INSTANCE_ID" ]; then
  echo "No EC2 instance found. Please run setup_dev_env.sh first."
  exit 1
fi

# Get the public IP address
PUBLIC_IP=$(aws ec2 describe-instances \
  --instance-ids $INSTANCE_ID \
  --query "Reservations[0].Instances[0].PublicIpAddress" \
  --output text)

echo "Found EC2 instance with public IP: $PUBLIC_IP"

echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Web Client: http://$PUBLIC_IP"
echo "Backend API: http://$PUBLIC_IP/api"
echo "Admin Panel: http://$PUBLIC_IP/admin"
echo ""
echo "Please share these URLs with the user for testing."
