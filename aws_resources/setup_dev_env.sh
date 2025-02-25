#!/bin/bash
# Script to setup the AWS development environment

echo "Setting up AWS development environment..."

# Create EC2 instance
echo "Creating EC2 instance..."
./aws_config/create_ec2_instance.sh

# Deploy backend service
echo "Deploying backend service..."
./aws_resources/deploy_backend.sh

# Deploy web client
echo "Deploying web client..."
mkdir -p /home/ec2-user/ghostprotocol/web
echo '<html><body><h1>GhostProtocol Web Client</h1><p>Coming soon...</p></body></html>' > /tmp/index.html
scp -i ~/.ssh/ghostprotocol-dev-key.pem /tmp/index.html ec2-user@54.215.16.4:/home/ec2-user/ghostprotocol/web/index.html

# Deploy admin panel
echo "Deploying admin panel..."
mkdir -p /home/ec2-user/ghostprotocol/admin
echo '<html><body><h1>GhostProtocol Admin Panel</h1><p>Coming soon...</p></body></html>' > /tmp/admin.html
scp -i ~/.ssh/ghostprotocol-dev-key.pem /tmp/admin.html ec2-user@54.215.16.4:/home/ec2-user/ghostprotocol/admin/index.html

# Configure nginx
echo "Configuring nginx..."
./deploy/fix_nginx.sh

# Verify URLs
echo "Verifying URLs..."
./deploy/verify_urls.sh

echo "AWS development environment setup complete!"
