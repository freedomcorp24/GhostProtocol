#!/bin/bash

# Install ngrok if not already installed
if ! command -v ngrok &> /dev/null; then
    echo "Installing ngrok..."
    sudo apt-get update
    sudo apt-get install -y ngrok
fi

# Expose the test environments
echo "Exposing web test environment on port 3000..."
ngrok http 3000 &

echo "Exposing iOS test environment on port 3001..."
ngrok http 3001 &

echo "Exposing Android test environment on port 3002..."
ngrok http 3002 &

echo "Test environments exposed. Press Ctrl+C to stop."
wait
