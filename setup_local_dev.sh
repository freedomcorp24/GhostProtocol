#!/bin/bash
set -e

echo "Setting up local development environment for GhostProtocol..."

# Create storage directories
mkdir -p /tmp/ghostprotocol-storage/attachments
mkdir -p /tmp/ghostprotocol-storage/profiles
mkdir -p /tmp/ghostprotocol-storage/vault

# Install dependencies
echo "Installing dependencies..."
sudo apt-get update
sudo apt-get install -y openjdk-17-jdk maven nodejs npm

# Build backend service
echo "Building backend service..."
cd service
mvn clean package -DskipTests

# Build web client
echo "Building web client..."
cd ../web
npm install
npm run build

echo "Local development environment setup complete!"
