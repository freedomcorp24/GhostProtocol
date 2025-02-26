#!/bin/bash

# Create necessary directories
mkdir -p ~/ghostprotocol/simple_api/static/data

# Copy the API server files
cp app.py ~/ghostprotocol/simple_api/
cp setup_users.py ~/ghostprotocol/simple_api/

# Install required packages
pip3 install fastapi uvicorn pyjwt

# Run the setup_users.py script
cd ~/ghostprotocol/simple_api
python3 setup_users.py

# Install the systemd service
sudo cp ghostprotocol-api.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl enable ghostprotocol-api
sudo systemctl restart ghostprotocol-api

# Update nginx configuration
cat > /tmp/ghostprotocol.conf << 'EOL'
server {
    listen 80;
    server_name _;

    location / {
        root /home/ubuntu/ghostprotocol/web/public;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    location /api/ {
        proxy_pass http://localhost:8000/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    location /admin/ {
        root /home/ubuntu/ghostprotocol/web/public;
        index admin.html;
        try_files $uri $uri/ /admin.html;
    }
}
EOL

sudo cp /tmp/ghostprotocol.conf /etc/nginx/sites-available/ghostprotocol
sudo ln -sf /etc/nginx/sites-available/ghostprotocol /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx

echo "API server fixed successfully!"
