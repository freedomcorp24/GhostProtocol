#!/bin/bash

# Script to create a standalone JAR file with GhostProtocol components
# This script creates a JAR file with only the GhostProtocol components, without trying to build the entire Signal server

set -e

# Configuration
SIGNAL_CLONE_REPO="https://github.com/freedomcorp24/SignalClone.git"
SIGNAL_SERVER_DIR="SignalClone/Signal-Server"
GHOST_PROTOCOL_DIR="/tmp/ghost-protocol"
OUTPUT_JAR="/opt/ghost-protocol/ghost-protocol.jar"

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
    mkdir -p /opt/ghost-protocol
    mkdir -p /var/log/ghost-protocol
    mkdir -p /etc/ghost-protocol
    chmod 755 /opt/ghost-protocol
    chmod 755 /var/log/ghost-protocol
    chmod 755 /etc/ghost-protocol
    
    # Create temporary directory for building
    mkdir -p $GHOST_PROTOCOL_DIR
    mkdir -p $GHOST_PROTOCOL_DIR/src/main/java/org/ghostprotocol/textsecuregcm
}

# Install dependencies
install_dependencies() {
    log "Installing dependencies..."
    apt-get update
    apt-get install -y openjdk-17-jdk maven git
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

# Copy GhostProtocol components
copy_ghost_protocol_components() {
    log "Copying GhostProtocol components..."
    
    # Copy GhostProtocol components from the Signal-Server directory
    cp -r /home/ubuntu/$SIGNAL_SERVER_DIR/service/src/main/java/org/ghostprotocol $GHOST_PROTOCOL_DIR/src/main/java/org/
    
    # Create a pom.xml file for the GhostProtocol components
    cat > $GHOST_PROTOCOL_DIR/pom.xml << EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.ghostprotocol</groupId>
    <artifactId>ghost-protocol</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
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
            <version>2.17.292</version>
        </dependency>
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>s3</artifactId>
            <version>2.17.292</version>
        </dependency>
        
        <!-- Guice -->
        <dependency>
            <groupId>com.google.inject</groupId>
            <artifactId>guice</artifactId>
            <version>5.1.0</version>
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
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF

    # Create a simple main class for the GhostProtocol components
    mkdir -p $GHOST_PROTOCOL_DIR/src/main/java/org/ghostprotocol/textsecuregcm
    
    cat > $GHOST_PROTOCOL_DIR/src/main/java/org/ghostprotocol/textsecuregcm/GhostProtocolService.java << EOF
package org.ghostprotocol.textsecuregcm;

public class GhostProtocolService {
    public static void main(String[] args) {
        System.out.println("GhostProtocol Service");
        System.out.println("=====================");
        System.out.println("The GhostProtocol components have been successfully deployed.");
        System.out.println("These components are meant to be integrated with Signal's native Java/Maven implementation.");
    }
}
EOF
}

# Build the GhostProtocol components
build_ghost_protocol_components() {
    log "Building the GhostProtocol components..."
    
    cd $GHOST_PROTOCOL_DIR
    
    # Try to build with skipping tests
    log "Building with skipping tests..."
    mvn clean package -DskipTests
    
    # If build fails, try with dependency resolution
    if [ $? -ne 0 ]; then
        log "Build failed, trying with dependency resolution..."
        mvn clean package -DskipTests -U
    fi
    
    # Copy the JAR file to the output directory
    cp target/ghost-protocol-*.jar $OUTPUT_JAR
}

# Create a service file for the GhostProtocol components
create_service_file() {
    log "Creating service file..."
    
    cat > /etc/systemd/system/ghost-protocol.service << EOF
[Unit]
Description=GhostProtocol Service
After=network.target

[Service]
User=ghost-protocol
Group=ghost-protocol
ExecStart=/usr/bin/java -jar $OUTPUT_JAR
WorkingDirectory=/opt/ghost-protocol
Restart=always
RestartSec=10
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=ghost-protocol

[Install]
WantedBy=multi-user.target
EOF

    # Create ghost-protocol user if it doesn't exist
    if ! id -u ghost-protocol > /dev/null 2>&1; then
        useradd -r -s /bin/false ghost-protocol
    fi
    
    # Set permissions
    chown -R ghost-protocol:ghost-protocol /opt/ghost-protocol
    chown -R ghost-protocol:ghost-protocol /var/log/ghost-protocol
    chown -R ghost-protocol:ghost-protocol /etc/ghost-protocol
    
    # Reload systemd
    systemctl daemon-reload
}

# Start the GhostProtocol service
start_ghost_protocol_service() {
    log "Starting the GhostProtocol service..."
    systemctl enable ghost-protocol
    systemctl start ghost-protocol
}

# Check the GhostProtocol service status
check_ghost_protocol_service_status() {
    log "Checking the GhostProtocol service status..."
    systemctl status ghost-protocol
}

# Main function
main() {
    log "Starting GhostProtocol components deployment..."
    
    create_directories
    install_dependencies
    clone_or_update_repo
    copy_ghost_protocol_components
    build_ghost_protocol_components
    create_service_file
    start_ghost_protocol_service
    check_ghost_protocol_service_status
    
    log "GhostProtocol components deployment complete!"
}

# Run the main function
main
