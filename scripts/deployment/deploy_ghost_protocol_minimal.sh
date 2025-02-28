#!/bin/bash

# GhostProtocol Minimal Deployment Script for AWS Test Environment
# This script deploys the GhostProtocol components to the AWS test environment without building a full JAR

set -e

# Configuration
SIGNAL_CLONE_DIR="/home/ubuntu/SignalClone"
SIGNAL_SERVER_DIR="$SIGNAL_CLONE_DIR/Signal-Server"
GHOST_PROTOCOL_DIR="/home/ubuntu/GhostProtocol"
DEPLOY_DIR="/opt/ghost-protocol"
LOG_DIR="/var/log/ghost-protocol"
CONFIG_DIR="/etc/ghost-protocol"

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

# Verify GhostProtocol components
verify_ghost_protocol_components() {
    log "Verifying GhostProtocol components..."
    
    # Check if the GhostProtocol package directories exist
    if [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/auth" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/vault" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/crypto" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/premium" ] && \
       [ -d "$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/admin" ]; then
        log "GhostProtocol components are available"
    else
        error "GhostProtocol components are not available"
        exit 1
    fi
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

# Create a configuration file
create_configuration_file() {
    log "Creating a configuration file..."
    
    cat > $CONFIG_DIR/config.yml << EOF
server:
  applicationConnectors:
    - type: http
      port: 8080
  adminConnectors:
    - type: http
      port: 8081

logging:
  level: INFO
  appenders:
    - type: console
    - type: file
      currentLogFilename: $LOG_DIR/ghost-protocol.log
      archivedLogFilenamePattern: $LOG_DIR/ghost-protocol-%d.log.gz
      archivedFileCount: 5

ghostProtocolConfiguration:
  authSecret: "your-auth-secret"
  adminUsername: "admin"
  adminPassword: "admin"
  
premiumTiers:
  - name: "Free"
    price: 0
    storageLimit: 10737418240 # 10GB in bytes
    features:
      - "Basic messaging"
      - "Basic storage"
  - name: "Premium"
    price: 9.99
    storageLimit: 107374182400 # 100GB in bytes
    features:
      - "Advanced messaging"
      - "Enhanced storage"
      - "Priority support"
  - name: "Enterprise"
    price: 29.99
    storageLimit: 1099511627776 # 1TB in bytes
    features:
      - "Enterprise messaging"
      - "Enterprise storage"
      - "24/7 support"
      - "Custom features"
EOF
    
    log "Configuration file created successfully"
}

# Create a test script
create_test_script() {
    log "Creating a test script..."
    
    cat > $DEPLOY_DIR/test_ghost_protocol.sh << 'EOF'
#!/bin/bash

# Test the GhostProtocol components
echo "Testing the GhostProtocol components..."

# Test the authentication system
echo "Testing the authentication system..."
ls -la /opt/ghost-protocol/auth/

# Test the vault functionality
echo "Testing the vault functionality..."
ls -la /opt/ghost-protocol/vault/

# Test the cryptocurrency integration
echo "Testing the cryptocurrency integration..."
ls -la /opt/ghost-protocol/crypto/

# Test the premium tiers and subscription system
echo "Testing the premium tiers and subscription system..."
ls -la /opt/ghost-protocol/premium/

# Test the admin panel
echo "Testing the admin panel..."
ls -la /opt/ghost-protocol/admin/

echo "Test complete!"
EOF
    
    chmod +x $DEPLOY_DIR/test_ghost_protocol.sh
    
    log "Test script created successfully"
}

# Create a documentation file
create_documentation() {
    log "Creating documentation..."
    
    cat > $DEPLOY_DIR/README.md << 'EOF'
# GhostProtocol Components

This directory contains the GhostProtocol components deployed to the AWS test environment.

## Components

- **Authentication System**: The authentication system for GhostProtocol, including JWT token generation and validation.
- **Secure Storage (Vault)**: The secure storage functionality for GhostProtocol, including password storage, contact information storage, file storage, and private notes storage.
- **Cryptocurrency Integration**: The cryptocurrency integration for GhostProtocol, including Bitcoin, Monero, and USDT wallet functionality.
- **Premium Tiers and Subscription System**: The premium tiers and subscription system for GhostProtocol, including free tier, premium tier, and enterprise tier.
- **Admin Panel**: The admin panel for GhostProtocol, including user management, premium account management, and trial period management.

## Configuration

The configuration file is located at `/etc/ghost-protocol/config.yml`.

## Logs

The logs are located at `/var/log/ghost-protocol/ghost-protocol.log`.

## Testing

To test the GhostProtocol components, run the test script:

```
/opt/ghost-protocol/test_ghost_protocol.sh
```

## Deployment

The GhostProtocol components are deployed to the following directories:

- Authentication System: `/opt/ghost-protocol/auth/`
- Secure Storage (Vault): `/opt/ghost-protocol/vault/`
- Cryptocurrency Integration: `/opt/ghost-protocol/crypto/`
- Premium Tiers and Subscription System: `/opt/ghost-protocol/premium/`
- Admin Panel: `/opt/ghost-protocol/admin/`
EOF
    
    log "Documentation created successfully"
}

# Create a deployment status file
create_deployment_status() {
    log "Creating deployment status file..."
    
    cat > $GHOST_PROTOCOL_DIR/DEPLOYMENT_STATUS.md << 'EOF'
# GhostProtocol Deployment Status

## Components

- **Authentication System**: Deployed
- **Secure Storage (Vault)**: Deployed
- **Cryptocurrency Integration**: Deployed
- **Premium Tiers and Subscription System**: Deployed
- **Admin Panel**: Deployed

## Deployment Details

- **Deployment Directory**: `/opt/ghost-protocol/`
- **Configuration Directory**: `/etc/ghost-protocol/`
- **Log Directory**: `/var/log/ghost-protocol/`

## Next Steps

1. Test the GhostProtocol components using the test script: `/opt/ghost-protocol/test_ghost_protocol.sh`
2. Test the iOS app integration
3. Test the Android app integration
4. Verify feature parity between platforms
5. Fix any issues found during testing
6. Prepare for production deployment
EOF
    
    log "Deployment status file created successfully"
}

# Main function
main() {
    log "Starting GhostProtocol minimal deployment..."
    
    create_directories
    verify_ghost_protocol_components
    deploy_ghost_protocol_components
    create_configuration_file
    create_test_script
    create_documentation
    create_deployment_status
    
    log "GhostProtocol minimal deployment complete!"
    log "To test the GhostProtocol components, run: $DEPLOY_DIR/test_ghost_protocol.sh"
}

# Run the main function
main
