#!/bin/bash
set -e

# Deploy API
echo "Deploying API..."
cd ~/GhostProtocol/simple_api
./deploy_api.sh

# Deploy Admin Panel
echo "Deploying Admin Panel..."
cd ~/GhostProtocol/deploy/admin
./deploy_admin.sh

echo "Deployment completed successfully!"
echo ""
echo "GhostProtocol Access Information"
echo "==============================="
echo "API Endpoint: http://54.215.16.4/api"
echo "Admin Panel: http://54.215.16.4/admin"
