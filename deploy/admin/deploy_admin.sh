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

# Update nginx configuration
echo "Updating nginx configuration..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo tee /etc/nginx/conf.d/admin.conf > /dev/null" << 'NGINX_CONF'
server {
    listen 80;
    server_name 54.215.16.4;

    location /admin {
        alias /home/ec2-user/ghostprotocol/admin;
        index index.html;
        try_files $uri $uri/ /admin/index.html;
    }
}
NGINX_CONF

# Restart nginx
echo "Restarting nginx..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl restart nginx"

echo "Admin panel deployment completed successfully!"
echo ""
echo "GhostProtocol Admin Panel Access Information"
echo "==========================================="
echo "Admin Panel: http://$EC2_PUBLIC_IP/admin"
