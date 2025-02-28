#!/bin/bash

# Test the API endpoints
echo "Testing API endpoints..."

# Start the API server in the background
cd simple_api
python3 app.py &
API_PID=$!

# Wait for the server to start
sleep 2

# Test the root endpoint
echo "Testing root endpoint..."
curl -s http://localhost:8080/

# Test the signup endpoint
echo -e "\n\nTesting signup endpoint..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpassword"}' http://localhost:8080/api/auth/signup

# Test the login endpoint
echo -e "\n\nTesting login endpoint..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpassword"}' http://localhost:8080/api/auth/login

# Test the current user endpoint
echo -e "\n\nTesting current user endpoint..."
TOKEN=$(curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpassword"}' http://localhost:8080/api/auth/login | jq -r '.token')
curl -s -H "Authorization: Bearer $TOKEN" http://localhost:8080/api/auth/me

# Kill the API server
kill $API_PID

echo -e "\n\nAPI testing completed."
