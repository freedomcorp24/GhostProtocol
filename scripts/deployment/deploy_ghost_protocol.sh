#!/bin/bash

# GhostProtocol Deployment Script for AWS Test Environment
# This script deploys the GhostProtocol components to the Signal server in the AWS test environment

set -e

# Configuration
SIGNAL_CLONE_REPO="https://github.com/freedomcorp24/SignalClone.git"
SIGNAL_SERVER_DIR="/home/ubuntu/SignalClone/Signal-Server"
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
    apt-get install -y openjdk-17-jdk maven git nginx certbot python3-certbot-nginx redis-server
    
    # Check Java version
    java_version=$(java -version 2>&1 | head -n 1 | awk -F '"' '{print $2}')
    log "Java version: $java_version"
    
    # Check Maven version
    maven_version=$(mvn --version | head -n 1 | awk '{print $3}')
    log "Maven version: $maven_version"
    
    # Check if Maven version is sufficient
    REQUIRED_VERSION="3.8.1"
    
    if [ "$(printf '%s\n' "$REQUIRED_VERSION" "$maven_version" | sort -V | head -n1)" != "$REQUIRED_VERSION" ]; then
        log "Maven version $maven_version is less than required version $REQUIRED_VERSION"
        log "Installing Maven 3.8.8..."
        
        # Download and install Maven 3.8.8
        cd /tmp
        wget https://dlcdn.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.tar.gz
        tar -xzvf apache-maven-3.8.8-bin.tar.gz
        mv apache-maven-3.8.8 /opt/
        ln -sf /opt/apache-maven-3.8.8/bin/mvn /usr/bin/mvn
        
        # Verify Maven version
        maven_version=$(mvn --version | head -n 1 | awk '{print $3}')
        log "Installed Maven version: $maven_version"
    fi
    
    # Install Redis
    log "Installing Redis..."
    systemctl enable redis-server
    systemctl start redis-server
    
    # Check Redis status
    redis_status=$(systemctl is-active redis-server)
    log "Redis status: $redis_status"
}

# Clone or update the repository
clone_or_update_repo() {
    log "Cloning or updating the repository..."
    REPO_DIR="/home/ubuntu/SignalClone"
    
    if [ -d "$REPO_DIR" ]; then
        log "Repository already exists, updating..."
        cd "$REPO_DIR"
        git pull
    else
        log "Cloning repository..."
        cd /home/ubuntu
        git clone $SIGNAL_CLONE_REPO
    fi
    
    # Check if Signal-Server directory exists
    if [ ! -d "$SIGNAL_SERVER_DIR" ]; then
        error "Signal-Server directory not found at $SIGNAL_SERVER_DIR!"
        exit 1
    fi
    
    log "Repository updated successfully"
}

# Build the GhostProtocol components
build_ghost_protocol() {
    log "Building the GhostProtocol components..."
    
    # Navigate to the Signal-Server directory
    cd "$SIGNAL_SERVER_DIR"
    
    # Check if the service directory exists
    if [ ! -d "service" ]; then
        error "Service directory not found at $SIGNAL_SERVER_DIR/service!"
        exit 1
    fi
    
    # Create the GhostProtocol package directories if they don't exist
    mkdir -p service/src/main/java/org/ghostprotocol/textsecuregcm/auth
    mkdir -p service/src/main/java/org/ghostprotocol/textsecuregcm/vault
    mkdir -p service/src/main/java/org/ghostprotocol/textsecuregcm/crypto
    mkdir -p service/src/main/java/org/ghostprotocol/textsecuregcm/premium
    mkdir -p service/src/main/java/org/ghostprotocol/textsecuregcm/admin
    
    log "GhostProtocol package directories created successfully"
    
    # Update the service pom.xml to include the GhostProtocol dependencies
    log "Updating service pom.xml..."
    
    # Check if the service pom.xml exists
    if [ ! -f "service/pom.xml" ]; then
        error "Service pom.xml not found at $SIGNAL_SERVER_DIR/service/pom.xml!"
        exit 1
    fi
    
    # Create a backup of the original pom.xml
    cp service/pom.xml service/pom.xml.bak
    
    # Update the service pom.xml to include the GhostProtocol dependencies
    sed -i 's/<dependencies>/<dependencies>\n        <!-- GhostProtocol Dependencies -->\n        <dependency>\n            <groupId>org.bitcoinj<\/groupId>\n            <artifactId>bitcoinj-core<\/artifactId>\n            <version>0.16.1<\/version>\n        <\/dependency>\n        <dependency>\n            <groupId>com.github.monero-ecosystem<\/groupId>\n            <artifactId>monero-java<\/artifactId>\n            <version>0.8.0<\/version>\n        <\/dependency>\n        <dependency>\n            <groupId>org.web3j<\/groupId>\n            <artifactId>core<\/artifactId>\n            <version>4.9.4<\/version>\n        <\/dependency>/' service/pom.xml
    
    log "Service pom.xml updated successfully"
    
    # Try to build only the service module with skipping tests
    log "Building only the service module with skipping tests..."
    cd service
    mvn clean package -DskipTests
    
    # If build fails, try with dependency resolution
    if [ $? -ne 0 ]; then
        log "Build failed, trying with dependency resolution..."
        mvn clean package -DskipTests -U
    fi
    
    # If build still fails, try with preview features
    if [ $? -ne 0 ]; then
        log "Build failed, trying with preview features..."
        mvn clean package -DskipTests -U -Dmaven.compiler.release=17 -Dmaven.compiler.enablePreview=true
    fi
    
    # If build still fails, try with standalone JAR approach
    if [ $? -ne 0 ]; then
        log "Build failed, trying with standalone JAR approach..."
        cd ..
        
        # Create a standalone GhostProtocol JAR
        log "Creating standalone GhostProtocol JAR..."
        
        # Create a temporary directory for the standalone JAR
        mkdir -p /tmp/ghostprotocol
        
        # Copy the GhostProtocol components to the temporary directory
        cp -r service/src/main/java/org/ghostprotocol /tmp/ghostprotocol/
        
        # Create a simple pom.xml for the standalone JAR
        cat > /tmp/ghostprotocol/pom.xml << EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.ghostprotocol</groupId>
    <artifactId>ghostprotocol</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <!-- GhostProtocol Dependencies -->
        <dependency>
            <groupId>org.bitcoinj</groupId>
            <artifactId>bitcoinj-core</artifactId>
            <version>0.16.1</version>
        </dependency>
        <dependency>
            <groupId>com.github.monero-ecosystem</groupId>
            <artifactId>monero-java</artifactId>
            <version>0.8.0</version>
        </dependency>
        <dependency>
            <groupId>org.web3j</groupId>
            <artifactId>core</artifactId>
            <version>4.9.4</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <release>17</release>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <mainClass>org.ghostprotocol.Main</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF
        
        # Build the standalone JAR
        cd /tmp/ghostprotocol
        mvn clean package
        
        # Copy the standalone JAR to the deploy directory
        cp target/ghostprotocol-1.0-SNAPSHOT.jar $DEPLOY_DIR/ghostprotocol.jar
        
        log "Standalone GhostProtocol JAR created successfully"
    else
        # Copy the service JAR to the deploy directory
        cp target/TextSecureServer-*.jar $DEPLOY_DIR/signal-server.jar
        
        log "Service JAR built successfully"
    fi
    
    cd "$SIGNAL_SERVER_DIR"
}

# Deploy the server
deploy_server() {
    log "Deploying the server..."
    
    # Check if the service JAR exists
    if [ -f "$DEPLOY_DIR/signal-server.jar" ]; then
        log "Using Signal server JAR"
        SERVER_JAR="$DEPLOY_DIR/signal-server.jar"
    elif [ -f "$DEPLOY_DIR/ghostprotocol.jar" ]; then
        log "Using standalone GhostProtocol JAR"
        SERVER_JAR="$DEPLOY_DIR/ghostprotocol.jar"
    else
        error "No server JAR found!"
        exit 1
    fi
    
    # Copy the config file
    if [ -f "$SIGNAL_SERVER_DIR/service/config/sample.yml" ]; then
        cp "$SIGNAL_SERVER_DIR/service/config/sample.yml" $CONFIG_DIR/config.yml
    else
        # Create a default config file
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
    fi
    
    # Create service file
    cat > /etc/systemd/system/$SERVICE_NAME.service << EOF
[Unit]
Description=Signal Server
After=network.target

[Service]
User=signal
Group=signal
ExecStart=/usr/bin/java -jar $SERVER_JAR server $CONFIG_DIR/config.yml
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
    log "Starting GhostProtocol deployment..."
    
    create_directories
    install_dependencies
    clone_or_update_repo
    build_ghost_protocol
    deploy_server
    configure_nginx
    start_server
    check_server_status
    
    log "GhostProtocol deployment complete!"
}

# Run the main function
main
