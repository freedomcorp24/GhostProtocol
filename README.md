# GhostProtocol

GhostProtocol is a secure messaging platform built on Signal's native Java/Maven implementation.

## Components

- **Authentication System**: JWT token generation and validation
- **Secure Storage (Vault)**: Password storage, contact information storage, file storage, and private notes storage
- **Cryptocurrency Integration**: Bitcoin, Monero, and USDT wallet functionality
- **Premium Tiers and Subscription System**: Free tier, premium tier, and enterprise tier
- **Admin Panel**: User management, premium account management, and trial period management

## Documentation

- [GhostProtocol Components](docs/GHOST_PROTOCOL_COMPONENTS.md)
- [Implementation Status](docs/IMPLEMENTATION_STATUS.md)
- [Mobile App Test Plan](docs/testing/MOBILE_APP_TEST_PLAN.md)
- [Production Deployment Plan](docs/deployment/PRODUCTION_DEPLOYMENT_PLAN.md)
- [Testing Issues Summary](docs/testing/TESTING_ISSUES_SUMMARY.md)
- [AWS Test Fixes](docs/deployment/AWS_TEST_FIXES.md)
- [Signal Server Integration](docs/deployment/SIGNAL_SERVER_INTEGRATION.md)

## Scripts

### Deployment Scripts

- [Deploy GhostProtocol](scripts/deployment/deploy_ghost_protocol.sh)
- [Deploy GhostProtocol Minimal](scripts/deployment/deploy_ghost_protocol_minimal.sh)
- [Deploy GhostProtocol Simple](scripts/deployment/deploy_ghost_protocol_simple.sh)
- [Deploy GhostProtocol Standalone](scripts/deployment/deploy_ghost_protocol_standalone.sh)
- [Deploy Production](scripts/deployment/deploy_production.sh)
- [Deploy Service Only](scripts/deployment/deploy_service_only.sh)
- [Deploy Signal Server](scripts/deployment/deploy_signal_server.sh)
- [Create GhostProtocol JAR](scripts/deployment/create_ghost_protocol_jar.sh)

### Testing Scripts

- [Verify GhostProtocol](scripts/testing/verify_ghost_protocol.sh)
- [Test iOS App](scripts/testing/test_ios_app.sh)
- [Test Android App](scripts/testing/test_android_app.sh)
- [Verify Feature Parity](scripts/testing/verify_feature_parity.sh)
- [Test Mobile Apps](scripts/testing/test_mobile_apps.sh)

## Test URLs

- Web: http://ghostprotocol-web.example.com
- iOS: http://ghostprotocol-ios.example.com
- Android: http://ghostprotocol-android.example.com
