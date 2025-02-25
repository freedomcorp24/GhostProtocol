#!/bin/bash
# GhostProtocol Web Client Startup Script

echo "Starting GhostProtocol web client..."

# Navigate to web directory
cd ~/GhostProtocol/web

# Install dependencies
npm install

# Start the web client
npm start
