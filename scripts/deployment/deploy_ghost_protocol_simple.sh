#!/bin/bash

# GhostProtocol Simple Deployment Script for AWS Test Environment
# This script deploys the GhostProtocol components to the Signal server in the AWS test environment

set -e

# Configuration
SIGNAL_CLONE_DIR="/home/ubuntu/SignalClone"
SIGNAL_SERVER_DIR="$SIGNAL_CLONE_DIR/Signal-Server"
DEPLOY_DIR="/opt/signal-server"
LOG_DIR="/var/log/signal-server"
CONFIG_DIR="/etc/signal-server"
SERVICE_NAME="signal-server"

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

# Install dependencies
install_dependencies() {
    log "Installing dependencies..."
    apt-get update
    apt-get install -y openjdk-17-jdk maven git nginx redis-server certbot python3-certbot-nginx
    
    # Check Java version
    java_version=$(java -version 2>&1 | head -1 | cut -d'"' -f2 | sed 's/^1\.//' | cut -d'.' -f1)
    log "Java version: $java_version"
    
    # Check Maven version
    mvn_version=$(mvn -version | head -1 | awk '{print $3}')
    log "Maven version: $mvn_version"
    
    # Install Redis
    log "Installing Redis..."
    systemctl enable redis-server
    systemctl start redis-server
    systemctl status redis-server | grep "active"
    log "Redis status: $(systemctl is-active redis-server)"
}

# Clone or update the repository
clone_or_update_repository() {
    log "Cloning or updating the repository..."
    if [ -d "$SIGNAL_CLONE_DIR" ]; then
        log "Repository already exists, updating..."
        cd $SIGNAL_CLONE_DIR
        git pull
    else
        log "Cloning the repository..."
        git clone https://github.com/freedomcorp24/SignalClone.git $SIGNAL_CLONE_DIR
    fi
    log "Repository updated successfully"
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

# Create a configuration file for the Signal server
create_configuration_file() {
    log "Creating a configuration file for the Signal server..."
    
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
      currentLogFilename: $LOG_DIR/signal-server.log
      archivedLogFilenamePattern: $LOG_DIR/signal-server-%d.log.gz
      archivedFileCount: 5

dynamoDbClientConfiguration:
  region: us-west-2

redisClusterConfiguration:
  hostAndPort: localhost:6379

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

# Configure Nginx as a reverse proxy
configure_nginx() {
    log "Configuring Nginx as a reverse proxy..."
    
    cat > /etc/nginx/sites-available/ghostprotocol << EOF
server {
    listen 80;
    server_name _;
    
    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host \$host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto \$scheme;
    }
    
    location /admin {
        proxy_pass http://localhost:8081;
        proxy_set_header Host \$host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto \$scheme;
    }
}
EOF
    
    ln -sf /etc/nginx/sites-available/ghostprotocol /etc/nginx/sites-enabled/
    rm -f /etc/nginx/sites-enabled/default
    
    # Test Nginx configuration
    nginx -t
    
    # Reload Nginx
    systemctl reload nginx
    
    log "Nginx configured successfully"
}

# Create a simple test script
create_test_script() {
    log "Creating a test script..."
    
    cat > $DEPLOY_DIR/test_server.sh << EOF
#!/bin/bash

# Test the Signal server
echo "Testing the Signal server..."

# Test the API server
echo "Testing the API server..."
curl -v http://localhost:8080/

# Test the admin server
echo "Testing the admin server..."
curl -v http://localhost:8081/

# Test the authentication endpoints
echo "Testing the authentication endpoints..."
curl -v http://localhost:8080/v1/auth

# Test the vault endpoints
echo "Testing the vault endpoints..."
curl -v http://localhost:8080/v1/vault

# Test the cryptocurrency endpoints
echo "Testing the cryptocurrency endpoints..."
curl -v http://localhost:8080/v1/crypto

# Test the premium tier endpoints
echo "Testing the premium tier endpoints..."
curl -v http://localhost:8080/v1/premium

# Test the admin panel endpoints
echo "Testing the admin panel endpoints..."
curl -v http://localhost:8081/admin

echo "Test complete!"
EOF
    
    chmod +x $DEPLOY_DIR/test_server.sh
    
    log "Test script created successfully"
}

# Create a simple deployment script
create_deployment_script() {
    log "Creating a deployment script..."
    
    cat > $DEPLOY_DIR/deploy_server.sh << EOF
#!/bin/bash

# Deploy the Signal server
echo "Deploying the Signal server..."

# Copy the Signal server JAR to the deployment directory
echo "Copying the Signal server JAR to the deployment directory..."
cp $SIGNAL_SERVER_DIR/service/target/service-*.jar $DEPLOY_DIR/signal-server.jar

# Start the Signal server
echo "Starting the Signal server..."
java -jar $DEPLOY_DIR/signal-server.jar server $CONFIG_DIR/config.yml

echo "Deployment complete!"
EOF
    
    chmod +x $DEPLOY_DIR/deploy_server.sh
    
    log "Deployment script created successfully"
}

# Create a systemd service file
create_systemd_service_file() {
    log "Creating a systemd service file..."
    
    cat > /etc/systemd/system/$SERVICE_NAME.service << EOF
[Unit]
Description=Signal Server
After=network.target

[Service]
User=root
WorkingDirectory=$DEPLOY_DIR
ExecStart=/usr/bin/java -jar $DEPLOY_DIR/signal-server.jar server $CONFIG_DIR/config.yml
Restart=always
RestartSec=5
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=$SERVICE_NAME

[Install]
WantedBy=multi-user.target
EOF
    
    log "Systemd service file created successfully"
}

# Main function
main() {
    log "Starting GhostProtocol deployment..."
    
    create_directories
    install_dependencies
    clone_or_update_repository
    verify_ghost_protocol_components
    create_configuration_file
    configure_nginx
    create_test_script
    create_deployment_script
    create_systemd_service_file
    
    log "GhostProtocol deployment complete!"
    log "To deploy the Signal server, run: $DEPLOY_DIR/deploy_server.sh"
    log "To test the Signal server, run: $DEPLOY_DIR/test_server.sh"
}

# Run the main function
main
