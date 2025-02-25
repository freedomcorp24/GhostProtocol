#!/bin/bash
set -e

echo "Starting GhostProtocol services..."

# Start backend service
echo "Starting backend service..."
cd service
nohup java -jar target/ghostprotocol-server.jar server src/main/resources/config/local-dev.yml > server.log 2>&1 &
BACKEND_PID=$!
echo "Backend service started with PID: $BACKEND_PID"

# Start web client
echo "Starting web client..."
cd ../web
nohup npm start > web.log 2>&1 &
WEB_PID=$!
echo "Web client started with PID: $WEB_PID"

# Expose services via ngrok
echo "Exposing services via ngrok..."
cd ..
nohup ngrok http --domain=ghostprotocol-api-dev.ngrok-free.app 8080 > ngrok-api.log 2>&1 &
nohup ngrok http --domain=ghostprotocol-dev.ngrok-free.app 3000 > ngrok-web.log 2>&1 &

echo "Services started and exposed via ngrok:"
echo "Backend API: https://ghostprotocol-api-dev.ngrok-free.app"
echo "Web Client: https://ghostprotocol-dev.ngrok-free.app"
echo "Admin Panel: https://ghostprotocol-api-dev.ngrok-free.app/admin"

echo "To stop services, run: kill $BACKEND_PID $WEB_PID"
