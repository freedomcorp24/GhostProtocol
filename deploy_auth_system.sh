#!/bin/bash

echo "Deploying authentication system to EC2 instance at 54.215.16.4"

# Create necessary directories
ssh ubuntu@54.215.16.4 "mkdir -p ~/ghostprotocol/simple_api/static/data"

# Copy the API server files
scp -r simple_api/app.py ubuntu@54.215.16.4:~/ghostprotocol/simple_api/

# Copy the setup_users.py script
scp simple_api/setup_users.py ubuntu@54.215.16.4:~/ghostprotocol/simple_api/

# Run the setup_users.py script on the EC2 instance
ssh ubuntu@54.215.16.4 "cd ~/ghostprotocol/simple_api && python3 setup_users.py"

# Install required packages
ssh ubuntu@54.215.16.4 "pip3 install fastapi uvicorn pyjwt"

# Restart the API server
ssh ubuntu@54.215.16.4 "sudo systemctl restart ghostprotocol-api"

echo "Authentication system deployed successfully!"
