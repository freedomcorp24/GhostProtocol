#!/bin/bash
# Script to verify that all URLs are accessible

EC2_PUBLIC_IP="54.215.16.4"

echo "Verifying GhostProtocol URLs..."
echo "================================"

# Verify web client URL
echo "Checking web client URL (http://$EC2_PUBLIC_IP/)..."
WEB_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://$EC2_PUBLIC_IP/)
if [ "$WEB_STATUS" == "200" ]; then
  echo "✅ Web client URL is accessible (HTTP $WEB_STATUS)"
else
  echo "❌ Web client URL is not accessible (HTTP $WEB_STATUS)"
fi

# Verify API URL
echo "Checking API URL (http://$EC2_PUBLIC_IP/api/health)..."
API_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://$EC2_PUBLIC_IP/api/health)
if [ "$API_STATUS" == "200" ]; then
  echo "✅ API URL is accessible (HTTP $API_STATUS)"
else
  echo "❌ API URL is not accessible (HTTP $API_STATUS)"
fi

# Verify admin URL
echo "Checking admin URL (http://$EC2_PUBLIC_IP/admin/)..."
ADMIN_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://$EC2_PUBLIC_IP/admin/)
if [ "$ADMIN_STATUS" == "200" ]; then
  echo "✅ Admin URL is accessible (HTTP $ADMIN_STATUS)"
else
  echo "❌ Admin URL is not accessible (HTTP $ADMIN_STATUS)"
fi

echo "================================"
if [ "$WEB_STATUS" == "200" ] && [ "$API_STATUS" == "200" ] && [ "$ADMIN_STATUS" == "200" ]; then
  echo "All URLs are accessible! ✅"
else
  echo "Some URLs are not accessible. ❌"
fi
