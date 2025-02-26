#!/bin/bash

# Set the EC2 instance IP
EC2_IP="54.215.16.4"

echo "Verifying API endpoints..."

# Check if the health check endpoint is accessible
echo "Checking health check endpoint..."
curl -s http://$EC2_IP/api/healthz

# Test signup endpoint
echo -e "\n\nTesting signup endpoint..."
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}' \
  http://$EC2_IP/api/auth/signup

# Test login endpoint
echo -e "\n\nTesting login endpoint..."
curl -s -X POST -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}' \
  http://$EC2_IP/api/auth/login

echo -e "\n\nAPI endpoint verification complete!"
