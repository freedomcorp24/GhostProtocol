#!/bin/bash
# GhostProtocol Development Environment EC2 Setup Script

echo "Setting up GhostProtocol EC2 instance in AWS..."

# Set AWS region
AWS_REGION="us-west-1"

# Create security group
echo "Creating security group..."
SECURITY_GROUP_ID=$(aws ec2 create-security-group \
  --group-name ghostprotocol-dev-sg \
  --description "Security group for GhostProtocol development environment" \
  --region $AWS_REGION \
  --query 'GroupId' \
  --output text)

echo "Created security group: $SECURITY_GROUP_ID"

# Add inbound rules
aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 22 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 80 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 443 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 8080 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

aws ec2 authorize-security-group-ingress \
  --group-id $SECURITY_GROUP_ID \
  --protocol tcp \
  --port 3000 \
  --cidr 0.0.0.0/0 \
  --region $AWS_REGION

echo "Added inbound rules to security group"

# Create a key pair
KEY_NAME="ghostprotocol-dev-key"
aws ec2 create-key-pair \
  --key-name $KEY_NAME \
  --query 'KeyMaterial' \
  --output text \
  --region $AWS_REGION > ~/.ssh/$KEY_NAME.pem

chmod 400 ~/.ssh/$KEY_NAME.pem
echo "Created key pair: $KEY_NAME"

# Launch an EC2 instance
INSTANCE_ID=$(aws ec2 run-instances \
  --image-id ami-0c55b159cbfafe1f0 \
  --instance-type t2.medium \
  --key-name $KEY_NAME \
  --security-group-ids $SECURITY_GROUP_ID \
  --region $AWS_REGION \
  --tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=ghostprotocol-dev-instance}]' \
  --query 'Instances[0].InstanceId' \
  --output text)

echo "Created EC2 instance: $INSTANCE_ID"

# Wait for the instance to be running
echo "Waiting for instance to be running..."
aws ec2 wait instance-running --instance-ids $INSTANCE_ID --region $AWS_REGION

# Allocate an Elastic IP
echo "Allocating Elastic IP..."
ALLOCATION_ID=$(aws ec2 allocate-address \
  --domain vpc \
  --query AllocationId \
  --output text \
  --region $AWS_REGION)

# Associate the Elastic IP with the instance
echo "Associating Elastic IP with instance..."
aws ec2 associate-address \
  --instance-id $INSTANCE_ID \
  --allocation-id $ALLOCATION_ID \
  --region $AWS_REGION

# Get the public IP address
PUBLIC_IP=$(aws ec2 describe-addresses \
  --allocation-ids $ALLOCATION_ID \
  --query 'Addresses[0].PublicIp' \
  --output text \
  --region $AWS_REGION)

echo "Instance is running with public IP: $PUBLIC_IP"

# Create a user data script
cat > /tmp/user-data.sh << 'EOD'
#!/bin/bash

# Install dependencies
sudo yum update -y
sudo yum install -y java-11-amazon-corretto-headless maven git nodejs npm

# Install and configure nginx
sudo amazon-linux-extras install nginx1 -y
sudo systemctl enable nginx
sudo systemctl start nginx

# Create directories
sudo mkdir -p /var/www/ghostprotocol/web
sudo mkdir -p /var/www/ghostprotocol/admin
sudo chown -R ec2-user:ec2-user /var/www/ghostprotocol

# Configure nginx
sudo cat > /etc/nginx/conf.d/ghostprotocol.conf << 'EOC'
server {
    listen 80;
    server_name _;

    location / {
        root /var/www/ghostprotocol/web;
        index index.html;
        try_files $uri $uri/ /index.html;
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
        root /var/www/ghostprotocol;
        index index.html;
        try_files $uri $uri/ /admin/index.html;
    }
}
EOC

sudo systemctl restart nginx
EOD

# Wait for SSH to be available
echo "Waiting for SSH to be available..."
while ! nc -z $PUBLIC_IP 22; do
  sleep 5
done

# Copy user data script to instance
echo "Copying user data script to instance..."
scp -i ~/.ssh/$KEY_NAME.pem -o StrictHostKeyChecking=no /tmp/user-data.sh ec2-user@$PUBLIC_IP:/home/ec2-user/

# Execute user data script
echo "Executing user data script..."
ssh -i ~/.ssh/$KEY_NAME.pem -o StrictHostKeyChecking=no ec2-user@$PUBLIC_IP "chmod +x /home/ec2-user/user-data.sh && sudo /home/ec2-user/user-data.sh"

echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Web Client: http://$PUBLIC_IP"
echo "Backend API: http://$PUBLIC_IP/api"
echo "Admin Panel: http://$PUBLIC_IP/admin"
echo ""
echo "Note: It may take a few minutes for the application to be fully deployed."

# Verify instance is running
INSTANCE_STATE=$(aws ec2 describe-instances \
  --instance-ids $INSTANCE_ID \
  --query 'Reservations[0].Instances[0].State.Name' \
  --output text \
  --region $AWS_REGION)

echo "Instance state: $INSTANCE_STATE"

# Update aws_setup_notes.md with EC2 instance information
cat >> aws_config/aws_setup_notes.md << EON

## EC2 Instance Setup

An EC2 instance has been created with the following configuration:

- Instance ID: $INSTANCE_ID
- Instance Type: t2.medium
- AMI ID: ami-0c55b159cbfafe1f0
- Security Group: $SECURITY_GROUP_ID
- Key Pair: $KEY_NAME
- Public IP: $PUBLIC_IP

The instance has been configured with:
- Java 11
- Maven
- Node.js
- npm
- nginx

The following ports are open:
- 22 (SSH)
- 80 (HTTP)
- 443 (HTTPS)
- 8080 (Backend API)
- 3000 (Development server)

The SSH key is stored at ~/.ssh/$KEY_NAME.pem
EON

echo "EC2 instance setup complete."
