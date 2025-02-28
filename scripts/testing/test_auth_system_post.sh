#!/bin/bash

# Test the authentication system with POST requests
echo "Testing authentication system with POST requests..."

# Test the login API endpoint
echo "Testing login API endpoint..."
curl -v -X POST -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}' \
  http://54.215.16.4/api/auth/login

# Test the signup API endpoint
echo -e "\n\nTesting signup API endpoint..."
curl -v -X POST -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpassword"}' \
  http://54.215.16.4/api/auth/signup

# Test the admin URL
echo -e "\n\nTesting admin URL..."
curl -s -I http://54.215.16.4/admin/ | head -n 1

# Test the login page
echo -e "\n\nTesting login page..."
curl -s -I http://54.215.16.4/login.html | head -n 1

# Test the signup page
echo -e "\n\nTesting signup page..."
curl -s -I http://54.215.16.4/signup.html | head -n 1

echo -e "\n\nTesting complete!"
