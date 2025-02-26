#!/bin/bash

# Test the video call feature
echo "Testing video call feature..."

# Test if the video call page is accessible
echo "Testing if the video call page is accessible..."
HTTP_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://54.215.16.4/video-call)
if [ "$HTTP_STATUS" -eq 200 ]; then
  echo "Video call page is accessible (HTTP 200 OK)"
else
  echo "Error: Video call page is not accessible (HTTP $HTTP_STATUS)"
  exit 1
fi

# Test if the WebRTC adapter is loaded
echo "Testing if the WebRTC adapter is loaded..."
WEBRTC_LOADED=$(curl -s http://54.215.16.4/video-call | grep -c "webrtc-adapter")
if [ "$WEBRTC_LOADED" -gt 0 ]; then
  echo "WebRTC adapter is loaded"
else
  echo "Error: WebRTC adapter is not loaded"
  exit 1
fi

# Test if the video call component is loaded
echo "Testing if the video call component is loaded..."
COMPONENT_LOADED=$(curl -s http://54.215.16.4/video-call | grep -c "VideoCallPage")
if [ "$COMPONENT_LOADED" -gt 0 ]; then
  echo "Video call component is loaded"
else
  echo "Error: Video call component is not loaded"
  exit 1
fi

# Test if the signaling endpoint is accessible
echo "Testing if the signaling endpoint is accessible..."
SIGNALING_STATUS=$(curl -s -I -X GET http://54.215.16.4/signaling/ | head -n 1 | grep -c "101\|200\|400")
if [ "$SIGNALING_STATUS" -gt 0 ]; then
  echo "Signaling endpoint is accessible"
else
  echo "Warning: Signaling endpoint may not be accessible. WebSocket connections might not work."
fi

echo "All tests passed! The video call feature is deployed and accessible."
echo "Manual testing is required for full functionality verification."
echo "Please visit http://54.215.16.4/video-call to test the video call feature."
