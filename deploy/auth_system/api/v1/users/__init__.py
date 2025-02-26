from flask import Blueprint, jsonify, request
import json
import os
import uuid
from datetime import datetime

users_bp = Blueprint('users', __name__)

# Helper function to load users data
def load_users():
    try:
        with open('static/data/users.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        # Initialize with default admin user
        default_users = {
            "users": [
                {
                    "id": "1",
                    "username": "admin",
                    "email": "admin@ghostprotocol.com",
                    "role": "super_admin",
                    "created_at": datetime.now().isoformat(),
                    "status": "active",
                    "premium": True,
                    "subscription_tier": "enterprise",
                    "storage_used": 0,
                    "storage_limit": 100000000000  # 100GB
                }
            ]
        }
        save_users(default_users)
        return default_users

# Helper function to save users data
def save_users(users_data):
    with open('static/data/users.json', 'w') as f:
        json.dump(users_data, f, indent=2)

# Get all users
@users_bp.route('/', methods=['GET'])
def get_users():
    users_data = load_users()
    return jsonify(users_data)

# Get user by ID
@users_bp.route('/<user_id>', methods=['GET'])
def get_user(user_id):
    users_data = load_users()
    for user in users_data['users']:
        if user['id'] == user_id:
            return jsonify(user)
    return jsonify({"error": "User not found"}), 404

# Create new user
@users_bp.route('/', methods=['POST'])
def create_user():
    users_data = load_users()
    new_user = request.json
    
    # Validate required fields
    required_fields = ['username', 'email']
    for field in required_fields:
        if field not in new_user:
            return jsonify({"error": f"Missing required field: {field}"}), 400
    
    # Check if username already exists
    for user in users_data['users']:
        if user['username'] == new_user['username']:
            return jsonify({"error": "Username already exists"}), 400
    
    # Add default fields
    new_user['id'] = str(uuid.uuid4())
    new_user['created_at'] = datetime.now().isoformat()
    new_user['status'] = 'active'
    new_user['premium'] = new_user.get('premium', False)
    new_user['subscription_tier'] = new_user.get('subscription_tier', 'free')
    new_user['storage_used'] = 0
    new_user['storage_limit'] = 10000000000  # 10GB for regular users
    
    users_data['users'].append(new_user)
    save_users(users_data)
    
    return jsonify(new_user), 201

# Update user
@users_bp.route('/<user_id>', methods=['PUT'])
def update_user(user_id):
    users_data = load_users()
    update_data = request.json
    
    for i, user in enumerate(users_data['users']):
        if user['id'] == user_id:
            # Update fields
            for key, value in update_data.items():
                # Don't allow changing certain fields
                if key not in ['id', 'created_at']:
                    user[key] = value
            
            users_data['users'][i] = user
            save_users(users_data)
            return jsonify(user)
    
    return jsonify({"error": "User not found"}), 404

# Delete user
@users_bp.route('/<user_id>', methods=['DELETE'])
def delete_user(user_id):
    users_data = load_users()
    
    for i, user in enumerate(users_data['users']):
        if user['id'] == user_id:
            deleted_user = users_data['users'].pop(i)
            save_users(users_data)
            return jsonify({"message": "User deleted successfully", "user": deleted_user})
    
    return jsonify({"error": "User not found"}), 404

# Ban user
@users_bp.route('/<user_id>/ban', methods=['POST'])
def ban_user(user_id):
    users_data = load_users()
    
    for i, user in enumerate(users_data['users']):
        if user['id'] == user_id:
            user['status'] = 'banned'
            users_data['users'][i] = user
            save_users(users_data)
            return jsonify({"message": "User banned successfully", "user": user})
    
    return jsonify({"error": "User not found"}), 404

# Unban user
@users_bp.route('/<user_id>/unban', methods=['POST'])
def unban_user(user_id):
    users_data = load_users()
    
    for i, user in enumerate(users_data['users']):
        if user['id'] == user_id:
            user['status'] = 'active'
            users_data['users'][i] = user
            save_users(users_data)
            return jsonify({"message": "User unbanned successfully", "user": user})
    
    return jsonify({"error": "User not found"}), 404

# Upgrade user to premium
@users_bp.route('/<user_id>/upgrade', methods=['POST'])
def upgrade_user(user_id):
    users_data = load_users()
    upgrade_data = request.json
    
    for i, user in enumerate(users_data['users']):
        if user['id'] == user_id:
            user['premium'] = True
            user['subscription_tier'] = upgrade_data.get('tier', 'premium')
            user['storage_limit'] = 50000000000  # 50GB for premium users
            users_data['users'][i] = user
            save_users(users_data)
            return jsonify({"message": "User upgraded to premium", "user": user})
    
    return jsonify({"error": "User not found"}), 404

# Downgrade user from premium
@users_bp.route('/<user_id>/downgrade', methods=['POST'])
def downgrade_user(user_id):
    users_data = load_users()
    
    for i, user in enumerate(users_data['users']):
        if user['id'] == user_id:
            user['premium'] = False
            user['subscription_tier'] = 'free'
            user['storage_limit'] = 10000000000  # 10GB for regular users
            users_data['users'][i] = user
            save_users(users_data)
            return jsonify({"message": "User downgraded from premium", "user": user})
    
    return jsonify({"error": "User not found"}), 404
