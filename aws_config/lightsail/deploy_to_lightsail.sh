#!/bin/bash
# GhostProtocol Deployment Script for AWS Lightsail

echo "Deploying GhostProtocol to AWS Lightsail..."

# Set variables
AWS_REGION="us-west-1"
INSTANCE_NAME="ghostprotocol-dev"
AVAILABILITY_ZONE="us-west-1a"
BLUEPRINT_ID="amazon_linux_2"
BUNDLE_ID="medium_2_0"
KEY_PAIR_NAME="ghostprotocol-dev-key"

# Create key pair
echo "Creating key pair..."
aws lightsail create-key-pair \
  --key-pair-name $KEY_PAIR_NAME \
  --region $AWS_REGION \
  --output text > ~/.ssh/$KEY_PAIR_NAME.pem

chmod 400 ~/.ssh/$KEY_PAIR_NAME.pem

# Create Lightsail instance
echo "Creating Lightsail instance..."
aws lightsail create-instances \
  --instance-names $INSTANCE_NAME \
  --availability-zone $AVAILABILITY_ZONE \
  --blueprint-id $BLUEPRINT_ID \
  --bundle-id $BUNDLE_ID \
  --key-pair-name $KEY_PAIR_NAME \
  --region $AWS_REGION

# Wait for instance to be running
echo "Waiting for instance to be running..."
aws lightsail wait instance-running \
  --instance-name $INSTANCE_NAME \
  --region $AWS_REGION

# Get instance public IP
PUBLIC_IP=$(aws lightsail get-instance \
  --instance-name $INSTANCE_NAME \
  --region $AWS_REGION \
  --query "instance.publicIpAddress" \
  --output text)

echo "Instance is running with public IP: $PUBLIC_IP"

# Create user data script
cat > /tmp/user-data.sh << 'EOF'
#!/bin/bash

# Install dependencies
sudo yum update -y
sudo yum install -y java-11-amazon-corretto-headless maven git nodejs npm

# Clone repository
git clone https://github.com/freedomcorp24/GhostProtocol.git /home/ec2-user/GhostProtocol

# Build backend
cd /home/ec2-user/GhostProtocol
mvn clean package -DskipTests

# Start backend
cd /home/ec2-user/GhostProtocol/service
nohup java -jar target/ghostprotocol-server.jar server src/main/resources/config/aws-dev.yml > server.log 2>&1 &

# Build and start web client
cd /home/ec2-user/GhostProtocol/web
npm install
npm run build
npm install -g serve
nohup serve -s build -l 3000 > web.log 2>&1 &

# Install and configure nginx
sudo amazon-linux-extras install nginx1 -y
sudo cat > /etc/nginx/conf.d/ghostprotocol.conf << 'EOC'
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
EOC

sudo systemctl restart nginx
EOF

# Copy user data script to instance
echo "Copying user data script to instance..."
scp -i ~/.ssh/$KEY_PAIR_NAME.pem -o StrictHostKeyChecking=no /tmp/user-data.sh ec2-user@$PUBLIC_IP:/home/ec2-user/

# Execute user data script
echo "Executing user data script..."
ssh -i ~/.ssh/$KEY_PAIR_NAME.pem -o StrictHostKeyChecking=no ec2-user@$PUBLIC_IP "chmod +x /home/ec2-user/user-data.sh && sudo /home/ec2-user/user-data.sh"

# Open ports
echo "Opening ports..."
aws lightsail open-instance-public-ports \
  --port-info fromPort=80,toPort=80,protocol=TCP \
  --instance-name $INSTANCE_NAME \
  --region $AWS_REGION

aws lightsail open-instance-public-ports \
  --port-info fromPort=443,toPort=443,protocol=TCP \
  --instance-name $INSTANCE_NAME \
  --region $AWS_REGION

aws lightsail open-instance-public-ports \
  --port-info fromPort=8080,toPort=8080,protocol=TCP \
  --instance-name $INSTANCE_NAME \
  --region $AWS_REGION

aws lightsail open-instance-public-ports \
  --port-info fromPort=3000,toPort=3000,protocol=TCP \
  --instance-name $INSTANCE_NAME \
  --region $AWS_REGION

echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Web Client: http://$PUBLIC_IP"
echo "Backend API: http://$PUBLIC_IP/api"
echo "Admin Panel: http://$PUBLIC_IP/admin"
echo ""
echo "Note: It may take a few minutes for the application to be fully deployed."
