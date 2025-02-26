#!/bin/bash

# Set the EC2 instance IP
EC2_IP="54.215.16.4"

echo "Verifying signup functionality..."

# Test the signup endpoint
echo "Testing signup endpoint..."
SIGNUP_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" \
  -d '{"username":"testuser3","password":"testpass3"}' \
  http://$EC2_IP/api/auth/signup)

echo "Signup response:"
echo $SIGNUP_RESPONSE | python3 -m json.tool

# Test the signup endpoint with a duplicate username
echo -e "\n\nTesting signup endpoint with duplicate username..."
DUPLICATE_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" \
  -d '{"username":"testuser3","password":"testpass3"}' \
  http://$EC2_IP/api/auth/signup)

echo "Duplicate username response:"
echo $DUPLICATE_RESPONSE | python3 -m json.tool

# Test the signup endpoint with missing fields
echo -e "\n\nTesting signup endpoint with missing fields..."
MISSING_FIELDS_RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" \
  -d '{"username":""}' \
  http://$EC2_IP/api/auth/signup)

echo "Missing fields response:"
echo $MISSING_FIELDS_RESPONSE | python3 -m json.tool

echo -e "\n\nSignup functionality verification complete!"
