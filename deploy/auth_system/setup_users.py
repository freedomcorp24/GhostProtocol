import json
import os
import hashlib
import datetime

# Create the data directory if it doesn't exist
os.makedirs('static/data', exist_ok=True)

# Create default admin user
default_users = {
    "users": [
        {
            "id": "1",
            "username": "admin",
            "password_hash": hashlib.sha256("admin123".encode()).hexdigest(),
            "role": "super_admin",
            "created_at": datetime.datetime.now().isoformat(),
            "status": "active",
            "premium": True,
            "subscription_tier": "enterprise",
            "storage_used": 0,
            "storage_limit": 100000000000  # 100GB
        }
    ]
}

# Save to users.json
with open('static/data/users.json', 'w') as f:
    json.dump(default_users, f, indent=2)

print("Users data initialized successfully.")
