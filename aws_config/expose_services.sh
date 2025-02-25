#!/bin/bash

# GhostProtocol Service Exposure Script

echo "Exposing GhostProtocol services for external access..."

# Expose backend service
echo "Exposing backend service..."
BACKEND_PORT=8080
expose_port local_port=$BACKEND_PORT

# Expose web client
echo "Exposing web client..."
WEB_PORT=3000
expose_port local_port=$WEB_PORT

echo "Services exposed successfully!"
echo "Backend URL: http://localhost:$BACKEND_PORT"
echo "Web Client URL: http://localhost:$WEB_PORT"
