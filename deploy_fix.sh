#!/bin/bash
set -e

# Set variables
EC2_PUBLIC_IP="54.215.16.4"
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

# Create directories on EC2 instance
echo "Creating directories on EC2 instance..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "mkdir -p /home/ec2-user/ghostprotocol/static/data"

# Copy files to EC2 instance
echo "Copying files to EC2 instance..."
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no fix_fastapi_app.py ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no fix_service_file.service ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/ghostprotocol-api.service
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no fix_nginx_config.conf ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/ghostprotocol.conf

# Install dependencies
echo "Installing dependencies..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "pip3 install --user fastapi uvicorn pyjwt python-multipart"

# Create users.json file
echo "Creating users.json file..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "mkdir -p /home/ec2-user/ghostprotocol/static/data"
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "cat > /home/ec2-user/ghostprotocol/static/data/users.json << 'EOF'
{
  \"users\": [
    {
      \"id\": \"1\",
      \"username\": \"admin\",
      \"password_hash\": \"240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9\",
      \"role\": \"super_admin\",
      \"created_at\": \"$(date -Iseconds)\",
      \"status\": \"active\",
      \"premium\": true,
      \"subscription_tier\": \"enterprise\",
      \"storage_used\": 0,
      \"storage_limit\": 100000000000
    }
  ]
}
EOF"

# Update nginx configuration
echo "Updating nginx configuration..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo cp /home/ec2-user/ghostprotocol/ghostprotocol.conf /etc/nginx/conf.d/ghostprotocol.conf"

# Install service
echo "Installing service..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo cp /home/ec2-user/ghostprotocol/ghostprotocol-api.service /etc/systemd/system/ && sudo systemctl daemon-reload && sudo systemctl enable ghostprotocol-api"

# Stop the old service
echo "Stopping the old service..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl stop simple-api || true"

# Start the new service
echo "Starting the new service..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl start ghostprotocol-api"

# Restart nginx
echo "Restarting nginx..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl restart nginx"

# Verify services are running
echo "Verifying services are running..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl status ghostprotocol-api"
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl status nginx"

echo "Fix deployment completed successfully!"
echo ""
echo "GhostProtocol Access Information"
echo "===================================="
echo "Web Client: http://$EC2_PUBLIC_IP/"
echo "Admin Panel: http://$EC2_PUBLIC_IP/admin/"
echo "API Endpoint: http://$EC2_PUBLIC_IP/api/"
echo "Login API: http://$EC2_PUBLIC_IP/api/auth/login"
echo "Signup API: http://$EC2_PUBLIC_IP/api/auth/signup"
