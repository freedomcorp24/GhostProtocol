#!/bin/bash
# Script to fix nginx configuration

EC2_PUBLIC_IP="54.215.16.4"
SSH_KEY_PATH="~/.ssh/ghostprotocol-dev-key.pem"

echo "Fixing nginx configuration..."

# Create a new nginx configuration
cat > /tmp/ghostprotocol.conf << 'EOF'
server {
    listen 80 default_server;
    server_name _;

    # Web client
    location / {
        root /home/ec2-user/ghostprotocol/web;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    # API endpoints
    location /api/ {
        proxy_pass http://localhost:8080/;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_cache_bypass $http_upgrade;
    }

    # Admin panel
    location /admin/ {
        root /home/ec2-user/ghostprotocol;
        index index.html;
        try_files $uri $uri/ /admin/index.html;
    }

    # Security
    location ~ /\.ht {
        deny all;
    }
}
EOF

# Copy the configuration to the EC2 instance
scp -i $SSH_KEY_PATH /tmp/ghostprotocol.conf ec2-user@$EC2_PUBLIC_IP:/home/ec2-user/ghostprotocol/

# Apply the configuration
ssh -i $SSH_KEY_PATH ec2-user@$EC2_PUBLIC_IP "sudo cp /home/ec2-user/ghostprotocol/ghostprotocol.conf /etc/nginx/conf.d/ghostprotocol.conf && sudo systemctl restart nginx"

# Create simple HTML files if they don't exist
ssh -i $SSH_KEY_PATH ec2-user@$EC2_PUBLIC_IP "
if [ ! -f /home/ec2-user/ghostprotocol/web/index.html ]; then
  mkdir -p /home/ec2-user/ghostprotocol/web
  echo '<html><body><h1>GhostProtocol Web Client</h1><p>Coming soon...</p></body></html>' > /home/ec2-user/ghostprotocol/web/index.html
fi

if [ ! -f /home/ec2-user/ghostprotocol/admin/index.html ]; then
  mkdir -p /home/ec2-user/ghostprotocol/admin
  echo '<html><body><h1>GhostProtocol Admin Panel</h1><p>Coming soon...</p></body></html>' > /home/ec2-user/ghostprotocol/admin/index.html
fi

sudo chmod -R 755 /home/ec2-user/ghostprotocol/web /home/ec2-user/ghostprotocol/admin
"

echo "Nginx configuration fixed. Verifying URLs..."
./deploy/verify_urls.sh
