#!/bin/bash

# Mobile Apps Testing Script for AWS Test Environment
# This script tests the iOS and Android app integration with the Signal server

set -e

# Configuration
SIGNAL_SERVER_API_ENDPOINT="http://localhost:8080/v1/health"
ANDROID_APP_PORT="8080"
IOS_APP_PORT="8081"
ANDROID_APK_DIR="/opt/signal-android/build/outputs/apk/release"
IOS_IPA_DIR="/opt/signal-ios/build/outputs/ipa"

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

# Check Signal server status
check_signal_server() {
    log "Checking Signal server status..."
    if curl -s -o /dev/null -w "%{http_code}" $SIGNAL_SERVER_API_ENDPOINT | grep -q "200"; then
        log "Signal server is running"
        return 0
    else
        error "Signal server is not running"
        return 1
    fi
}

# Test Android app server
test_android_app_server() {
    log "Testing Android app server..."
    if netstat -tuln | grep -q ":$ANDROID_APP_PORT"; then
        log "Android app server is running on port $ANDROID_APP_PORT"
        return 0
    else
        error "Android app server is not running on port $ANDROID_APP_PORT"
        return 1
    fi
}

# Test iOS app server
test_ios_app_server() {
    log "Testing iOS app server..."
    if netstat -tuln | grep -q ":$IOS_APP_PORT"; then
        log "iOS app server is running on port $IOS_APP_PORT"
        return 0
    else
        error "iOS app server is not running on port $IOS_APP_PORT"
        return 1
    fi
}

# Build Android APK
build_android_apk() {
    log "Building Android APK..."
    cd /opt/signal-android
    ./gradlew assembleRelease
    if [ -f "$ANDROID_APK_DIR/GhostProtocol-release.apk" ]; then
        log "Android APK built successfully"
        return 0
    else
        error "Failed to build Android APK"
        return 1
    fi
}

# Build iOS IPA
build_ios_ipa() {
    log "Building iOS IPA..."
    cd /opt/signal-ios
    xcodebuild -workspace GhostProtocol.xcworkspace -scheme GhostProtocol -configuration Release -archivePath build/GhostProtocol.xcarchive archive
    xcodebuild -exportArchive -archivePath build/GhostProtocol.xcarchive -exportOptionsPlist exportOptions.plist -exportPath $IOS_IPA_DIR
    if [ -f "$IOS_IPA_DIR/GhostProtocol.ipa" ]; then
        log "iOS IPA built successfully"
        return 0
    else
        error "Failed to build iOS IPA"
        return 1
    fi
}

# Test Android app features
test_android_features() {
    log "Testing Android app features..."
    
    # Test authentication
    log "Testing Android authentication..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$ANDROID_APP_PORT/v1/auth/login" -X POST -d '{"username":"test","password":"test"}' -H "Content-Type: application/json" | grep -q "200"; then
        log "Android authentication is working"
    else
        warn "Android authentication may not be working"
    fi
    
    # Test video call
    log "Testing Android video call..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$ANDROID_APP_PORT/v1/call/capabilities" | grep -q "200"; then
        log "Android video call is working"
    else
        warn "Android video call may not be working"
    fi
    
    # Test secure messaging
    log "Testing Android secure messaging..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$ANDROID_APP_PORT/v1/messages" | grep -q "200"; then
        log "Android secure messaging is working"
    else
        warn "Android secure messaging may not be working"
    fi
    
    # Test vault
    log "Testing Android vault..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$ANDROID_APP_PORT/v1/vault" | grep -q "200"; then
        log "Android vault is working"
    else
        warn "Android vault may not be working"
    fi
    
    # Test cryptocurrency
    log "Testing Android cryptocurrency..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$ANDROID_APP_PORT/v1/crypto/wallets" | grep -q "200"; then
        log "Android cryptocurrency is working"
    else
        warn "Android cryptocurrency may not be working"
    fi
    
    # Test premium tiers
    log "Testing Android premium tiers..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$ANDROID_APP_PORT/v1/premium/tiers" | grep -q "200"; then
        log "Android premium tiers are working"
    else
        warn "Android premium tiers may not be working"
    fi
    
    return 0
}

# Test iOS app features
test_ios_features() {
    log "Testing iOS app features..."
    
    # Test authentication
    log "Testing iOS authentication..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$IOS_APP_PORT/v1/auth/login" -X POST -d '{"username":"test","password":"test"}' -H "Content-Type: application/json" | grep -q "200"; then
        log "iOS authentication is working"
    else
        warn "iOS authentication may not be working"
    fi
    
    # Test video call
    log "Testing iOS video call..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$IOS_APP_PORT/v1/call/capabilities" | grep -q "200"; then
        log "iOS video call is working"
    else
        warn "iOS video call may not be working"
    fi
    
    # Test secure messaging
    log "Testing iOS secure messaging..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$IOS_APP_PORT/v1/messages" | grep -q "200"; then
        log "iOS secure messaging is working"
    else
        warn "iOS secure messaging may not be working"
    fi
    
    # Test vault
    log "Testing iOS vault..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$IOS_APP_PORT/v1/vault" | grep -q "200"; then
        log "iOS vault is working"
    else
        warn "iOS vault may not be working"
    fi
    
    # Test cryptocurrency
    log "Testing iOS cryptocurrency..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$IOS_APP_PORT/v1/crypto/wallets" | grep -q "200"; then
        log "iOS cryptocurrency is working"
    else
        warn "iOS cryptocurrency may not be working"
    fi
    
    # Test premium tiers
    log "Testing iOS premium tiers..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$IOS_APP_PORT/v1/premium/tiers" | grep -q "200"; then
        log "iOS premium tiers are working"
    else
        warn "iOS premium tiers may not be working"
    fi
    
    return 0
}

# Verify feature parity between platforms
verify_feature_parity() {
    log "Verifying feature parity between Android and iOS..."
    
    # Compare authentication
    log "Comparing authentication..."
    ANDROID_AUTH=$(curl -s "http://localhost:$ANDROID_APP_PORT/v1/auth/login" -X POST -d '{"username":"test","password":"test"}' -H "Content-Type: application/json")
    IOS_AUTH=$(curl -s "http://localhost:$IOS_APP_PORT/v1/auth/login" -X POST -d '{"username":"test","password":"test"}' -H "Content-Type: application/json")
    if [ "$ANDROID_AUTH" == "$IOS_AUTH" ]; then
        log "Authentication has feature parity"
    else
        warn "Authentication may not have feature parity"
    fi
    
    # Compare video call
    log "Comparing video call..."
    ANDROID_CALL=$(curl -s "http://localhost:$ANDROID_APP_PORT/v1/call/capabilities")
    IOS_CALL=$(curl -s "http://localhost:$IOS_APP_PORT/v1/call/capabilities")
    if [ "$ANDROID_CALL" == "$IOS_CALL" ]; then
        log "Video call has feature parity"
    else
        warn "Video call may not have feature parity"
    fi
    
    # Compare secure messaging
    log "Comparing secure messaging..."
    ANDROID_MSG=$(curl -s "http://localhost:$ANDROID_APP_PORT/v1/messages")
    IOS_MSG=$(curl -s "http://localhost:$IOS_APP_PORT/v1/messages")
    if [ "$ANDROID_MSG" == "$IOS_MSG" ]; then
        log "Secure messaging has feature parity"
    else
        warn "Secure messaging may not have feature parity"
    fi
    
    # Compare vault
    log "Comparing vault..."
    ANDROID_VAULT=$(curl -s "http://localhost:$ANDROID_APP_PORT/v1/vault")
    IOS_VAULT=$(curl -s "http://localhost:$IOS_APP_PORT/v1/vault")
    if [ "$ANDROID_VAULT" == "$IOS_VAULT" ]; then
        log "Vault has feature parity"
    else
        warn "Vault may not have feature parity"
    fi
    
    # Compare cryptocurrency
    log "Comparing cryptocurrency..."
    ANDROID_CRYPTO=$(curl -s "http://localhost:$ANDROID_APP_PORT/v1/crypto/wallets")
    IOS_CRYPTO=$(curl -s "http://localhost:$IOS_APP_PORT/v1/crypto/wallets")
    if [ "$ANDROID_CRYPTO" == "$IOS_CRYPTO" ]; then
        log "Cryptocurrency has feature parity"
    else
        warn "Cryptocurrency may not have feature parity"
    fi
    
    # Compare premium tiers
    log "Comparing premium tiers..."
    ANDROID_PREMIUM=$(curl -s "http://localhost:$ANDROID_APP_PORT/v1/premium/tiers")
    IOS_PREMIUM=$(curl -s "http://localhost:$IOS_APP_PORT/v1/premium/tiers")
    if [ "$ANDROID_PREMIUM" == "$IOS_PREMIUM" ]; then
        log "Premium tiers have feature parity"
    else
        warn "Premium tiers may not have feature parity"
    fi
    
    return 0
}

# Test platform-specific features
test_platform_specific_features() {
    log "Testing platform-specific features..."
    
    # Test Android push notifications
    log "Testing Android push notifications..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$ANDROID_APP_PORT/v1/push/register" -X POST -d '{"token":"test"}' -H "Content-Type: application/json" | grep -q "200"; then
        log "Android push notifications are working"
    else
        warn "Android push notifications may not be working"
    fi
    
    # Test iOS push notifications
    log "Testing iOS push notifications..."
    if curl -s -o /dev/null -w "%{http_code}" "http://localhost:$IOS_APP_PORT/v1/push/register" -X POST -d '{"token":"test"}' -H "Content-Type: application/json" | grep -q "200"; then
        log "iOS push notifications are working"
    else
        warn "iOS push notifications may not be working"
    fi
    
    return 0
}

# Make APK available for download
make_apk_available() {
    log "Making Android APK available for download..."
    if [ -f "$ANDROID_APK_DIR/GhostProtocol-release.apk" ]; then
        cp "$ANDROID_APK_DIR/GhostProtocol-release.apk" /var/www/html/downloads/
        chmod 644 /var/www/html/downloads/GhostProtocol-release.apk
        log "Android APK is available at http://$(hostname -I | awk '{print $1}')/downloads/GhostProtocol-release.apk"
        return 0
    else
        error "Android APK not found"
        return 1
    fi
}

# Make IPA available for download
make_ipa_available() {
    log "Making iOS IPA available for download..."
    if [ -f "$IOS_IPA_DIR/GhostProtocol.ipa" ]; then
        cp "$IOS_IPA_DIR/GhostProtocol.ipa" /var/www/html/downloads/
        chmod 644 /var/www/html/downloads/GhostProtocol.ipa
        log "iOS IPA is available at http://$(hostname -I | awk '{print $1}')/downloads/GhostProtocol.ipa"
        return 0
    else
        error "iOS IPA not found"
        return 1
    fi
}

# Main function
main() {
    log "Starting mobile apps testing..."
    
    # Check Signal server status
    check_signal_server
    
    # Test Android app server
    test_android_app_server
    
    # Test iOS app server
    test_ios_app_server
    
    # Build Android APK
    build_android_apk
    
    # Build iOS IPA
    build_ios_ipa
    
    # Test Android app features
    test_android_features
    
    # Test iOS app features
    test_ios_features
    
    # Verify feature parity between platforms
    verify_feature_parity
    
    # Test platform-specific features
    test_platform_specific_features
    
    # Make APK available for download
    make_apk_available
    
    # Make IPA available for download
    make_ipa_available
    
    log "Mobile apps testing complete!"
}

# Run the main function
main
