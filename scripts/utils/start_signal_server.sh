#!/bin/bash

# Start the Signal server
echo "Starting the Signal server..."

# Check if the configuration file exists
if [ ! -f "/etc/ghost-protocol/config.yml" ]; then
    echo "Configuration file not found at /etc/ghost-protocol/config.yml"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Java is not installed"
    exit 1
fi

# Start the Signal server
echo "Starting the Signal server in the background..."
nohup java -jar /opt/ghost-protocol/ghost-protocol.jar server /etc/ghost-protocol/config.yml > /var/log/ghost-protocol/ghost-protocol.log 2>&1 &

# Wait for the server to start
echo "Waiting for the server to start..."
sleep 5

# Check if the server is running
if pgrep -f "java -jar /opt/ghost-protocol/ghost-protocol.jar" > /dev/null; then
    echo "Signal server started successfully"
else
    echo "Failed to start the Signal server"
    exit 1
fi

# Print the server status
echo "Signal server status:"
ps aux | grep "java -jar /opt/ghost-protocol/ghost-protocol.jar" | grep -v grep

echo "Signal server started successfully"
