import json
import os
import uuid
import datetime
import hashlib

# Helper function to load users data
def load_users():
    try:
        os.makedirs('static/data', exist_ok=True)
        with open('static/data/users.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        default_users = {"users": []}
        save_users(default_users)
        return default_users

# Helper function to save users data
def save_users(users_data):
    os.makedirs('static/data', exist_ok=True)
    with open('static/data/users.json', 'w') as f:
        json.dump(users_data, f, indent=2)

# Get all users
def get_users():
    users_data = load_users()
    
    # Remove password hashes from response
    for user in users_data['users']:
        if 'password_hash' in user:
            del user['password_hash']
    
    return users_data['users']

# Get a specific user
def get_user(user_id):
    users_data = load_users()
    
    for user in users_data['users']:
        if user['id'] == user_id:
            # Remove password hash from response
            if 'password_hash' in user:
                del user['password_hash']
            
            return user
    
    return {"error": "User not found"}, 404

# Create a new user
def create_user(data):
    if not data or 'username' not in data or 'password' not in data:
        return {"error": "Username and password are required"}, 400
    
    users_data = load_users()
    
    # Check if username already exists
    for user in users_data['users']:
        if user['username'] == data['username']:
            return {"error": "Username already exists"}, 400
    
    user_id = str(uuid.uuid4())
    password_hash = hashlib.sha256(data['password'].encode()).hexdigest()
    
    new_user = {
        "id": user_id,
        "username": data['username'],
        "password_hash": password_hash,
        "role": data.get('role', 'user'),
        "created_at": datetime.datetime.now().isoformat(),
        "status": "active",
        "premium": False,
        "subscription_tier": "free",
        "storage_used": 0,
        "storage_limit": 10000000000  # 10GB for free users
    }
    
    users_data['users'].append(new_user)
    save_users(users_data)
    
    # Remove password hash from response
    response_user = new_user.copy()
    del response_user['password_hash']
    
    return response_user, 201

# Update a user
def update_user(user_id, data):
    if not data:
        return {"error": "No data provided"}, 400
    
    users_data = load_users()
    
    for user in users_data['users']:
        if user['id'] == user_id:
            # Update user fields
            if 'username' in data:
                # Check if new username already exists
                for other_user in users_data['users']:
                    if other_user['username'] == data['username'] and other_user['id'] != user_id:
                        return {"error": "Username already exists"}, 400
                
                user['username'] = data['username']
            
            if 'password' in data:
                user['password_hash'] = hashlib.sha256(data['password'].encode()).hexdigest()
            
            if 'role' in data:
                user['role'] = data['role']
            
            if 'status' in data:
                user['status'] = data['status']
            
            if 'premium' in data:
                user['premium'] = data['premium']
            
            if 'subscription_tier' in data:
                user['subscription_tier'] = data['subscription_tier']
            
            if 'storage_limit' in data:
                user['storage_limit'] = data['storage_limit']
            
            save_users(users_data)
            
            # Remove password hash from response
            response_user = user.copy()
            del response_user['password_hash']
            
            return response_user, 200
    
    return {"error": "User not found"}, 404

# Delete a user
def delete_user(user_id):
    users_data = load_users()
    
    for i, user in enumerate(users_data['users']):
        if user['id'] == user_id:
            del users_data['users'][i]
            save_users(users_data)
            
            return {"message": "User deleted successfully"}, 200
    
    return {"error": "User not found"}, 404

# Ban a user
def ban_user(user_id):
    users_data = load_users()
    
    for user in users_data['users']:
        if user['id'] == user_id:
            user['status'] = 'banned'
            save_users(users_data)
            
            # Remove password hash from response
            response_user = user.copy()
            del response_user['password_hash']
            
            return response_user, 200
    
    return {"error": "User not found"}, 404
