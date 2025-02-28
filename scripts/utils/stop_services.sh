#!/bin/bash

echo "Stopping GhostProtocol services..."

# Stop backend service
if [ -f backend.pid ]; then
    BACKEND_PID=$(cat backend.pid)
    echo "Stopping backend service (PID: $BACKEND_PID)..."
    kill $BACKEND_PID || true
    rm backend.pid
fi

# Stop web client
if [ -f web.pid ]; then
    WEB_PID=$(cat web.pid)
    echo "Stopping web client (PID: $WEB_PID)..."
    kill $WEB_PID || true
    rm web.pid
fi

# Stop ngrok tunnels
echo "Stopping ngrok tunnels..."
pkill -f ngrok || true

echo "All services stopped."
