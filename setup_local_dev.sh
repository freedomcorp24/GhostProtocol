#!/bin/bash
# Setup script for local development environment with public URL

echo "Setting up local development environment for GhostProtocol..."

# Install required dependencies
echo "Installing dependencies..."
sudo apt-get update
sudo apt-get install -y openjdk-17-jdk maven npm

# Build the backend service
echo "Building backend service..."
cd ~/GhostProtocol/service
mvn clean package -DskipTests

# Build the web client
echo "Building web client..."
cd ~/GhostProtocol/web
npm install
npm run build

# Install ngrok for exposing local services
echo "Installing ngrok for public URL access..."
curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null
echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | sudo tee /etc/apt/sources.list.d/ngrok.list
sudo apt-get update
sudo apt-get install -y ngrok

echo "Local development environment setup complete!"
