#!/bin/bash
# Script to deploy GhostProtocol to mock AWS environment

echo "Starting deployment to mock AWS environment..."
MOCK_EC2_IP="54.123.45.67"
DEPLOY_DIR="/home/ubuntu/GhostProtocol/deploy"

# Create necessary directories
echo "Creating deployment directories..."
mkdir -p $DEPLOY_DIR/backend/build
mkdir -p $DEPLOY_DIR/web/build
mkdir -p $DEPLOY_DIR/admin

# Copy backend files
echo "Preparing backend service..."
cp -r /home/ubuntu/GhostProtocol/service/target/*.jar $DEPLOY_DIR/backend/build/
cp -r /home/ubuntu/GhostProtocol/service/src/main/resources/config $DEPLOY_DIR/backend/

# Copy web client files
echo "Preparing web client..."
mkdir -p $DEPLOY_DIR/web/build/static
mkdir -p $DEPLOY_DIR/web/build/images
echo '<!DOCTYPE html><html><head><title>GhostProtocol</title></head><body><h1>GhostProtocol Web Client</h1><p>Welcome to the secure messaging platform.</p></body></html>' > $DEPLOY_DIR/web/build/index.html
cp -r /home/ubuntu/GhostProtocol/web/public/images/* $DEPLOY_DIR/web/build/images/ 2>/dev/null || echo "No images to copy"

# Copy admin panel files
echo "Preparing admin panel..."
echo '<!DOCTYPE html><html><head><title>GhostProtocol Admin</title></head><body><h1>GhostProtocol Admin Panel</h1><p>Administrative interface for GhostProtocol.</p></body></html>' > $DEPLOY_DIR/admin/index.html

# Configure Nginx
echo "Configuring Nginx..."
cp $DEPLOY_DIR/nginx/ghostprotocol.conf /etc/nginx/sites-available/ghostprotocol 2>/dev/null || echo "Mock Nginx configuration"

echo "Starting services..."
echo "Mock backend service started on port 8080"
echo "Mock web client available at http://$MOCK_EC2_IP/"
echo "Mock admin panel available at http://$MOCK_EC2_IP/admin"
echo "Mock API available at http://$MOCK_EC2_IP/api"

echo "Deployment to mock AWS environment completed successfully!"
