#!/bin/bash

# Set the EC2 instance IP
EC2_IP="54.215.16.4"

echo "Fixing API server on EC2 instance at $EC2_IP"

# Create necessary directories
ssh ubuntu@$EC2_IP "mkdir -p ~/ghostprotocol/simple_api/static/data"

# Copy the fixed API server
scp simple_api/app.py ubuntu@$EC2_IP:~/ghostprotocol/simple_api/

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

echo "API server fixed successfully!"
