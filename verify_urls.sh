#!/bin/bash

# Verify the URLs are working
echo "Verifying URLs..."

echo "Testing web client URL (http://54.215.16.4/)..."
curl -s http://54.215.16.4/ | head -n 10

echo -e "\n\nTesting API URL (http://54.215.16.4/api/)..."
curl -s http://54.215.16.4/api/

echo -e "\n\nTesting admin URL (http://54.215.16.4/admin/)..."
curl -s http://54.215.16.4/admin/ | head -n 10

echo -e "\n\nTesting signup endpoint..."
curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpassword"}' http://54.215.16.4/api/auth/signup

echo -e "\n\nTesting login endpoint..."
curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpassword"}' http://54.215.16.4/api/auth/login

echo -e "\n\nVerification completed."
