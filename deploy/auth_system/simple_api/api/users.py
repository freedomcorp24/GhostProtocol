import json
import uuid
from datetime import datetime

# Mock database for users
users = {
    "1": {
        "id": "1",
        "username": "admin",
        "email": "admin@ghostprotocol.com",
        "created_at": "2025-01-01T00:00:00Z",
        "role": "super_admin",
        "status": "active",
        "subscription": "business"
    },
    "2": {
        "id": "2",
        "username": "user1",
        "email": "user1@example.com",
        "created_at": "2025-01-02T00:00:00Z",
        "role": "user",
        "status": "active",
        "subscription": "free"
    },
    "3": {
        "id": "3",
        "username": "premium_user",
        "email": "premium@example.com",
        "created_at": "2025-01-03T00:00:00Z",
        "role": "user",
        "status": "active",
        "subscription": "premium"
    }
}

def get_users():
    return {"users": list(users.values()), "total": len(users)}

def get_user(user_id):
    if user_id in users:
        return {"user": users[user_id]}
    return {"error": "User not found"}, 404

def create_user(data):
    user_id = str(uuid.uuid4())
    new_user = {
        "id": user_id,
        "username": data.get("username"),
        "email": data.get("email"),
        "created_at": datetime.utcnow().isoformat() + "Z",
        "role": data.get("role", "user"),
        "status": "active",
        "subscription": data.get("subscription", "free")
    }
    users[user_id] = new_user
    return {"user": new_user}, 201

def update_user(user_id, data):
    if user_id not in users:
        return {"error": "User not found"}, 404
    
    user = users[user_id]
    for key, value in data.items():
        if key in ["username", "email", "role", "status", "subscription"]:
            user[key] = value
    
    return {"user": user}

def delete_user(user_id):
    if user_id not in users:
        return {"error": "User not found"}, 404
    
    del users[user_id]
    return {"message": "User deleted successfully"}

def ban_user(user_id):
    if user_id not in users:
        return {"error": "User not found"}, 404
    
    users[user_id]["status"] = "banned"
    return {"user": users[user_id]}
