#!/bin/bash
set -e

# Set variables
EC2_PUBLIC_IP="54.215.16.4"
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

# Create directories on EC2 instance
echo "Creating directories on EC2 instance..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "mkdir -p /home/ec2-user/ghostprotocol/admin/css /home/ec2-user/ghostprotocol/admin/js"

# Copy files to EC2 instance
echo "Copying files to EC2 instance..."
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no index.html ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/admin/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no css/admin.css ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/admin/css/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no js/*.js ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/admin/js/

# Update nginx configuration if needed
echo "Updating nginx configuration..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo sed -i 's|/home/ec2-user/ghostprotocol/admin_panel|/home/ec2-user/ghostprotocol/admin|g' /etc/nginx/conf.d/ghostprotocol.conf"

# Restart nginx
echo "Restarting nginx..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl restart nginx"

echo "Admin panel deployment completed successfully!"
echo ""
echo "GhostProtocol Admin Panel Access Information"
echo "==========================================="
echo "Admin Panel: http://$EC2_PUBLIC_IP/admin"
