#!/bin/bash

# Set the EC2 instance IP address
EC2_IP="54.215.16.4"

# Create a deployment package
echo "Creating deployment package..."
mkdir -p deploy/auth_system
cp -r simple_api deploy/auth_system/
cp -r web/src/components/auth deploy/auth_system/
cp -r web/src/services/api.js deploy/auth_system/

# Create a tar file
tar -czf auth_deployment.tar.gz -C deploy auth_system

# Copy the deployment package to the EC2 instance
echo "Copying deployment package to EC2 instance..."
scp auth_deployment.tar.gz ec2-user@$EC2_IP:/tmp/

# SSH into the EC2 instance and deploy the authentication system
echo "Deploying authentication system on EC2 instance..."
ssh ec2-user@$EC2_IP << 'ENDSSH'
# Extract the deployment package
mkdir -p /tmp/auth_system
tar -xzf /tmp/auth_deployment.tar.gz -C /tmp

# Copy the files to the appropriate locations
sudo cp -r /tmp/auth_system/simple_api/* /var/www/ghostprotocol/api/
sudo cp -r /tmp/auth_system/auth/* /var/www/ghostprotocol/web/src/components/
sudo cp -r /tmp/auth_system/api.js /var/www/ghostprotocol/web/src/services/

# Restart the services
sudo systemctl restart nginx
sudo systemctl restart ghostprotocol-api
sudo systemctl restart ghostprotocol-web

# Clean up
rm -rf /tmp/auth_deployment.tar.gz /tmp/auth_system
ENDSSH

echo "Deployment complete!"
echo "Authentication system is now accessible at:"
echo "- Web client: http://$EC2_IP/"
echo "- API: http://$EC2_IP/api"
echo "- Admin panel: http://$EC2_IP/admin"
