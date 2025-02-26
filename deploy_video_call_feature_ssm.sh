#!/bin/bash

# Deploy the video call feature to the EC2 instance using AWS Systems Manager
echo "Deploying video call feature to EC2 instance..."

# Get the EC2 instance ID
INSTANCE_ID=$(aws ec2 describe-instances --filters "Name=private-ip-address,Values=54.215.16.4" --query "Reservations[0].Instances[0].InstanceId" --output text)

# Create a deployment package
echo "Creating deployment package..."
mkdir -p /tmp/video-call-deployment/video-call
mkdir -p /tmp/video-call-deployment/screen-sharing
mkdir -p /tmp/video-call-deployment/services

# Copy the video call component files
cp -r web/src/components/video-call/* /tmp/video-call-deployment/video-call/
cp -r web/src/components/screen-sharing/* /tmp/video-call-deployment/screen-sharing/
cp web/src/services/webrtc.js /tmp/video-call-deployment/services/
cp web/src/components/navigation/NavigationBar.js /tmp/video-call-deployment/
cp web/src/App.js /tmp/video-call-deployment/

# Create a deployment script to run on the EC2 instance
cat > /tmp/video-call-deployment/deploy.sh << 'EOF'
#!/bin/bash

# Create necessary directories
mkdir -p /home/ec2-user/ghostprotocol/web/src/components/video-call
mkdir -p /home/ec2-user/ghostprotocol/web/src/components/screen-sharing
mkdir -p /home/ec2-user/ghostprotocol/web/src/services
mkdir -p /home/ec2-user/ghostprotocol/web/src/components/navigation

# Copy files to the appropriate locations
cp -r /tmp/deployment/video-call/* /home/ec2-user/ghostprotocol/web/src/components/video-call/
cp -r /tmp/deployment/screen-sharing/* /home/ec2-user/ghostprotocol/web/src/components/screen-sharing/
cp /tmp/deployment/services/webrtc.js /home/ec2-user/ghostprotocol/web/src/services/
cp /tmp/deployment/NavigationBar.js /home/ec2-user/ghostprotocol/web/src/components/navigation/
cp /tmp/deployment/App.js /home/ec2-user/ghostprotocol/web/src/

# Install webrtc-adapter dependency if not already installed
cd /home/ec2-user/ghostprotocol/web
npm install --save webrtc-adapter@^8.1.1

# Build the web client
npm run build

# Copy the build files to the nginx directory
sudo cp -r /home/ec2-user/ghostprotocol/web/build/* /usr/share/nginx/html/web/

# Update nginx configuration to handle video call routing
sudo tee /etc/nginx/conf.d/ghostprotocol.conf > /dev/null << 'EOC'
server {
    listen 80;
    server_name _;

    location / {
        root /usr/share/nginx/html/web;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    location /api/ {
        proxy_pass http://localhost:5000/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_read_timeout 90;
        proxy_connect_timeout 90;
        proxy_request_buffering off;
        proxy_buffering off;
        client_max_body_size 10m;
    }

    location /admin/ {
        root /usr/share/nginx/html;
        index index.html;
        try_files $uri $uri/ /admin/index.html;
    }

    # Handle WebRTC signaling
    location /signaling/ {
        proxy_pass http://localhost:5000/signaling/;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
EOC

# Restart nginx
sudo systemctl restart nginx

# Update the FastAPI app to include WebRTC signaling
cat > /tmp/webrtc_signaling.py << 'EOP'
from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from typing import Dict

# WebRTC signaling connections
class ConnectionManager:
    def __init__(self):
        self.active_connections: Dict[str, WebSocket] = {}

    async def connect(self, websocket: WebSocket, user_id: str):
        await websocket.accept()
        self.active_connections[user_id] = websocket

    def disconnect(self, user_id: str):
        if user_id in self.active_connections:
            del self.active_connections[user_id]

    async def send_personal_message(self, message: dict, user_id: str):
        if user_id in self.active_connections:
            await self.active_connections[user_id].send_json(message)

manager = ConnectionManager()

# Add this to your FastAPI app
async def setup_webrtc_signaling(app: FastAPI):
    @app.websocket("/signaling/{user_id}")
    async def websocket_endpoint(websocket: WebSocket, user_id: str):
        await manager.connect(websocket, user_id)
        try:
            while True:
                data = await websocket.receive_json()
                target_user_id = data.get("target")
                if target_user_id:
                    await manager.send_personal_message(data, target_user_id)
        except WebSocketDisconnect:
            manager.disconnect(user_id)
EOP

# Add WebRTC signaling to the FastAPI app
sudo cp /tmp/webrtc_signaling.py /home/ec2-user/ghostprotocol/

# Update the FastAPI app
cat > /tmp/update_fastapi.py << 'EOP'
import sys
import re

# Path to the FastAPI app
app_path = '/home/ec2-user/ghostprotocol/fix_fastapi_app.py'

# Read the current content
with open(app_path, 'r') as file:
    content = file.read()

# Check if WebRTC signaling is already imported
if 'import webrtc_signaling' not in content:
    # Add import statement
    import_statement = 'import webrtc_signaling\n'
    content = import_statement + content

# Check if WebRTC signaling setup is already added
if 'setup_webrtc_signaling' not in content:
    # Find the app creation line
    app_creation_pattern = r'app\s*=\s*FastAPI\(\)'
    match = re.search(app_creation_pattern, content)
    
    if match:
        # Add the setup code after app creation
        setup_code = '\n\n# Setup WebRTC signaling\n@app.on_event("startup")\nasync def startup_event():\n    await webrtc_signaling.setup_webrtc_signaling(app)\n'
        insert_pos = match.end()
        content = content[:insert_pos] + setup_code + content[insert_pos:]

# Write the updated content back
with open(app_path, 'w') as file:
    file.write(content)

print("FastAPI app updated with WebRTC signaling support")
EOP

# Run the update script
sudo python3 /tmp/update_fastapi.py

# Restart the FastAPI service
sudo systemctl restart ghostprotocol-api

echo "Video call feature deployed successfully!"
EOF

# Make the deployment script executable
chmod +x /tmp/video-call-deployment/deploy.sh

# Create a tar archive of the deployment package
cd /tmp
tar -czf video-call-deployment.tar.gz -C /tmp video-call-deployment

# Upload the deployment package to S3
aws s3 cp /tmp/video-call-deployment.tar.gz s3://ghostprotocol-deployment/video-call-deployment.tar.gz

# Run the deployment command on the EC2 instance using SSM
aws ssm send-command \
  --instance-ids "$INSTANCE_ID" \
  --document-name "AWS-RunShellScript" \
  --parameters commands=["mkdir -p /tmp/deployment", \
                         "aws s3 cp s3://ghostprotocol-deployment/video-call-deployment.tar.gz /tmp/video-call-deployment.tar.gz", \
                         "tar -xzf /tmp/video-call-deployment.tar.gz -C /tmp", \
                         "bash /tmp/video-call-deployment/deploy.sh"] \
  --output text

echo "Deployment command sent to EC2 instance. Check the AWS Systems Manager console for status."
