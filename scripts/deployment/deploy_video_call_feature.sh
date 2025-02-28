#!/bin/bash

# Deploy the video call feature to the EC2 instance
echo "Deploying video call feature to EC2 instance..."

# Copy the video call component files
echo "Copying video call component files..."
scp -i ~/.ssh/ghostprotocol.pem web/src/components/video-call/VideoCallPage.js ec2-user@54.215.16.4:/home/ec2-user/ghostprotocol/web/src/components/video-call/
scp -i ~/.ssh/ghostprotocol.pem web/src/components/video-call/VideoCallStyles.css ec2-user@54.215.16.4:/home/ec2-user/ghostprotocol/web/src/components/video-call/

# Copy the navigation bar component
echo "Copying navigation bar component..."
scp -i ~/.ssh/ghostprotocol.pem web/src/components/navigation/NavigationBar.js ec2-user@54.215.16.4:/home/ec2-user/ghostprotocol/web/src/components/navigation/

# Copy the updated App.js file
echo "Copying updated App.js file..."
scp -i ~/.ssh/ghostprotocol.pem web/src/App.js ec2-user@54.215.16.4:/home/ec2-user/ghostprotocol/web/src/

# Rebuild the web client
echo "Rebuilding the web client..."
ssh -i ~/.ssh/ghostprotocol.pem ec2-user@54.215.16.4 "cd /home/ec2-user/ghostprotocol/web && npm run build"

# Copy the build files to the nginx directory
echo "Copying build files to nginx directory..."
ssh -i ~/.ssh/ghostprotocol.pem ec2-user@54.215.16.4 "sudo cp -r /home/ec2-user/ghostprotocol/web/build/* /usr/share/nginx/html/web/"

# Restart nginx
echo "Restarting nginx..."
ssh -i ~/.ssh/ghostprotocol.pem ec2-user@54.215.16.4 "sudo systemctl restart nginx"

echo "Video call feature deployed successfully!"
