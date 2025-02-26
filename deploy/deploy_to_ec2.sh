#!/bin/bash

# Deploy the GhostProtocol API to the EC2 instance
echo "Deploying GhostProtocol API to EC2 instance..."

# Create a deployment package
mkdir -p deploy/tmp/simple_api/api/v1
cp -r simple_api/app.py deploy/tmp/simple_api/
cp -r simple_api/setup_users.py deploy/tmp/simple_api/
cp -r simple_api/api/users.py deploy/tmp/simple_api/api/
cp -r simple_api/api/v1/auth_custom.py deploy/tmp/simple_api/api/v1/
cp -r simple_api/api/subscriptions.py deploy/tmp/simple_api/api/
cp -r simple_api/api/vault.py deploy/tmp/simple_api/api/
cp -r simple_api/api/analytics.py deploy/tmp/simple_api/api/

# Create a systemd service file
cat > deploy/tmp/ghostprotocol-api.service << 'SERVICEEOF'
[Unit]
Description=GhostProtocol API Service
After=network.target

[Service]
User=ubuntu
WorkingDirectory=/home/ubuntu/ghostprotocol/simple_api
ExecStart=/usr/bin/python3 app.py
Restart=always
RestartSec=5

[Install]
WantedBy=multi-user.target
SERVICEEOF

# Create a deployment script
cat > deploy/tmp/deploy.sh << 'DEPLOYEOF'
#!/bin/bash

# Stop the API service if it's running
sudo systemctl stop ghostprotocol-api || true

# Create directory structure
mkdir -p /home/ubuntu/ghostprotocol/simple_api/api/v1
mkdir -p /home/ubuntu/ghostprotocol/simple_api/static/data

# Copy the API files
cp -r simple_api/* /home/ubuntu/ghostprotocol/simple_api/

# Install dependencies
sudo apt-get update
sudo apt-get install -y python3-pip
pip3 install pyjwt

# Initialize users data
cd /home/ubuntu/ghostprotocol/simple_api
python3 setup_users.py

# Install and start the service
sudo cp ghostprotocol-api.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable ghostprotocol-api
sudo systemctl start ghostprotocol-api
sudo systemctl status ghostprotocol-api

# Update nginx configuration
sudo tee /etc/nginx/sites-available/ghostprotocol << 'NGINXEOF'
server {
    listen 80 default_server;
    server_name _;
    
    # Enable debug logging
    error_log /var/log/nginx/ghostprotocol-error.log debug;
    access_log /var/log/nginx/ghostprotocol-access.log;

    # Web client
    location / {
        root /usr/share/nginx/html/web;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    # API endpoints
    location /api/ {
        proxy_pass http://localhost:8080/api/;
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
        alias /usr/share/nginx/html/admin/;
        index index.html;
        try_files $uri $uri/ /admin/index.html;
    }
}
NGINXEOF

sudo ln -sf /etc/nginx/sites-available/ghostprotocol /etc/nginx/sites-enabled/
sudo rm -f /etc/nginx/sites-enabled/default
sudo nginx -t && sudo systemctl restart nginx

echo "Deployment completed successfully."
DEPLOYEOF

chmod +x deploy/tmp/deploy.sh

# Create a tar file
cd deploy/tmp
tar -czf ../auth_deployment.tar.gz *
cd ../..

echo "Deployment package created: deploy/auth_deployment.tar.gz"
