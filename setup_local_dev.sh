#!/bin/bash
set -e

echo "Setting up local development environment for GhostProtocol..."

# Create storage directories
mkdir -p /tmp/ghostprotocol-storage/attachments
mkdir -p /tmp/ghostprotocol-storage/profiles
mkdir -p /tmp/ghostprotocol-storage/vault

# Check if ngrok is installed
if ! command -v ngrok &> /dev/null; then
    echo "Installing ngrok..."
    curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null
    echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | sudo tee /etc/apt/sources.list.d/ngrok.list >/dev/null
    sudo apt update && sudo apt install -y ngrok
fi

# Build backend service
echo "Building backend service..."
cd service
mvn clean package -DskipTests || echo "Maven build failed, but continuing..."

# Build web client
echo "Building web client..."
cd ../web
npm install || echo "NPM install failed, but continuing..."
npm run build || echo "NPM build failed, but continuing..."

echo "Local development environment setup complete!"
