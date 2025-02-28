# GhostProtocol Test URLs

This document contains the URLs for accessing the GhostProtocol test environments for web, iOS, and Android functionality.

## Test Environment URLs

- **Web Test Environment**: http://localhost:3000
- **iOS Test Environment**: http://localhost:3001
- **Android Test Environment**: http://localhost:3002

## Accessing the Test Environments

To access the test environments, you need to start the test environment servers and then navigate to the URLs in your browser.

### Starting the Test Environment Servers

1. Navigate to the GhostProtocol directory:
   ```bash
   cd /home/ubuntu/GhostProtocol
   ```

2. Start the test environment servers:
   ```bash
   cd test_environments
   node server/server.js
   ```

3. The servers will start on the following ports:
   - Web: 3000
   - iOS: 3001
   - Android: 3002

4. You should see output similar to the following:
   ```
   All test environment servers are running
   Web server running on port 3000
   iOS server running on port 3001
   Android server running on port 3002
   ```

### Exposing the Test Environments

To make the test environments accessible from outside your local machine, you can use the following methods:

1. **SSH Tunnel**: If you have SSH access to the machine, you can create an SSH tunnel to access the test environments:
   ```bash
   ssh -L 3000:localhost:3000 -L 3001:localhost:3001 -L 3002:localhost:3002 user@hostname
   ```

2. **Ngrok**: You can use Ngrok to create temporary public URLs for your test environments:
   ```bash
   # Install Ngrok if not already installed
   sudo apt-get install -y ngrok

   # Expose the web test environment
   ngrok http 3000

   # Expose the iOS test environment
   ngrok http 3001

   # Expose the Android test environment
   ngrok http 3002
   ```

3. **AWS Load Balancer**: If you're running the test environments in AWS, you can set up an Application Load Balancer to expose the test environments:
   - Create a target group for each test environment
   - Create a load balancer with listeners for each target group
   - Configure the security groups to allow traffic to the load balancer
   - Use the load balancer DNS name to access the test environments

## Test Environment Features

Each test environment demonstrates the following GhostProtocol components:

- **Authentication System**:
  - User Registration
  - User Login
  - Password Reset

- **Secure Storage (Vault)**:
  - Password Storage
  - Contact Information Storage
  - File Storage
  - Private Notes Storage

- **Cryptocurrency Integration**:
  - Bitcoin Wallet
  - Monero Wallet
  - USDT Wallet
  - Transaction History

- **Premium Tiers and Subscription System**:
  - Free Tier (10GB Storage)
  - Premium Tier (100GB Storage)
  - Enterprise Tier (1TB Storage)
  - Subscription Management

- **Admin Panel**:
  - User Management
  - Premium Account Management
  - Trial Period Management

## Notes

These test environments are for demonstration purposes only and do not include actual functionality. They are meant to show how the GhostProtocol components would be implemented using Signal's native implementation.

To implement actual functionality, you would need to:

1. Build the Signal server with the GhostProtocol components
2. Deploy the server to AWS
3. Build the iOS and Android apps with the GhostProtocol components
4. Deploy the apps to test devices

## Next Steps

1. Start the test environment servers
2. Access the test environments using the URLs
3. Verify that all components are displayed correctly
4. Proceed with implementing actual functionality
