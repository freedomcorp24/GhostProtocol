#!/bin/bash

# Set the EC2 instance IP
EC2_IP="54.215.16.4"

echo "Deploying authentication system to EC2 instance at $EC2_IP"

# Create necessary directories
mkdir -p ~/ghostprotocol/simple_api/static/data
mkdir -p ~/ghostprotocol/web/public

# Copy the API server files
cp app.py ~/ghostprotocol/simple_api/
cp setup_users.py ~/ghostprotocol/simple_api/

# Copy the login and signup HTML pages
cp web/public/login.html ~/ghostprotocol/web/public/
cp web/public/signup.html ~/ghostprotocol/web/public/

# Run the setup_users.py script
cd ~/ghostprotocol/simple_api
python3 setup_users.py

# Install required packages
pip3 install fastapi uvicorn pyjwt

# Install the systemd service
sudo cp ghostprotocol-api.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable ghostprotocol-api
sudo systemctl restart ghostprotocol-api

# Install the nginx configuration
sudo cp nginx/ghostprotocol.conf /etc/nginx/sites-available/ghostprotocol
sudo ln -sf /etc/nginx/sites-available/ghostprotocol /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx

echo "Authentication system deployed successfully!"
echo "Login page: http://$EC2_IP/login.html"
echo "Signup page: http://$EC2_IP/signup.html"
echo "API: http://$EC2_IP/api"
echo "Admin panel: http://$EC2_IP/admin"
