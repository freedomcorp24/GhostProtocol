#!/bin/bash

# Set the EC2 instance IP
EC2_IP="54.215.16.4"

echo "Deploying authentication system to EC2 instance at $EC2_IP"

# Create necessary directories
ssh ubuntu@$EC2_IP "mkdir -p ~/ghostprotocol/simple_api/static/data ~/ghostprotocol/web/public"

# Copy the authentication system tarball
scp auth_system.tar.gz ubuntu@$EC2_IP:~/ghostprotocol/

# Extract the tarball on the EC2 instance
ssh ubuntu@$EC2_IP "cd ~/ghostprotocol && tar -xzvf auth_system.tar.gz"

# Run the setup_users.py script on the EC2 instance
ssh ubuntu@$EC2_IP "cd ~/ghostprotocol/simple_api && python3 setup_users.py"

# Install required packages
ssh ubuntu@$EC2_IP "pip3 install fastapi uvicorn pyjwt"

# Create a systemd service file for the API server
cat > /tmp/ghostprotocol-api.service << 'EOL'
[Unit]
Description=GhostProtocol API Server
After=network.target

[Service]
User=ubuntu
WorkingDirectory=/home/ubuntu/ghostprotocol/simple_api
ExecStart=/usr/bin/python3 -m uvicorn app:app --host 0.0.0.0 --port 8000
Restart=always
RestartSec=5
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=ghostprotocol-api

[Install]
WantedBy=multi-user.target
EOL

# Copy the systemd service file to the EC2 instance
scp /tmp/ghostprotocol-api.service ubuntu@$EC2_IP:/tmp/

# Install the systemd service
ssh ubuntu@$EC2_IP "sudo mv /tmp/ghostprotocol-api.service /etc/systemd/system/ && sudo systemctl daemon-reload && sudo systemctl enable ghostprotocol-api && sudo systemctl restart ghostprotocol-api"

# Update nginx configuration to serve the login and signup pages
cat > /tmp/ghostprotocol-nginx.conf << 'EOL'
server {
    listen 80;
    server_name _;

    location / {
        root /home/ubuntu/ghostprotocol/web/public;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    location /api/ {
        proxy_pass http://localhost:8000/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    location /admin/ {
        root /home/ubuntu/ghostprotocol/web/public;
        index admin.html;
        try_files $uri $uri/ /admin.html;
    }
}
EOL

# Copy the nginx configuration to the EC2 instance
scp /tmp/ghostprotocol-nginx.conf ubuntu@$EC2_IP:/tmp/

# Install the nginx configuration
ssh ubuntu@$EC2_IP "sudo mv /tmp/ghostprotocol-nginx.conf /etc/nginx/sites-available/ghostprotocol && sudo ln -sf /etc/nginx/sites-available/ghostprotocol /etc/nginx/sites-enabled/ && sudo nginx -t && sudo systemctl restart nginx"

echo "Authentication system deployed successfully!"
echo "Login page: http://$EC2_IP/login.html"
echo "Signup page: http://$EC2_IP/signup.html"
echo "API: http://$EC2_IP/api"
echo "Admin panel: http://$EC2_IP/admin"
