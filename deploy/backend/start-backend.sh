#!/bin/bash
echo "Starting GhostProtocol backend service..."
java -jar ghostprotocol-service.jar server config/aws-dev.yml
