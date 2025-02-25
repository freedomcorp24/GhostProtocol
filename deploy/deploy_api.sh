#!/bin/bash

# Deploy the API to the EC2 instance
echo "Deploying API to EC2 instance..."

# Create directory structure
mkdir -p simple_api/api/v1
mkdir -p simple_api/static/data

# Create the users.json file
python simple_api/setup_users.py

# Start the API server
cd simple_api
python app.py > api.log 2>&1 &
echo $! > api.pid
cd ..

echo "API deployment completed."
