#!/bin/bash

# GhostProtocol Standalone Deployment Script for AWS Test Environment
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

# Create a standalone JAR for GhostProtocol components
create_standalone_jar() {
    log "Creating a standalone JAR for GhostProtocol components..."
    
    # Create a temporary directory for the standalone JAR
    mkdir -p /tmp/ghostprotocol-standalone
    
    # Copy the GhostProtocol components to the temporary directory
    cp -r $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol /tmp/ghostprotocol-standalone/
    
    # Create a pom.xml file for the standalone JAR
    cat > /tmp/ghostprotocol-standalone/pom.xml << EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.ghostprotocol</groupId>
    <artifactId>ghostprotocol-standalone</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- Dropwizard -->
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-core</artifactId>
            <version>2.1.4</version>
        </dependency>
        
        <!-- DynamoDB -->
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>dynamodb</artifactId>
            <version>2.17.102</version>
        </dependency>
        
        <!-- Redis -->
        <dependency>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
            <version>6.1.5.RELEASE</version>
        </dependency>
        
        <!-- Web3j for Ethereum -->
        <dependency>
            <groupId>org.web3j</groupId>
            <artifactId>core</artifactId>
            <version>4.9.4</version>
        </dependency>
        
        <!-- Bitcoin -->
        <dependency>
            <groupId>org.bitcoinj</groupId>
            <artifactId>bitcoinj-core</artifactId>
            <version>0.16.1</version>
        </dependency>
        
        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.2</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.2</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.2</version>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Guava -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>31.1-jre</version>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>org.ghostprotocol.GhostProtocolApplication</mainClass>
                                </transformer>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
EOF
    
    # Create a main application class
    mkdir -p /tmp/ghostprotocol-standalone/src/main/java/org/ghostprotocol
    cat > /tmp/ghostprotocol-standalone/src/main/java/org/ghostprotocol/GhostProtocolApplication.java << EOF
package org.ghostprotocol;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.ghostprotocol.textsecuregcm.GhostProtocolConfiguration;
import org.ghostprotocol.textsecuregcm.auth.GhostProtocolAuthModule;
import org.ghostprotocol.textsecuregcm.vault.VaultModule;
import org.ghostprotocol.textsecuregcm.crypto.CryptoModule;
import org.ghostprotocol.textsecuregcm.premium.PremiumModule;
import org.ghostprotocol.textsecuregcm.admin.AdminModule;

public class GhostProtocolApplication extends Application<GhostProtocolConfiguration> {
    public static void main(String[] args) throws Exception {
        new GhostProtocolApplication().run(args);
    }

    @Override
    public String getName() {
        return "ghost-protocol";
    }

    @Override
    public void initialize(Bootstrap<GhostProtocolConfiguration> bootstrap) {
        // Initialize bootstrap
    }

    @Override
    public void run(GhostProtocolConfiguration configuration, Environment environment) {
        // Register modules
        GhostProtocolAuthModule authModule = new GhostProtocolAuthModule();
        VaultModule vaultModule = new VaultModule();
        CryptoModule cryptoModule = new CryptoModule();
        PremiumModule premiumModule = new PremiumModule();
        AdminModule adminModule = new AdminModule();
        
        // Register resources
        environment.jersey().register(authModule);
        environment.jersey().register(vaultModule);
        environment.jersey().register(cryptoModule);
        environment.jersey().register(premiumModule);
        environment.jersey().register(adminModule);
    }
}
EOF
    
    # Create a configuration class
    mkdir -p /tmp/ghostprotocol-standalone/src/main/java/org/ghostprotocol/textsecuregcm
    cp $SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol/textsecuregcm/GhostProtocolConfiguration.java /tmp/ghostprotocol-standalone/src/main/java/org/ghostprotocol/textsecuregcm/
    
    # Build the standalone JAR
    cd /tmp/ghostprotocol-standalone
    mvn clean package -DskipTests
    
    # Copy the standalone JAR to the deployment directory
    cp target/ghostprotocol-standalone-1.0-SNAPSHOT.jar $DEPLOY_DIR/ghostprotocol-standalone.jar
    
    log "Standalone JAR created successfully"
}

# Create a configuration file for the standalone JAR
create_configuration_file() {
    log "Creating a configuration file for the standalone JAR..."
    
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

# Create a systemd service file
create_systemd_service_file() {
    log "Creating a systemd service file..."
    
    cat > /etc/systemd/system/$SERVICE_NAME.service << EOF
[Unit]
Description=GhostProtocol Signal Server
After=network.target

[Service]
User=root
WorkingDirectory=$DEPLOY_DIR
ExecStart=/usr/bin/java -jar $DEPLOY_DIR/ghostprotocol-standalone.jar server $CONFIG_DIR/config.yml
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

# Start the service
start_service() {
    log "Starting the service..."
    
    systemctl daemon-reload
    systemctl enable $SERVICE_NAME
    systemctl start $SERVICE_NAME
    
    log "Service started successfully"
}

# Check the service status
check_service_status() {
    log "Checking the service status..."
    
    systemctl status $SERVICE_NAME
    
    log "Service status checked successfully"
}

# Main function
main() {
    log "Starting GhostProtocol deployment..."
    
    create_directories
    install_dependencies
    clone_or_update_repository
    create_standalone_jar
    create_configuration_file
    create_systemd_service_file
    configure_nginx
    start_service
    check_service_status
    
    log "GhostProtocol deployment complete!"
}

# Run the main function
main
