#!/bin/bash
# GhostProtocol Deployment Script for EC2

echo "Deploying GhostProtocol to EC2 instance..."

# Set variables
EC2_IP=$1
SSH_KEY=$2

if [ -z "$EC2_IP" ] || [ -z "$SSH_KEY" ]; then
  echo "Usage: $0 <ec2-ip-address> <ssh-key-path>"
  exit 1
fi

# Package the application
echo "Packaging the application..."
cd ~/GhostProtocol
mvn clean package -DskipTests

# Copy the application to the EC2 instance
echo "Copying application to EC2 instance..."
scp -i $SSH_KEY -r ~/GhostProtocol ec2-user@$EC2_IP:/home/ec2-user/

# SSH into the instance and start the application
echo "Starting the application on EC2 instance..."
ssh -i $SSH_KEY ec2-user@$EC2_IP << 'ENDSSH'
# Start the backend service
cd ~/GhostProtocol/service
nohup java -jar target/ghostprotocol-server.jar server src/main/resources/config/aws-dev.yml > server.log 2>&1 &
echo "Backend service started"

# Start the web client
cd ~/GhostProtocol/web
npm install
npm run build
npm install -g serve
nohup serve -s build -l 3000 > web.log 2>&1 &
echo "Web client started"

# Restart nginx
sudo systemctl restart nginx
echo "Nginx restarted"
ENDSSH

echo "Deployment completed successfully!"
