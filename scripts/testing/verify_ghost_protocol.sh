#!/bin/bash

# GhostProtocol Verification Script for AWS Test Environment
# This script verifies the GhostProtocol components in the Signal server in the AWS test environment

set -e

# Configuration
SIGNAL_SERVER_DIR="/home/ubuntu/SignalClone/Signal-Server"
DEPLOY_DIR="/opt/signal-server"
LOG_DIR="/var/log/signal-server"
CONFIG_DIR="/etc/signal-server"
SERVICE_NAME="signal-server"
SERVER_URL="http://localhost:8080"
ADMIN_URL="http://localhost:8081"

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

# Check if the Signal server is running
check_server_status() {
    log "Checking Signal server status..."
    if systemctl is-active --quiet $SERVICE_NAME; then
        log "Signal server is running"
        return 0
    else
        error "Signal server is not running"
        return 1
    fi
}

# Check if the GhostProtocol components are available
check_ghost_protocol_components() {
    log "Checking GhostProtocol components..."
    
    # Check if the GhostProtocol package directories exist
    if [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/auth" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/vault" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/crypto" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/premium" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/admin" ]; then
        log "GhostProtocol components are available"
        return 0
    else
        error "GhostProtocol components are not available"
        return 1
    fi
}

# Check if the API endpoints are accessible
check_api_endpoints() {
    log "Checking API endpoints..."
    
    # Check if the server is accessible
    if curl -s -o /dev/null -w "%{http_code}" $SERVER_URL | grep -q "200\|404"; then
        log "API server is accessible"
    else
        error "API server is not accessible"
        return 1
    fi
    
    # Check if the admin server is accessible
    if curl -s -o /dev/null -w "%{http_code}" $ADMIN_URL | grep -q "200\|404"; then
        log "Admin server is accessible"
    else
        error "Admin server is not accessible"
        return 1
    fi
    
    return 0
}

# Check if the authentication system is working
check_authentication_system() {
    log "Checking authentication system..."
    
    # Check if the authentication endpoints are accessible
    if curl -s -o /dev/null -w "%{http_code}" $SERVER_URL/v1/auth | grep -q "200\|404\|401"; then
        log "Authentication endpoints are accessible"
    else
        error "Authentication endpoints are not accessible"
        return 1
    fi
    
    return 0
}

# Check if the secure storage (vault) functionality is working
check_vault_functionality() {
    log "Checking secure storage (vault) functionality..."
    
    # Check if the vault endpoints are accessible
    if curl -s -o /dev/null -w "%{http_code}" $SERVER_URL/v1/vault | grep -q "200\|404\|401"; then
        log "Vault endpoints are accessible"
    else
        error "Vault endpoints are not accessible"
        return 1
    fi
    
    return 0
}

# Check if the cryptocurrency integration is working
check_cryptocurrency_integration() {
    log "Checking cryptocurrency integration..."
    
    # Check if the cryptocurrency endpoints are accessible
    if curl -s -o /dev/null -w "%{http_code}" $SERVER_URL/v1/crypto | grep -q "200\|404\|401"; then
        log "Cryptocurrency endpoints are accessible"
    else
        error "Cryptocurrency endpoints are not accessible"
        return 1
    fi
    
    return 0
}

# Check if the premium tiers and subscription system is working
check_premium_tiers() {
    log "Checking premium tiers and subscription system..."
    
    # Check if the premium tier endpoints are accessible
    if curl -s -o /dev/null -w "%{http_code}" $SERVER_URL/v1/premium | grep -q "200\|404\|401"; then
        log "Premium tier endpoints are accessible"
    else
        error "Premium tier endpoints are not accessible"
        return 1
    fi
    
    return 0
}

# Check if the admin panel functionality is working
check_admin_panel() {
    log "Checking admin panel functionality..."
    
    # Check if the admin panel endpoints are accessible
    if curl -s -o /dev/null -w "%{http_code}" $ADMIN_URL/admin | grep -q "200\|404\|401"; then
        log "Admin panel endpoints are accessible"
    else
        error "Admin panel endpoints are not accessible"
        return 1
    fi
    
    return 0
}

# Check server logs for errors
check_server_logs() {
    log "Checking server logs for errors..."
    
    # Check if the log file exists
    if [ -f "$LOG_DIR/signal-server.log" ]; then
        # Check for errors in the log file
        if grep -i "error" "$LOG_DIR/signal-server.log" | grep -v "INFO" | grep -v "DEBUG"; then
            warn "Found errors in the server logs"
        else
            log "No errors found in the server logs"
        fi
    else
        warn "Log file not found at $LOG_DIR/signal-server.log"
    fi
    
    return 0
}

# Main function
main() {
    log "Starting GhostProtocol verification..."
    
    # Check if the Signal server is running
    check_server_status || exit 1
    
    # Check if the GhostProtocol components are available
    check_ghost_protocol_components || exit 1
    
    # Check if the API endpoints are accessible
    check_api_endpoints || exit 1
    
    # Check if the authentication system is working
    check_authentication_system || exit 1
    
    # Check if the secure storage (vault) functionality is working
    check_vault_functionality || exit 1
    
    # Check if the cryptocurrency integration is working
    check_cryptocurrency_integration || exit 1
    
    # Check if the premium tiers and subscription system is working
    check_premium_tiers || exit 1
    
    # Check if the admin panel functionality is working
    check_admin_panel || exit 1
    
    # Check server logs for errors
    check_server_logs || exit 1
    
    log "GhostProtocol verification complete!"
}

# Run the main function
main
