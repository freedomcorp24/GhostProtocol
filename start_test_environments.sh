#!/bin/bash

# Start the test environment servers
cd "$(dirname "$0")/test_environments"
node server/server.js
