#!/bin/bash
set -e

# Set variables
EC2_PUBLIC_IP="54.215.16.4"
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

# Create directories on EC2 instance
echo "Creating directories on EC2 instance..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "mkdir -p /home/ec2-user/ghostprotocol/web"

# Copy files to EC2 instance
echo "Copying files to EC2 instance..."
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no index.html ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/web/

# Update nginx configuration
echo "Updating nginx configuration..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo tee /etc/nginx/conf.d/web.conf > /dev/null" << 'NGINX_CONF'
server {
    listen 80;
    server_name 54.215.16.4;

    location / {
        root /home/ec2-user/ghostprotocol/web;
        index index.html;
        try_files $uri $uri/ /index.html;
    }
}
NGINX_CONF

# Restart nginx
echo "Restarting nginx..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl restart nginx"

echo "Web client deployment completed successfully!"
echo ""
echo "GhostProtocol Web Client Access Information"
echo "=========================================="
echo "Web Client: http://$EC2_PUBLIC_IP/"
