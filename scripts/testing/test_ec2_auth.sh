#!/bin/bash

# Set the API URL
API_URL="http://54.215.16.4/api"

# Test the signup endpoint
echo "Testing signup endpoint..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpassword"}' $API_URL/auth/signup

# Test the login endpoint
echo -e "\n\nTesting login endpoint..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpassword"}' $API_URL/auth/login

# Test the admin login
echo -e "\n\nTesting admin login..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"admin","password":"admin123"}' $API_URL/auth/login

echo -e "\n\nAuthentication system testing completed!"
