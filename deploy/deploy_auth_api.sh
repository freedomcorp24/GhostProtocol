#!/bin/bash

# Deploy the authentication API to the EC2 instance
echo "Deploying authentication API to EC2 instance..."

# Create directory structure
ssh -i ~/.ssh/ghostprotocol.pem ubuntu@54.215.16.4 "mkdir -p /home/ubuntu/ghostprotocol/simple_api/api/v1"

# Copy the API files
scp -i ~/.ssh/ghostprotocol.pem simple_api/app.py ubuntu@54.215.16.4:/home/ubuntu/ghostprotocol/simple_api/
scp -i ~/.ssh/ghostprotocol.pem simple_api/setup_users.py ubuntu@54.215.16.4:/home/ubuntu/ghostprotocol/simple_api/
scp -i ~/.ssh/ghostprotocol.pem -r simple_api/api ubuntu@54.215.16.4:/home/ubuntu/ghostprotocol/simple_api/
scp -i ~/.ssh/ghostprotocol.pem simple_api/api/v1/auth_custom.py ubuntu@54.215.16.4:/home/ubuntu/ghostprotocol/simple_api/api/v1/

# Install dependencies and start the server
ssh -i ~/.ssh/ghostprotocol.pem ubuntu@54.215.16.4 "cd /home/ubuntu/ghostprotocol/simple_api && \
    sudo apt-get update && \
    sudo apt-get install -y python3-pip && \
    pip3 install pyjwt && \
    python3 setup_users.py && \
    sudo mkdir -p /etc/systemd/system && \
    sudo tee /etc/systemd/system/ghostprotocol-api.service > /dev/null << 'SERVICEEOF'
[Unit]
Description=GhostProtocol API Service
After=network.target

[Service]
User=ubuntu
WorkingDirectory=/home/ubuntu/ghostprotocol/simple_api
ExecStart=/usr/bin/python3 app.py
Restart=always
RestartSec=5

[Install]
WantedBy=multi-user.target
SERVICEEOF
    sudo systemctl daemon-reload && \
    sudo systemctl enable ghostprotocol-api && \
    sudo systemctl restart ghostprotocol-api && \
    sudo systemctl status ghostprotocol-api"

echo "Authentication API deployment completed."
