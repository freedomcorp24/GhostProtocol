import json
import uuid
from datetime import datetime, timedelta

# Mock database for subscriptions
subscriptions = {
    "free": {
        "id": "free",
        "name": "Free",
        "price": 0,
        "features": ["basic_messaging", "group_chats", "media_sharing"],
        "limits": {
            "storage": "1GB",
            "message_history": "30 days",
            "group_size": 10
        }
    },
    "premium": {
        "id": "premium",
        "name": "Premium",
        "price": 9.99,
        "features": ["basic_messaging", "group_chats", "media_sharing", "screen_sharing", "vault_storage", "extended_history"],
        "limits": {
            "storage": "10GB",
            "message_history": "1 year",
            "group_size": 100
        }
    },
    "business": {
        "id": "business",
        "name": "Business",
        "price": 19.99,
        "features": ["basic_messaging", "group_chats", "media_sharing", "screen_sharing", "vault_storage", "extended_history", "priority_support", "admin_controls"],
        "limits": {
            "storage": "50GB",
            "message_history": "unlimited",
            "group_size": 500
        }
    }
}

# Mock database for user subscriptions
user_subscriptions = {
    "1": {
        "user_id": "1",
        "subscription_id": "business",
        "start_date": "2025-01-01T00:00:00Z",
        "end_date": "2026-01-01T00:00:00Z",
        "status": "active",
        "payment_method": "crypto",
        "auto_renew": True
    },
    "2": {
        "user_id": "2",
        "subscription_id": "free",
        "start_date": "2025-01-02T00:00:00Z",
        "end_date": None,
        "status": "active",
        "payment_method": None,
        "auto_renew": False
    },
    "3": {
        "user_id": "3",
        "subscription_id": "premium",
        "start_date": "2025-01-03T00:00:00Z",
        "end_date": "2025-02-03T00:00:00Z",
        "status": "active",
        "payment_method": "crypto",
        "auto_renew": True
    }
}

def get_subscription_tiers():
    return {"tiers": list(subscriptions.values())}

def get_subscription_tier(tier_id):
    if tier_id in subscriptions:
        return {"tier": subscriptions[tier_id]}
    return {"error": "Subscription tier not found"}, 404

def create_subscription_tier(data):
    tier_id = data.get("id")
    if tier_id in subscriptions:
        return {"error": "Subscription tier already exists"}, 400
    
    new_tier = {
        "id": tier_id,
        "name": data.get("name"),
        "price": data.get("price"),
        "features": data.get("features", []),
        "limits": data.get("limits", {})
    }
    subscriptions[tier_id] = new_tier
    return {"tier": new_tier}, 201

def update_subscription_tier(tier_id, data):
    if tier_id not in subscriptions:
        return {"error": "Subscription tier not found"}, 404
    
    tier = subscriptions[tier_id]
    for key, value in data.items():
        if key in ["name", "price", "features", "limits"]:
            tier[key] = value
    
    return {"tier": tier}

def delete_subscription_tier(tier_id):
    if tier_id not in subscriptions:
        return {"error": "Subscription tier not found"}, 404
    
    del subscriptions[tier_id]
    return {"message": "Subscription tier deleted successfully"}

def get_user_subscription(user_id):
    for sub_id, sub in user_subscriptions.items():
        if sub["user_id"] == user_id:
            return {"subscription": sub}
    return {"error": "User subscription not found"}, 404

def update_user_subscription(user_id, data):
    for sub_id, sub in user_subscriptions.items():
        if sub["user_id"] == user_id:
            for key, value in data.items():
                if key in ["subscription_id", "end_date", "status", "payment_method", "auto_renew"]:
                    sub[key] = value
            return {"subscription": sub}
    
    # Create new subscription if not found
    sub_id = str(uuid.uuid4())
    new_sub = {
        "user_id": user_id,
        "subscription_id": data.get("subscription_id", "free"),
        "start_date": datetime.utcnow().isoformat() + "Z",
        "end_date": (datetime.utcnow() + timedelta(days=30)).isoformat() + "Z" if data.get("subscription_id") != "free" else None,
        "status": "active",
        "payment_method": data.get("payment_method"),
        "auto_renew": data.get("auto_renew", False)
    }
    user_subscriptions[sub_id] = new_sub
    return {"subscription": new_sub}
