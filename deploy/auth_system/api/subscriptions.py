import json
import os
import uuid
import datetime

# Helper function to load subscription data
def load_subscription_data():
    try:
        os.makedirs('static/data', exist_ok=True)
        with open('static/data/subscriptions.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        default_subscriptions = {
            "tiers": [
                {
                    "id": "free",
                    "name": "Free",
                    "price": 0,
                    "storage_limit": 10000000000,  # 10GB
                    "features": ["basic_messaging", "basic_calls"]
                },
                {
                    "id": "premium",
                    "name": "Premium",
                    "price": 9.99,
                    "storage_limit": 50000000000,  # 50GB
                    "features": ["basic_messaging", "basic_calls", "group_calls", "screen_sharing", "extended_message_retention"]
                },
                {
                    "id": "enterprise",
                    "name": "Enterprise",
                    "price": 19.99,
                    "storage_limit": 100000000000,  # 100GB
                    "features": ["basic_messaging", "basic_calls", "group_calls", "screen_sharing", "extended_message_retention", "priority_support", "admin_controls"]
                }
            ],
            "user_subscriptions": {}
        }
        save_subscription_data(default_subscriptions)
        return default_subscriptions

# Helper function to save subscription data
def save_subscription_data(subscription_data):
    os.makedirs('static/data', exist_ok=True)
    with open('static/data/subscriptions.json', 'w') as f:
        json.dump(subscription_data, f, indent=2)

# Get all subscription tiers
def get_subscription_tiers():
    subscription_data = load_subscription_data()
    return subscription_data['tiers']

# Get a specific subscription tier
def get_subscription_tier(tier_id):
    subscription_data = load_subscription_data()
    
    for tier in subscription_data['tiers']:
        if tier['id'] == tier_id:
            return tier
    
    return {"error": "Subscription tier not found"}, 404

# Create a new subscription tier
def create_subscription_tier(data):
    if not data or 'id' not in data or 'name' not in data or 'price' not in data or 'features' not in data:
        return {"error": "ID, name, price, and features are required"}, 400
    
    subscription_data = load_subscription_data()
    
    # Check if tier ID already exists
    for tier in subscription_data['tiers']:
        if tier['id'] == data['id']:
            return {"error": "Subscription tier ID already exists"}, 400
    
    new_tier = {
        "id": data['id'],
        "name": data['name'],
        "price": data['price'],
        "storage_limit": data.get('storage_limit', 10000000000),  # Default to 10GB
        "features": data['features']
    }
    
    subscription_data['tiers'].append(new_tier)
    save_subscription_data(subscription_data)
    
    return new_tier, 201

# Update a subscription tier
def update_subscription_tier(tier_id, data):
    if not data:
        return {"error": "No data provided"}, 400
    
    subscription_data = load_subscription_data()
    
    for tier in subscription_data['tiers']:
        if tier['id'] == tier_id:
            if 'name' in data:
                tier['name'] = data['name']
            if 'price' in data:
                tier['price'] = data['price']
            if 'storage_limit' in data:
                tier['storage_limit'] = data['storage_limit']
            if 'features' in data:
                tier['features'] = data['features']
            
            save_subscription_data(subscription_data)
            return tier, 200
    
    return {"error": "Subscription tier not found"}, 404

# Delete a subscription tier
def delete_subscription_tier(tier_id):
    subscription_data = load_subscription_data()
    
    for i, tier in enumerate(subscription_data['tiers']):
        if tier['id'] == tier_id:
            del subscription_data['tiers'][i]
            save_subscription_data(subscription_data)
            
            return {"message": "Subscription tier deleted successfully"}, 200
    
    return {"error": "Subscription tier not found"}, 404

# Get a user's subscription
def get_user_subscription(user_id):
    subscription_data = load_subscription_data()
    
    if user_id in subscription_data['user_subscriptions']:
        return subscription_data['user_subscriptions'][user_id]
    
    # Return default free tier if user has no subscription
    return {
        "user_id": user_id,
        "tier_id": "free",
        "start_date": None,
        "end_date": None,
        "auto_renew": False,
        "payment_method": None
    }

# Update a user's subscription
def update_user_subscription(user_id, data):
    if not data or 'tier_id' not in data:
        return {"error": "Tier ID is required"}, 400
    
    subscription_data = load_subscription_data()
    
    # Check if tier exists
    tier_exists = False
    for tier in subscription_data['tiers']:
        if tier['id'] == data['tier_id']:
            tier_exists = True
            break
    
    if not tier_exists:
        return {"error": "Subscription tier not found"}, 404
    
    # Create or update user subscription
    user_subscription = {
        "user_id": user_id,
        "tier_id": data['tier_id'],
        "start_date": data.get('start_date', datetime.datetime.now().isoformat()),
        "end_date": data.get('end_date'),
        "auto_renew": data.get('auto_renew', False),
        "payment_method": data.get('payment_method')
    }
    
    subscription_data['user_subscriptions'][user_id] = user_subscription
    save_subscription_data(subscription_data)
    
    return user_subscription, 200
