#!/bin/bash
# GhostProtocol Development Environment Setup Script

echo "Setting up GhostProtocol development environment in AWS..."

# Set AWS region
AWS_REGION="us-west-1"

# Create S3 buckets
echo "Creating S3 buckets..."
aws s3 mb s3://ghostprotocol-dev-profiles --region $AWS_REGION
aws s3 mb s3://ghostprotocol-dev-vault --region $AWS_REGION
aws s3 mb s3://ghostprotocol-dev-media --region $AWS_REGION
aws s3 mb s3://ghostprotocol-dev-attachments --region $AWS_REGION

# Set bucket policies for public read access where needed
echo "Setting bucket policies..."
cat > /tmp/media-bucket-policy.json << 'EOF'
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::ghostprotocol-dev-media/*"
    }
  ]
}
EOF

aws s3api put-bucket-policy \
  --bucket ghostprotocol-dev-media \
  --policy file:///tmp/media-bucket-policy.json \
  --region $AWS_REGION

# Create DynamoDB tables
echo "Creating DynamoDB tables..."
aws dynamodb create-table \
  --table-name ghostprotocol-dev-accounts \
  --attribute-definitions AttributeName=uuid,AttributeType=S \
  --key-schema AttributeName=uuid,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-devices \
  --attribute-definitions AttributeName=accountUuid,AttributeType=S AttributeName=deviceId,AttributeType=N \
  --key-schema AttributeName=accountUuid,KeyType=HASH AttributeName=deviceId,KeyType=RANGE \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-messages \
  --attribute-definitions AttributeName=destination,AttributeType=S AttributeName=timestamp,AttributeType=N \
  --key-schema AttributeName=destination,KeyType=HASH AttributeName=timestamp,KeyType=RANGE \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-groups \
  --attribute-definitions AttributeName=groupId,AttributeType=S \
  --key-schema AttributeName=groupId,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-crypto-wallets \
  --attribute-definitions AttributeName=accountUuid,AttributeType=S \
  --key-schema AttributeName=accountUuid,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-storage-usage \
  --attribute-definitions AttributeName=accountUuid,AttributeType=S \
  --key-schema AttributeName=accountUuid,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-admin-roles \
  --attribute-definitions AttributeName=username,AttributeType=S \
  --key-schema AttributeName=username,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-vault-items \
  --attribute-definitions AttributeName=accountUuid,AttributeType=S AttributeName=itemId,AttributeType=S \
  --key-schema AttributeName=accountUuid,KeyType=HASH AttributeName=itemId,KeyType=RANGE \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

aws dynamodb create-table \
  --table-name ghostprotocol-dev-subscriptions \
  --attribute-definitions AttributeName=accountUuid,AttributeType=S \
  --key-schema AttributeName=accountUuid,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --region $AWS_REGION

# Create EC2 instance for hosting the application
echo "Creating EC2 instance..."
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

# Get the public IP address
PUBLIC_IP=$(aws ec2 describe-instances \
  --instance-ids $INSTANCE_ID \
  --region $AWS_REGION \
  --query 'Reservations[0].Instances[0].PublicIpAddress' \
  --output text)

echo "Instance is running with public IP: $PUBLIC_IP"

# Create a user data script
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
