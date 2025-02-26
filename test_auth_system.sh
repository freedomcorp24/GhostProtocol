#!/bin/bash

# Start the API server
cd simple_api
python3 app.py > api.log 2>&1 &
echo $! > api.pid
cd ..

# Wait for the server to start
sleep 2

# Test the root endpoint
echo "Testing root endpoint..."
curl -s http://localhost:8080/

# Test the signup endpoint
echo -e "\n\nTesting signup endpoint..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser2","password":"testpassword"}' http://localhost:8080/api/auth/signup

# Test the login endpoint
echo -e "\n\nTesting login endpoint..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"testuser2","password":"testpassword"}' http://localhost:8080/api/auth/login

# Test the admin login
echo -e "\n\nTesting admin login..."
curl -s -X POST -H "Content-Type: application/json" -d '{"username":"admin","password":"admin123"}' http://localhost:8080/api/auth/login

# Kill the API server
kill $(cat simple_api/api.pid)
rm simple_api/api.pid

echo -e "\n\nAuthentication system testing completed!"
