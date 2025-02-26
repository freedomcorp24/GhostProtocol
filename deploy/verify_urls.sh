#!/bin/bash

# Set the EC2 instance IP
EC2_IP="54.215.16.4"

echo "Verifying authentication system URLs"

# Check if the login page is accessible
echo "Checking login page..."
curl -s -o /dev/null -w "%{http_code}" http://$EC2_IP/login.html

# Check if the signup page is accessible
echo "Checking signup page..."
curl -s -o /dev/null -w "%{http_code}" http://$EC2_IP/signup.html

# Check if the API is accessible
echo "Checking API..."
curl -s -o /dev/null -w "%{http_code}" http://$EC2_IP/api/healthz

echo "Verification complete!"
