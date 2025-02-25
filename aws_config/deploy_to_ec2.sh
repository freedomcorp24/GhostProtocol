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
# Install dependencies
sudo yum update -y
sudo yum install -y java-11-amazon-corretto-headless maven nodejs npm

# Start the backend service
cd ~/GhostProtocol/service
nohup mvn exec:java -Dexec.mainClass="org.ghostprotocol.service.GhostProtocolServerService" -Dexec.args="server src/main/resources/config/aws-dev.yml" > server.log 2>&1 &
echo "Backend service started"

# Start the web client
cd ~/GhostProtocol/web
npm install
nohup npm start > web.log 2>&1 &
echo "Web client started"

# Configure nginx as a reverse proxy
sudo amazon-linux-extras install nginx1
sudo cat > /etc/nginx/conf.d/ghostprotocol.conf << 'EOF'
server {
    listen 80;
    server_name _;

    location / {
        proxy_pass http://localhost:3000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }

    location /api {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }

    location /admin {
        proxy_pass http://localhost:8080/admin;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
EOF

sudo systemctl restart nginx
echo "Nginx configured and restarted"

# Print access information
PUBLIC_IP=$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4)
echo ""
echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Web Client: http://$PUBLIC_IP"
echo "Backend API: http://$PUBLIC_IP/api"
echo "Admin Panel: http://$PUBLIC_IP/admin"
ENDSSH

echo "Deployment completed successfully!"
