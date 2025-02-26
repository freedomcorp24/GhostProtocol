#!/usr/bin/env python3
import sys
import json

# Read the JSON data from stdin
data = json.load(sys.stdin)

# Extract the token
token = data.get('token')

if token:
    print(token)
else:
    print("Token not found in response")
