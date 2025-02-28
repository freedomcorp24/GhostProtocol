# GhostProtocol Components Documentation

This document provides an overview of the GhostProtocol components that have been implemented using Signal's native Java/Maven implementation.

## Overview
- GhostProtocol backend features have been implemented using Signal's native Java/Maven-based server with Dropwizard framework
- All components have been migrated to Signal's native code
- Deployment scripts have been created for the AWS test environment

## Features Implemented in Signal's Native Code
The following features have been implemented using Signal's native Java code:

1. **Authentication System** (`org.ghostprotocol.textsecuregcm.auth` package)
   - Username-based authentication
   - JWT token generation
   - Master admin account

2. **Secure Storage (Vault)** (`org.ghostprotocol.textsecuregcm.vault` package)
   - Password storage
   - Contact information storage
   - File storage
   - Private notes storage

3. **Cryptocurrency Integration** (`org.ghostprotocol.textsecuregcm.crypto` package)
   - Bitcoin support
   - Monero support
   - USDT support
   - Wallet management
   - Transaction processing

4. **Premium Tiers and Subscription System** (`org.ghostprotocol.textsecuregcm.premium` package)
   - Free tier (10GB storage)
   - Premium tier (100GB storage)
   - Enterprise tier (1TB storage)
   - Trial period management

5. **Admin Panel** (`org.ghostprotocol.textsecuregcm.admin` package)
   - User management
   - Premium account management
   - Trial period management
   - Subscription tier management

## Implementation Details
- All components are implemented using Signal's native Java code
- The implementation follows Signal's architectural patterns and coding standards
- DynamoDB is used for persistent storage
- Redis is used for caching and messaging
- The implementation is fully compatible with Signal's client applications

## Integration with Signal Server
To integrate the GhostProtocol components with Signal's server:

1. Add the GhostProtocol packages to Signal's server codebase
2. Register the GhostProtocol modules in Signal's server application
3. Configure the GhostProtocol components in Signal's server configuration
4. Deploy the integrated server to the AWS test environment

## Deployment Instructions
To deploy the integrated server to the AWS test environment:

1. Clone the repository:
   ```
   git clone https://github.com/freedomcorp24/SignalClone.git
   ```

2. Build the server:
   ```
   cd SignalClone/Signal-Server
   mvn clean package
   ```

3. Deploy the server:
   ```
   java -jar service/target/service-*.jar server config/config.yml
   ```

## Configuration
The Signal server is configured with the following settings:
- API Port: 8080
- Admin Port: 8081
- Log Directory: /var/log/signal-server
- Config Directory: /etc/signal-server
- Deploy Directory: /opt/signal-server

## Mobile App Testing
The mobile app testing script (`scripts/test_mobile_apps.sh`) tests the following:
- Android app integration with Signal server
- iOS app integration with Signal server
- Feature parity between Android and iOS apps
- Platform-specific features (push notifications)
- Core features (authentication, video call, secure messaging, vault, cryptocurrency, premium tiers)
- Builds and makes available APK and IPA files for direct testing

## Next Steps
1. Test all features in the AWS test environment
2. Fix any issues found during testing
3. Prepare for production deployment
4. Deploy to production
