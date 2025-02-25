#!/bin/bash
# Script to start GhostProtocol services

echo "Starting GhostProtocol services..."

# Start the backend service
echo "Starting backend service..."
cd ~/GhostProtocol/service
java -jar target/ghostprotocol-server.jar server src/main/resources/config/local-dev.yml &
BACKEND_PID=$!
echo "Backend service started with PID: $BACKEND_PID"

# Start the web client
echo "Starting web client..."
cd ~/GhostProtocol/web
npm start &
WEB_PID=$!
echo "Web client started with PID: $WEB_PID"

# Wait for services to start
echo "Waiting for services to start..."
sleep 10

# Expose services with ngrok
echo "Exposing services with ngrok..."
ngrok http --domain=ghostprotocol-dev.ngrok-free.app 3000 &
NGROK_WEB_PID=$!
echo "Web client exposed at: https://ghostprotocol-dev.ngrok-free.app"

ngrok http --domain=ghostprotocol-api-dev.ngrok-free.app 8080 &
NGROK_API_PID=$!
echo "Backend API exposed at: https://ghostprotocol-api-dev.ngrok-free.app"

echo "Services started and exposed successfully!"
echo ""
echo "GhostProtocol Development Environment Access Information"
echo "========================================================"
echo "Web Client: https://ghostprotocol-dev.ngrok-free.app"
echo "Backend API: https://ghostprotocol-api-dev.ngrok-free.app"
echo "Admin Panel: https://ghostprotocol-api-dev.ngrok-free.app/admin"
echo ""
echo "Press Ctrl+C to stop all services"

# Wait for user to stop services
wait
