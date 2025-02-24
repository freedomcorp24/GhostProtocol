# GhostProtocol Setup Guide

## Overview
GhostProtocol is a secure messaging platform built on Signal's foundation with enhanced features including cryptocurrency payments, screen sharing, and advanced security mechanisms.

## Prerequisites
- Java 17 or higher
- Maven 3.9.9 or higher
- Node.js 16 or higher
- AWS Account with KMS and S3 access
- PostgreSQL 13 or higher

## Quick Start
1. Clone the repository:
```bash
git clone https://github.com/freedomcorp24/GhostProtocolv1.git
cd GhostProtocolv1
```

2. Configure AWS credentials:
```bash
aws configure
```

3. Set up the database:
```bash
psql -U postgres -f setup/db/init.sql
```

4. Build and run the server:
```bash
mvn clean install
java -jar service/target/ghostprotocol-server.jar
```

5. Start the web client:
```bash
cd web
npm install
npm start
```

## Component Setup
- [Server Configuration](../configuration/server.md)
- [Web Client Setup](../configuration/web-client.md)
- [Mobile Client Setup](../configuration/mobile-clients.md)
- [Security Configuration](../security/README.md)

## Environment Variables
```env
GHOST_DB_URL=jdbc:postgresql://localhost:5432/ghostprotocol
GHOST_AWS_REGION=us-east-2
GHOST_KMS_KEY_ID=your-kms-key-id
GHOST_S3_BUCKET=your-s3-bucket
```

## Health Checks
- Server: `http://localhost:8080/health`
- WebSocket: `ws://localhost:8080/ws`
- Admin Panel: `http://localhost:8080/admin`
