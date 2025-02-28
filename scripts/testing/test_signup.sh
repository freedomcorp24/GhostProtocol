#!/bin/bash

# Set the EC2 instance IP
EC2_IP="54.215.16.4"

# Generate a random username
USERNAME="user_$(date +%s)"
PASSWORD="password123"

echo "Testing signup with username: $USERNAME"

# Test signup endpoint
SIGNUP_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" \
  -d "{\"username\":\"$USERNAME\",\"password\":\"$PASSWORD\"}" \
  http://$EC2_IP/api/auth/signup)

echo "Signup response:"
echo $SIGNUP_RESPONSE | python3 -m json.tool

# Extract the token from the signup response
TOKEN=$(echo $SIGNUP_RESPONSE | python3 extract_token.py)

echo -e "\nToken: $TOKEN"

# Verify the token
echo -e "\nToken information:"
echo $TOKEN | python3 verify_admin_token.py

# Test login with the new user
echo -e "\nTesting login with the new user..."
LOGIN_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" \
  -d "{\"username\":\"$USERNAME\",\"password\":\"$PASSWORD\"}" \
  http://$EC2_IP/api/auth/login)

echo "Login response:"
echo $LOGIN_RESPONSE | python3 -m json.tool

# Test the /auth/me endpoint
echo -e "\nTesting /auth/me endpoint..."
ME_RESPONSE=$(curl -s -H "Authorization: Bearer $TOKEN" \
  http://$EC2_IP/api/auth/me)

echo "Me response:"
echo $ME_RESPONSE | python3 -m json.tool
