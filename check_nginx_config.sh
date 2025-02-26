#!/bin/bash

echo "Checking nginx configuration..."
nginx -t

echo "Restarting nginx..."
sudo systemctl restart nginx

echo "Nginx configuration check complete!"
