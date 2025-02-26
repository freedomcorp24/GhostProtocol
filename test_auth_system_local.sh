#!/bin/bash

# Set the API URL
LOCAL_API_URL="http://localhost:8080"

# Start the API server in the background
cd simple_api
python app.py &
API_PID=$!

# Wait for the server to start
sleep 2

# Test the root endpoint
echo "Testing root endpoint..."
curl -s $LOCAL_API_URL

# Test the signup endpoint
echo -e "\n\nTesting signup endpoint..."
SIGNUP_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser3","password":"testpassword"}' $LOCAL_API_URL/api/auth/signup)
echo $SIGNUP_RESPONSE

# Extract the token from the signup response
TOKEN=$(echo $SIGNUP_RESPONSE | grep -o '"token":"[^"]*"' | cut -d'"' -f4)

# Test the login endpoint
echo -e "\n\nTesting login endpoint..."
LOGIN_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser3","password":"testpassword"}' $LOCAL_API_URL/api/auth/login)
echo $LOGIN_RESPONSE

# Extract the token from the login response
TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*"' | cut -d'"' -f4)

# Test the current user endpoint
echo -e "\n\nTesting current user endpoint..."
curl -s -H "Authorization: Bearer $TOKEN" $LOCAL_API_URL/api/auth/me

# Test the admin login
echo -e "\n\nTesting admin login..."
ADMIN_LOGIN_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" -d '{"username":"admin","password":"admin123"}' $LOCAL_API_URL/api/auth/login)
echo $ADMIN_LOGIN_RESPONSE

# Extract the admin token
ADMIN_TOKEN=$(echo $ADMIN_LOGIN_RESPONSE | grep -o '"token":"[^"]*"' | cut -d'"' -f4)

# Test the admin current user endpoint
echo -e "\n\nTesting admin current user endpoint..."
curl -s -H "Authorization: Bearer $ADMIN_TOKEN" $LOCAL_API_URL/api/auth/me

# Kill the API server
kill $API_PID

echo -e "\n\nAuthentication system testing completed!"
