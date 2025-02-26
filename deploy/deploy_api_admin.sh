#!/bin/bash
set -e

# Set variables
EC2_PUBLIC_IP="54.215.16.4"
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

# Create directories on EC2 instance
echo "Creating directories on EC2 instance..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "mkdir -p /home/ec2-user/ghostprotocol/admin/css /home/ec2-user/ghostprotocol/admin/js /home/ec2-user/ghostprotocol/api"

# Deploy API
echo "Deploying API..."
# Copy API files
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no -r simple_api/* ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/api/

# Create API service file
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo tee /etc/systemd/system/ghostprotocol-api.service > /dev/null" << 'API_SERVICE'
[Unit]
Description=GhostProtocol API Service
After=network.target

[Service]
User=ec2-user
WorkingDirectory=/home/ec2-user/ghostprotocol/api
ExecStart=/usr/bin/python3 app.py
Restart=always

[Install]
WantedBy=multi-user.target
API_SERVICE

# Install required Python packages
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo pip3 install flask flask-cors"

# Enable and start API service
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl daemon-reload && sudo systemctl enable ghostprotocol-api && sudo systemctl restart ghostprotocol-api"

# Deploy Admin Panel
echo "Deploying Admin Panel..."
# Copy admin files
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no deploy/admin/index.html ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/admin/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no deploy/admin/css/admin.css ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/admin/css/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no deploy/admin/js/*.js ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/admin/js/

# Update nginx configuration
echo "Updating nginx configuration..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo tee /etc/nginx/conf.d/ghostprotocol.conf > /dev/null" << 'NGINX_CONF'
server {
    listen 80;
    server_name 54.215.16.4;

    # Admin Panel
    location /admin {
        alias /home/ec2-user/ghostprotocol/admin;
        index index.html;
        try_files $uri $uri/ /admin/index.html;
    }

    # API
    location /api {
        proxy_pass http://localhost:8000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }

    # Web Client
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

# Verify services are running
echo "Verifying services are running..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl status ghostprotocol-api"
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl status nginx"

echo "Deployment completed successfully!"
echo ""
echo "GhostProtocol Access Information"
echo "==============================="
echo "Web Client: http://$EC2_PUBLIC_IP/"
echo "API: http://$EC2_PUBLIC_IP/api"
echo "Admin Panel: http://$EC2_PUBLIC_IP/admin"
