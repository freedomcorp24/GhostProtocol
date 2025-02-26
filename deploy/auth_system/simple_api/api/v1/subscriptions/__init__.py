from flask import Blueprint, jsonify, request
import json
import os
import uuid
from datetime import datetime

subscriptions_bp = Blueprint('subscriptions', __name__)

# Helper function to load subscription tiers data
def load_tiers():
    try:
        with open('static/data/subscription_tiers.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        # Initialize with default tiers
        default_tiers = {
            "tiers": [
                {
                    "id": "1",
                    "name": "free",
                    "display_name": "Free",
                    "price": 0,
                    "storage_limit": 10000000000,  # 10GB
                    "features": [
                        "Basic messaging",
                        "End-to-end encryption",
                        "10GB storage"
                    ],
                    "created_at": datetime.now().isoformat()
                },
                {
                    "id": "2",
                    "name": "premium",
                    "display_name": "Premium",
                    "price": 9.99,
                    "storage_limit": 50000000000,  # 50GB
                    "features": [
                        "Basic messaging",
                        "End-to-end encryption",
                        "50GB storage",
                        "Screen recording",
                        "Video calls",
                        "Voice calls"
                    ],
                    "created_at": datetime.now().isoformat()
                },
                {
                    "id": "3",
                    "name": "enterprise",
                    "display_name": "Enterprise",
                    "price": 29.99,
                    "storage_limit": 100000000000,  # 100GB
                    "features": [
                        "Basic messaging",
                        "End-to-end encryption",
                        "100GB storage",
                        "Screen recording",
                        "Video calls",
                        "Voice calls",
                        "Admin dashboard",
                        "User management",
                        "Analytics"
                    ],
                    "created_at": datetime.now().isoformat()
                }
            ]
        }
        save_tiers(default_tiers)
        return default_tiers

# Helper function to save subscription tiers data
def save_tiers(tiers_data):
    with open('static/data/subscription_tiers.json', 'w') as f:
        json.dump(tiers_data, f, indent=2)

# Helper function to load user subscriptions data
def load_subscriptions():
    try:
        with open('static/data/subscriptions.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        # Initialize with empty subscriptions
        default_subscriptions = {
            "subscriptions": []
        }
        save_subscriptions(default_subscriptions)
        return default_subscriptions

# Helper function to save user subscriptions data
def save_subscriptions(subscriptions_data):
    with open('static/data/subscriptions.json', 'w') as f:
        json.dump(subscriptions_data, f, indent=2)

# Get all subscription tiers
@subscriptions_bp.route('/tiers', methods=['GET'])
def get_tiers():
    tiers_data = load_tiers()
    return jsonify(tiers_data)

# Get subscription tier by ID
@subscriptions_bp.route('/tiers/<tier_id>', methods=['GET'])
def get_tier(tier_id):
    tiers_data = load_tiers()
    for tier in tiers_data['tiers']:
        if tier['id'] == tier_id:
            return jsonify(tier)
    return jsonify({"error": "Subscription tier not found"}), 404

# Create new subscription tier
@subscriptions_bp.route('/tiers', methods=['POST'])
def create_tier():
    tiers_data = load_tiers()
    new_tier = request.json
    
    # Validate required fields
    required_fields = ['name', 'display_name', 'price', 'storage_limit', 'features']
    for field in required_fields:
        if field not in new_tier:
            return jsonify({"error": f"Missing required field: {field}"}), 400
    
    # Check if tier name already exists
    for tier in tiers_data['tiers']:
        if tier['name'] == new_tier['name']:
            return jsonify({"error": "Tier name already exists"}), 400
    
    # Add default fields
    new_tier['id'] = str(uuid.uuid4())
    new_tier['created_at'] = datetime.now().isoformat()
    
    tiers_data['tiers'].append(new_tier)
    save_tiers(tiers_data)
    
    return jsonify(new_tier), 201

# Update subscription tier
@subscriptions_bp.route('/tiers/<tier_id>', methods=['PUT'])
def update_tier(tier_id):
    tiers_data = load_tiers()
    update_data = request.json
    
    for i, tier in enumerate(tiers_data['tiers']):
        if tier['id'] == tier_id:
            # Update fields
            for key, value in update_data.items():
                # Don't allow changing certain fields
                if key not in ['id', 'created_at']:
                    tier[key] = value
            
            tiers_data['tiers'][i] = tier
            save_tiers(tiers_data)
            return jsonify(tier)
    
    return jsonify({"error": "Subscription tier not found"}), 404

# Delete subscription tier
@subscriptions_bp.route('/tiers/<tier_id>', methods=['DELETE'])
def delete_tier(tier_id):
    tiers_data = load_tiers()
    
    for i, tier in enumerate(tiers_data['tiers']):
        if tier['id'] == tier_id:
            deleted_tier = tiers_data['tiers'].pop(i)
            save_tiers(tiers_data)
            return jsonify({"message": "Subscription tier deleted successfully", "tier": deleted_tier})
    
    return jsonify({"error": "Subscription tier not found"}), 404

# Get all user subscriptions
@subscriptions_bp.route('/', methods=['GET'])
def get_subscriptions():
    subscriptions_data = load_subscriptions()
    return jsonify(subscriptions_data)

# Get user subscription by user ID
@subscriptions_bp.route('/user/<user_id>', methods=['GET'])
def get_user_subscription(user_id):
    subscriptions_data = load_subscriptions()
    for subscription in subscriptions_data['subscriptions']:
        if subscription['user_id'] == user_id:
            return jsonify(subscription)
    return jsonify({"error": "Subscription not found for user"}), 404

# Create new user subscription
@subscriptions_bp.route('/', methods=['POST'])
def create_subscription():
    subscriptions_data = load_subscriptions()
    new_subscription = request.json
    
    # Validate required fields
    required_fields = ['user_id', 'tier_id', 'payment_method']
    for field in required_fields:
        if field not in new_subscription:
            return jsonify({"error": f"Missing required field: {field}"}), 400
    
    # Check if user already has a subscription
    for subscription in subscriptions_data['subscriptions']:
        if subscription['user_id'] == new_subscription['user_id']:
            return jsonify({"error": "User already has a subscription"}), 400
    
    # Add default fields
    new_subscription['id'] = str(uuid.uuid4())
    new_subscription['created_at'] = datetime.now().isoformat()
    new_subscription['status'] = 'active'
    
    subscriptions_data['subscriptions'].append(new_subscription)
    save_subscriptions(subscriptions_data)
    
    return jsonify(new_subscription), 201

# Update user subscription
@subscriptions_bp.route('/<subscription_id>', methods=['PUT'])
def update_subscription(subscription_id):
    subscriptions_data = load_subscriptions()
    update_data = request.json
    
    for i, subscription in enumerate(subscriptions_data['subscriptions']):
        if subscription['id'] == subscription_id:
            # Update fields
            for key, value in update_data.items():
                # Don't allow changing certain fields
                if key not in ['id', 'created_at', 'user_id']:
                    subscription[key] = value
            
            subscriptions_data['subscriptions'][i] = subscription
            save_subscriptions(subscriptions_data)
            return jsonify(subscription)
    
    return jsonify({"error": "Subscription not found"}), 404

# Cancel user subscription
@subscriptions_bp.route('/<subscription_id>/cancel', methods=['POST'])
def cancel_subscription(subscription_id):
    subscriptions_data = load_subscriptions()
    
    for i, subscription in enumerate(subscriptions_data['subscriptions']):
        if subscription['id'] == subscription_id:
            subscription['status'] = 'cancelled'
            subscriptions_data['subscriptions'][i] = subscription
            save_subscriptions(subscriptions_data)
            return jsonify({"message": "Subscription cancelled successfully", "subscription": subscription})
    
    return jsonify({"error": "Subscription not found"}), 404
