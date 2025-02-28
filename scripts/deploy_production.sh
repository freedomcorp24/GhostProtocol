#!/bin/bash

# GhostProtocol Production Deployment Script
echo "Deploying GhostProtocol to production environment..."

# Configuration
SIGNAL_CLONE_DIR="/home/ubuntu/SignalClone"
SIGNAL_SERVER_DIR="$SIGNAL_CLONE_DIR/Signal-Server"
GHOST_PROTOCOL_DIR="/home/ubuntu/GhostProtocol"
DEPLOY_DIR="/opt/ghost-protocol"
LOG_DIR="/var/log/ghost-protocol"
CONFIG_DIR="/etc/ghost-protocol"
CONFIG_FILE="$GHOST_PROTOCOL_DIR/config/production/config.yml"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
NC='\033[0m' # No Color

# Log function
log() {
    echo -e "${GREEN}[$(date '+%Y-%m-%d %H:%M:%S')] $1${NC}"
}

error() {
    echo -e "${RED}[$(date '+%Y-%m-%d %H:%M:%S')] ERROR: $1${NC}" >&2
}

warn() {
    echo -e "${YELLOW}[$(date '+%Y-%m-%d %H:%M:%S')] WARNING: $1${NC}"
}

# Check if running as root
if [ "$EUID" -ne 0 ]; then
    error "This script must be run as root"
    exit 1
fi

# Create directories
create_directories() {
    log "Creating directories..."
    mkdir -p $DEPLOY_DIR
    mkdir -p $LOG_DIR
    mkdir -p $CONFIG_DIR
    chmod 755 $DEPLOY_DIR
    chmod 755 $LOG_DIR
    chmod 755 $CONFIG_DIR
}

# Copy configuration file
copy_configuration_file() {
    log "Copying configuration file..."
    cp $CONFIG_FILE $CONFIG_DIR/config.yml
    chmod 644 $CONFIG_DIR/config.yml
}

# Build Signal Server JAR
build_signal_server_jar() {
    log "Building Signal Server JAR..."
    cd $SIGNAL_SERVER_DIR
    mvn clean package -DskipTests
    if [ $? -ne 0 ]; then
        error "Failed to build Signal Server JAR"
        exit 1
    fi
    cp $SIGNAL_SERVER_DIR/service/target/service-1.0-SNAPSHOT.jar $DEPLOY_DIR/ghost-protocol.jar
    chmod 644 $DEPLOY_DIR/ghost-protocol.jar
}

# Deploy GhostProtocol components
deploy_ghost_protocol_components() {
    log "Deploying GhostProtocol components..."
    
    # Create the deployment directory structure
    mkdir -p $DEPLOY_DIR/auth
    mkdir -p $DEPLOY_DIR/vault
    mkdir -p $DEPLOY_DIR/crypto
    mkdir -p $DEPLOY_DIR/premium
    mkdir -p $DEPLOY_DIR/admin
    
    # Copy the GhostProtocol components to the deployment directory
    cp -r $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/auth/* $DEPLOY_DIR/auth/
    cp -r $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/vault/* $DEPLOY_DIR/vault/
    cp -r $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/crypto/* $DEPLOY_DIR/crypto/
    cp -r $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/premium/* $DEPLOY_DIR/premium/
    cp -r $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/admin/* $DEPLOY_DIR/admin/
    
    # Copy the GhostProtocolService and GhostProtocolConfiguration classes
    cp $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/GhostProtocolService.java $DEPLOY_DIR/ 2>/dev/null || true
    cp $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/GhostProtocolConfiguration.java $DEPLOY_DIR/
    
    log "GhostProtocol components deployed successfully"
}

# Create systemd service
create_systemd_service() {
    log "Creating systemd service..."
    cat > /etc/systemd/system/ghost-protocol.service << 'EOT'
[Unit]
Description=GhostProtocol Service
After=network.target

[Service]
Type=simple
User=ghost-protocol
Group=ghost-protocol
WorkingDirectory=/opt/ghost-protocol
ExecStart=/usr/bin/java -jar /opt/ghost-protocol/ghost-protocol.jar server /etc/ghost-protocol/config.yml
Restart=on-failure
RestartSec=5
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=ghost-protocol

[Install]
WantedBy=multi-user.target
EOT
    
    # Create user and group
    id -u ghost-protocol &>/dev/null || useradd -r -s /bin/false ghost-protocol
    
    # Set permissions
    chown -R ghost-protocol:ghost-protocol $DEPLOY_DIR
    chown -R ghost-protocol:ghost-protocol $LOG_DIR
    chown -R ghost-protocol:ghost-protocol $CONFIG_DIR
    
    # Reload systemd
    systemctl daemon-reload
}

# Start GhostProtocol service
start_ghost_protocol_service() {
    log "Starting GhostProtocol service..."
    systemctl enable ghost-protocol
    systemctl start ghost-protocol
    
    # Wait for the service to start
    sleep 5
    
    # Check if the service is running
    if systemctl is-active --quiet ghost-protocol; then
        log "GhostProtocol service started successfully"
    else
        error "Failed to start GhostProtocol service"
        exit 1
    fi
}

# Verify deployment
verify_deployment() {
    log "Verifying deployment..."
    
    # Check if the service is running
    if ! systemctl is-active --quiet ghost-protocol; then
        error "GhostProtocol service is not running"
        exit 1
    fi
    
    # Check if the API endpoints are accessible
    log "Checking API endpoints..."
    
    # Check authentication endpoint
    curl -s http://localhost:8080/v1/auth > /dev/null
    if [ $? -ne 0 ]; then
        error "Authentication endpoint is not accessible"
        exit 1
    fi
    
    # Check vault endpoint
    curl -s http://localhost:8080/v1/vault > /dev/null
    if [ $? -ne 0 ]; then
        error "Vault endpoint is not accessible"
        exit 1
    fi
    
    # Check cryptocurrency endpoint
    curl -s http://localhost:8080/v1/crypto > /dev/null
    if [ $? -ne 0 ]; then
        error "Cryptocurrency endpoint is not accessible"
        exit 1
    fi
    
    # Check premium tier endpoint
    curl -s http://localhost:8080/v1/premium > /dev/null
    if [ $? -ne 0 ]; then
        error "Premium tier endpoint is not accessible"
        exit 1
    fi
    
    # Check admin panel endpoint
    curl -s http://localhost:8081/admin > /dev/null
    if [ $? -ne 0 ]; then
        error "Admin panel endpoint is not accessible"
        exit 1
    fi
    
    log "Deployment verification completed successfully"
}

# Main function
main() {
    log "Starting GhostProtocol production deployment..."
    
    create_directories
    copy_configuration_file
    build_signal_server_jar
    deploy_ghost_protocol_components
    create_systemd_service
    start_ghost_protocol_service
    verify_deployment
    
    log "GhostProtocol production deployment completed successfully"
}

# Run the main function
main
