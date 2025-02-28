#!/bin/bash

# Signal Server Deployment Script for AWS Test Environment
# This script deploys the Signal server to the AWS test environment

set -e

# Configuration
SIGNAL_CLONE_REPO="https://github.com/freedomcorp24/SignalClone.git"
SIGNAL_SERVER_DIR="SignalClone/Signal-Server"
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

# Check if running as root
if [ "$EUID" -ne 0 ]; then
    error "Please run as root"
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

# Install dependencies
install_dependencies() {
    log "Installing dependencies..."
    apt-get update
    apt-get install -y openjdk-17-jdk maven git nginx certbot python3-certbot-nginx
}

# Clone or update the repository
clone_or_update_repo() {
    log "Cloning or updating the repository..."
    if [ -d "$SIGNAL_SERVER_DIR" ]; then
        cd $SIGNAL_SERVER_DIR
        git pull
    else
        git clone $SIGNAL_CLONE_REPO
        cd $SIGNAL_SERVER_DIR
    fi
}

# Build the server
build_server() {
    log "Building the server..."
    cd $SIGNAL_SERVER_DIR
    mvn clean package -DskipTests
}

# Deploy the server
deploy_server() {
    log "Deploying the server..."
    cp $SIGNAL_SERVER_DIR/service/target/TextSecureServer-*.jar $DEPLOY_DIR/signal-server.jar
    cp $SIGNAL_SERVER_DIR/service/config/sample.yml $CONFIG_DIR/config.yml
    
    # Create service file
    cat > /etc/systemd/system/$SERVICE_NAME.service << EOF
[Unit]
Description=Signal Server
After=network.target

[Service]
User=signal
Group=signal
ExecStart=/usr/bin/java -jar $DEPLOY_DIR/signal-server.jar server $CONFIG_DIR/config.yml
WorkingDirectory=$DEPLOY_DIR
Restart=always
RestartSec=10
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=$SERVICE_NAME

[Install]
WantedBy=multi-user.target
EOF

    # Create signal user if it doesn't exist
    if ! id -u signal > /dev/null 2>&1; then
        useradd -r -s /bin/false signal
    fi
    
    # Set permissions
    chown -R signal:signal $DEPLOY_DIR
    chown -R signal:signal $LOG_DIR
    chown -R signal:signal $CONFIG_DIR
    
    # Reload systemd
    systemctl daemon-reload
}

# Configure the server
configure_server() {
    log "Configuring the server..."
    
    # Backup the original config
    cp $CONFIG_DIR/config.yml $CONFIG_DIR/config.yml.bak
    
    # Update the config
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
    - type: file
      currentLogFilename: $LOG_DIR/signal-server.log
      archivedLogFilenamePattern: $LOG_DIR/signal-server-%d.log.gz
      archivedFileCount: 5
    - type: console

dynamoDbClientConfiguration:
  region: us-east-1

messageCache:
  redis:
    url: redis://localhost:6379/0

pushScheduler:
  url: redis://localhost:6379/0

rateLimiters:
  sms:
    bucketSize: 100
    leakRatePerMinute: 50

accountDatabaseCrawler:
  chunkSize: 1000
  chunkIntervalMs: 1000

directoryQueue:
  url: redis://localhost:6379/0

directory:
  client:
    userAuthenticationTokenSharedSecret: replace-me-with-a-real-secret
  sqs:
    accessKey: replace-me
    accessSecret: replace-me
    queueUrl: replace-me
  server:
    replicationUrl: replace-me

messageStore:
  dynamoDb:
    tableName: messages

keyStore:
  dynamoDb:
    tableName: keys

accountsManager:
  dynamoDb:
    tableName: accounts

profiles:
  dynamoDb:
    tableName: profiles

subscriptions:
  dynamoDb:
    tableName: subscriptions

redisCluster:
  urls:
    - redis://localhost:6379/0

apn:
  bundleId: org.ghostprotocol.messenger
  keyId: replace-me
  teamId: replace-me
  signingKey: replace-me

gcm:
  senderId: replace-me
  apiKey: replace-me

cdn:
  accessKey: replace-me
  accessSecret: replace-me
  bucket: replace-me
  region: us-east-1

attachments:
  accessKey: replace-me
  accessSecret: replace-me
  bucket: replace-me
  region: us-east-1

clientPresenceManager:
  redis:
    url: redis://localhost:6379/0

metricsFactory:
  reporters:
    - type: log
      logger: metrics
      frequency: 1 minute

unidentifiedDelivery:
  certificate: replace-me
  privateKey: replace-me
  expiresDays: 365

voiceVerification:
  url: replace-me
  locales:
    - en-US

recaptcha:
  projectPath: replace-me
  credentialConfigurationJson: replace-me

storageService:
  uri: replace-me
  userAuthenticationTokenSharedSecret: replace-me
  storageCaCertificate: replace-me

backupService:
  uri: replace-me
  userAuthenticationTokenSharedSecret: replace-me
  backupCaCertificate: replace-me

zkConfig:
  serverPublic: replace-me
  serverSecret: replace-me
  enabled: false

callingTurnConfig:
  hostname: replace-me
  secret: replace-me

svr2:
  uri: replace-me
  userAuthenticationTokenSharedSecret: replace-me
  serverPublic: replace-me
  serverSecret: replace-me
  mrenclave: replace-me

paymentsService:
  userAuthenticationTokenSharedSecret: replace-me
  fixerApiKey: replace-me
  coinMarketCapApiKey: replace-me
EOF

    log "Server configuration complete"
}

# Configure Nginx
configure_nginx() {
    log "Configuring Nginx..."
    
    # Create Nginx config
    cat > /etc/nginx/sites-available/$SERVICE_NAME << EOF
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
}
EOF

    # Enable the site
    ln -sf /etc/nginx/sites-available/$SERVICE_NAME /etc/nginx/sites-enabled/
    
    # Test Nginx config
    nginx -t
    
    # Reload Nginx
    systemctl reload nginx
}

# Start the server
start_server() {
    log "Starting the server..."
    systemctl enable $SERVICE_NAME
    systemctl start $SERVICE_NAME
}

# Check server status
check_server_status() {
    log "Checking server status..."
    systemctl status $SERVICE_NAME
}

# Main function
main() {
    log "Starting Signal Server deployment..."
    
    create_directories
    install_dependencies
    clone_or_update_repo
    build_server
    deploy_server
    configure_server
    configure_nginx
    start_server
    check_server_status
    
    log "Signal Server deployment complete!"
}

# Run the main function
main
