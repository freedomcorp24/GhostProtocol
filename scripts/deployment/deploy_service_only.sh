#!/bin/bash

# Signal Server Service-Only Deployment Script for AWS Test Environment
# This script deploys only the service module of the Signal server to the AWS test environment

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
    REPO_DIR="/home/ubuntu/SignalClone"
    SERVER_DIR="$REPO_DIR/Signal-Server"
    
    if [ -d "$REPO_DIR" ]; then
        cd "$REPO_DIR"
        git pull
        cd - > /dev/null
    else
        cd /home/ubuntu
        git clone $SIGNAL_CLONE_REPO
    fi
    
    # Check if Signal-Server directory exists
    if [ ! -d "$SERVER_DIR" ]; then
        error "Signal-Server directory not found at $SERVER_DIR!"
        exit 1
    fi
}

# Build only the service module
build_service_module() {
    log "Building only the service module..."
    
    # Navigate to the service directory
    cd /home/ubuntu/$SIGNAL_SERVER_DIR/service
    
    # Create a standalone pom.xml for the service module
    cat > pom.xml.standalone << EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.whispersystems.textsecure</groupId>
    <artifactId>service</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <dropwizard.version>2.1.4</dropwizard.version>
        <jackson.version>2.13.4</jackson.version>
        <jetty.version>9.4.49.v20220914</jetty.version>
        <reactor.version>3.4.24</reactor.version>
        <grpc.version>1.50.2</grpc.version>
        <protobuf.version>3.21.9</protobuf.version>
        <aws.version>2.17.292</aws.version>
        <guice.version>5.1.0</guice.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Dropwizard -->
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-core</artifactId>
            <version>\${dropwizard.version}</version>
        </dependency>
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-auth</artifactId>
            <version>\${dropwizard.version}</version>
        </dependency>
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-client</artifactId>
            <version>\${dropwizard.version}</version>
        </dependency>
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-jdbi3</artifactId>
            <version>\${dropwizard.version}</version>
        </dependency>
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-testing</artifactId>
            <version>\${dropwizard.version}</version>
            <scope>test</scope>
        </dependency>
        
        <!-- Jakarta EE -->
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
            <version>3.0.2</version>
        </dependency>
        <dependency>
            <groupId>jakarta.ws.rs</groupId>
            <artifactId>jakarta.ws.rs-api</artifactId>
            <version>3.1.0</version>
        </dependency>
        <dependency>
            <groupId>jakarta.inject</groupId>
            <artifactId>jakarta.inject-api</artifactId>
            <version>2.0.1</version>
        </dependency>
        
        <!-- AWS SDK -->
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>\${aws.version}</version>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>s3</artifactId>
            <version>\${aws.version}</version>
        </dependency>
        
        <!-- Other -->
        <dependency>
            <groupId>com.google.inject</groupId>
            <artifactId>guice</artifactId>
            <version>\${guice.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.4.1</version>
                <configuration>
                    <createDependencyReducedPom>true</createDependencyReducedPom>
                    <filters>
                        <filter>
                            <artifact>*:*</artifact>
                            <excludes>
                                <exclude>META-INF/*.SF</exclude>
                                <exclude>META-INF/*.DSA</exclude>
                                <exclude>META-INF/*.RSA</exclude>
                            </excludes>
                        </filter>
                    </filters>
                </configuration>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>org.ghostprotocol.textsecuregcm.GhostProtocolService</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <release>17</release>
                    <compilerArgs>
                        <arg>--enable-preview</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF

    # Backup the original pom.xml
    cp pom.xml pom.xml.bak
    
    # Use the standalone pom.xml
    cp pom.xml.standalone pom.xml
    
    # Try to build with skipping tests and enabling preview features
    log "Building the service module with skipping tests and enabling preview features..."
    mvn clean package -DskipTests -Dmaven.compiler.release=17 -Dmaven.compiler.enablePreview=true
    
    # If build fails, try with dependency resolution
    if [ $? -ne 0 ]; then
        log "Build failed, trying with dependency resolution and preview features..."
        mvn clean package -DskipTests -U -Dmaven.compiler.release=17 -Dmaven.compiler.enablePreview=true
    fi
    
    # Restore the original pom.xml
    mv pom.xml.bak pom.xml
}

# Deploy the server
deploy_server() {
    log "Deploying the server..."
    cp /home/ubuntu/$SIGNAL_SERVER_DIR/service/target/service-*.jar $DEPLOY_DIR/signal-server.jar
    
    # Create sample config if it doesn't exist
    if [ ! -f "/home/ubuntu/$SIGNAL_SERVER_DIR/service/config/sample.yml" ]; then
        mkdir -p /home/ubuntu/$SIGNAL_SERVER_DIR/service/config
        cat > /home/ubuntu/$SIGNAL_SERVER_DIR/service/config/sample.yml << EOF
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
    
    cp /home/ubuntu/$SIGNAL_SERVER_DIR/service/config/sample.yml $CONFIG_DIR/config.yml
    
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
    log "Starting Signal Server deployment (service module only)..."
    
    create_directories
    install_dependencies
    clone_or_update_repo
    build_service_module
    deploy_server
    configure_nginx
    start_server
    check_server_status
    
    log "Signal Server deployment complete!"
}

# Run the main function
main
