#!/bin/bash
set -e

# Set variables
EC2_PUBLIC_IP="54.215.16.4"
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

# Create directories on EC2 instance
echo "Creating directories on EC2 instance..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "mkdir -p /home/ec2-user/ghostprotocol/simple_api/api"

# Copy files to EC2 instance
echo "Copying files to EC2 instance..."
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no app.py ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/simple_api/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no simple-api.service ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/simple_api/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no api/*.py ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/simple_api/api/

# Install service
echo "Installing service..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo cp /home/ec2-user/ghostprotocol/simple_api/simple-api.service /etc/systemd/system/ && sudo systemctl daemon-reload && sudo systemctl enable simple-api && sudo systemctl restart simple-api"

# Verify service is running
echo "Verifying service is running..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl status simple-api"

echo "API deployment completed successfully!"
echo ""
echo "GhostProtocol API Access Information"
echo "===================================="
echo "API Endpoint: http://$EC2_PUBLIC_IP/api"
echo "Health Check: http://$EC2_PUBLIC_IP/api/health"
