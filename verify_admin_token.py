#!/usr/bin/env python3
import sys
import json
import jwt

# Read the token from stdin
token = sys.stdin.read().strip()

try:
    # Decode the token without verification (since we don't have the secret key)
    decoded = jwt.decode(token, options={"verify_signature": False})
    
    # Print the decoded token
    print(json.dumps(decoded, indent=2))
    
    # Check if the user is an admin
    if decoded.get('role') in ['admin', 'super_admin']:
        print("\nToken belongs to an admin user")
    else:
        print("\nToken belongs to a regular user")
except Exception as e:
    print(f"Error decoding token: {e}")
