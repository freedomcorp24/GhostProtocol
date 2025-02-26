#!/bin/bash

# Deploy the authentication system locally
echo "Deploying authentication system locally..."

# Create the data directory for user storage
mkdir -p ~/GhostProtocol/simple_api/static/data

# Install dependencies
pip3 install fastapi uvicorn pyjwt python-multipart

# Initialize the users data
cd ~/GhostProtocol/simple_api
python3 setup_users.py

# Start the FastAPI server in the background using uvicorn
cd ~/GhostProtocol/simple_api
nohup python3 -m uvicorn app:app --host 0.0.0.0 --port 5000 > api_server.log 2>&1 &
echo $! > api_server.pid

echo "Authentication system deployed successfully!"
echo "API server running on port 5000"
echo "API endpoints:"
echo "- GET  /api/ - API info"
echo "- POST /api/auth/login - Login endpoint"
echo "- POST /api/auth/signup - Signup endpoint"
echo "- GET  /api/auth/me - Get current user info"
