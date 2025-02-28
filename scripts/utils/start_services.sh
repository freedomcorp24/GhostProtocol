#!/bin/bash
set -e

echo "Starting GhostProtocol services..."

# Start backend service
echo "Starting backend service..."
cd service
nohup java -jar target/service-*.jar server src/main/resources/config/local-dev.yml > backend.log 2>&1 &
BACKEND_PID=$!
echo "Backend service started with PID: $BACKEND_PID"

# Start web client
echo "Starting web client..."
cd ../web
nohup npx serve -s build -l 3000 > web.log 2>&1 &
WEB_PID=$!
echo "Web client started with PID: $WEB_PID"

# Start ngrok tunnels
echo "Starting ngrok tunnels..."
ngrok config add-authtoken 1234567890abcdef # Replace with actual token if available
nohup ngrok http --domain=ghostprotocol-api-dev.ngrok-free.app 8080 > ngrok-api.log 2>&1 &
nohup ngrok http --domain=ghostprotocol-dev.ngrok-free.app 3000 > ngrok-web.log 2>&1 &

echo "Services started successfully!"
echo "Backend API URL: https://ghostprotocol-api-dev.ngrok-free.app"
echo "Web Client URL: https://ghostprotocol-dev.ngrok-free.app"
echo "Admin Panel URL: https://ghostprotocol-api-dev.ngrok-free.app/admin"

# Save PIDs for later cleanup
echo "$BACKEND_PID" > backend.pid
echo "$WEB_PID" > web.pid

echo "To stop services, run: ./stop_services.sh"
