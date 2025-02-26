#!/bin/bash

# Test the video call feature
echo "Testing video call feature..."

# Test the video call page
echo "Testing video call page..."
curl -s -I http://54.215.16.4/video-call | head -n 1

# Test the navigation bar
echo "Testing navigation bar..."
curl -s -I http://54.215.16.4/ | head -n 1

echo "Testing complete!"
