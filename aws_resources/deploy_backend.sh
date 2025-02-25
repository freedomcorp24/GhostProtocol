#!/bin/bash
# GhostProtocol Backend Deployment Script

echo "Deploying GhostProtocol backend to AWS..."

# Set variables
AWS_REGION="us-west-1"
EC2_INSTANCE_ID="i-02c36ae2dc15653c6"  # Actual instance ID
EC2_PUBLIC_IP="54.215.16.4"  # Actual public IP
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

# Package the backend
echo "Packaging the backend..."
cd ~/GhostProtocol/service
mvn clean package -DskipTests

# Copy configuration file
echo "Copying configuration file..."
mkdir -p target/config
cp ../deploy/backend/config/aws-dev.yml target/config/

# Create service file
echo "Creating service file..."
cat > target/ghostprotocol-backend.service << 'EOD'
[Unit]
Description=GhostProtocol Backend Service
After=network.target

[Service]
User=ec2-user
WorkingDirectory=/home/ec2-user/ghostprotocol
ExecStart=/usr/bin/java -jar /home/ec2-user/ghostprotocol/ghostprotocol-server.jar server /home/ec2-user/ghostprotocol/config/aws-dev.yml
Restart=always

[Install]
WantedBy=multi-user.target
EOD

# Create start script
echo "Creating start script..."
cat > target/start-backend.sh << 'EOD'
#!/bin/bash
java -jar ghostprotocol-server.jar server config/aws-dev.yml
EOD

# Deploy to EC2 instance
echo "Deploying to EC2 instance..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "mkdir -p /home/ec2-user/ghostprotocol/config"
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no target/ghostprotocol-server.jar ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no target/config/aws-dev.yml ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/config/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no target/ghostprotocol-backend.service ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/
scp -i $SSH_KEY_PATH -o StrictHostKeyChecking=no target/start-backend.sh ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "chmod +x /home/ec2-user/ghostprotocol/start-backend.sh"

# Install service
echo "Installing service..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo cp /home/ec2-user/ghostprotocol/ghostprotocol-backend.service /etc/systemd/system/ && sudo systemctl daemon-reload && sudo systemctl enable ghostprotocol-backend && sudo systemctl start ghostprotocol-backend"

# Verify service is running
echo "Verifying service is running..."
ssh -i $SSH_KEY_PATH -o StrictHostKeyChecking=no ec2-user@$EC2_PUBLIC_IP "sudo systemctl status ghostprotocol-backend"

echo "Backend deployment completed successfully!"
echo ""
echo "GhostProtocol Backend Access Information"
echo "========================================"
echo "Backend API: http://$EC2_PUBLIC_IP/api"
echo "Admin Panel: http://$EC2_PUBLIC_IP/admin"
