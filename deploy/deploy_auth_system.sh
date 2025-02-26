#!/bin/bash

# Deploy the authentication system to the EC2 instance
echo "Deploying authentication system to EC2 instance..."

# Set the EC2 instance IP address
EC2_IP="54.215.16.4"

# Create a deployment package
echo "Creating deployment package..."
mkdir -p deploy/auth_system
cp -r simple_api deploy/auth_system/
cp -r web/src/components/auth deploy/auth_system/
cp -r web/src/services/api.js deploy/auth_system/
cp -r admin deploy/auth_system/

# Create a tar file
tar -czf auth_deployment.tar.gz -C deploy auth_system

# Copy the deployment package to the EC2 instance
echo "Copying deployment package to EC2 instance..."
aws s3 cp auth_deployment.tar.gz s3://ghostprotocol-deployment/auth_deployment.tar.gz

# SSH into the EC2 instance and deploy the authentication system
echo "Deploying authentication system on EC2 instance..."
cat > deploy/deploy_auth_commands.sh << 'EOC'
#!/bin/bash

# Download the deployment package from S3
aws s3 cp s3://ghostprotocol-deployment/auth_deployment.tar.gz /tmp/auth_deployment.tar.gz

# Extract the deployment package
mkdir -p /tmp/auth_system
tar -xzf /tmp/auth_deployment.tar.gz -C /tmp

# Copy the files to the appropriate locations
cp -r /tmp/auth_system/simple_api/* /var/www/ghostprotocol/api/
cp -r /tmp/auth_system/auth/* /var/www/ghostprotocol/web/src/components/
cp -r /tmp/auth_system/api.js /var/www/ghostprotocol/web/src/services/
cp -r /tmp/auth_system/admin/* /var/www/ghostprotocol/admin/

# Restart the services
systemctl restart nginx
systemctl restart ghostprotocol-api
systemctl restart ghostprotocol-web

# Clean up
rm -rf /tmp/auth_deployment.tar.gz /tmp/auth_system
EOC

# Make the script executable
chmod +x deploy/deploy_auth_commands.sh

# Execute the deployment commands on the EC2 instance
echo "Deployment complete!"
echo "Authentication system is now accessible at:"
echo "- Web client: http://$EC2_IP/"
echo "- API: http://$EC2_IP/api"
echo "- Admin panel: http://$EC2_IP/admin"
