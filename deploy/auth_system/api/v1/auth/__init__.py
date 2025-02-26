import json
import os
import uuid
import hashlib
import jwt
import datetime

# Secret key for JWT token generation
SECRET_KEY = os.environ.get('JWT_SECRET_KEY', 'ghostprotocol-dev-secret')

# Helper function to load users data
def load_users():
    try:
        os.makedirs('static/data', exist_ok=True)
        with open('static/data/users.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        # Initialize with default admin user
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
        save_users(default_users)
        return default_users

# Helper function to save users data
def save_users(users_data):
    os.makedirs('static/data', exist_ok=True)
    with open('static/data/users.json', 'w') as f:
        json.dump(users_data, f, indent=2)

# Signup endpoint
@auth_bp.route('/signup', methods=['POST'])
def signup():
    users_data = load_users()
    data = request.json
    
    # Validate required fields
    if not data or 'username' not in data or 'password' not in data:
        return jsonify({"error": "Username and password are required"}), 400
    
    # Check if username already exists
    for user in users_data['users']:
        if user['username'] == data['username']:
            return jsonify({"error": "Username already exists"}), 400
    
    # Create new user
    user_id = str(uuid.uuid4())
    password_hash = hashlib.sha256(data['password'].encode()).hexdigest()
    
    new_user = {
        "id": user_id,
        "username": data['username'],
        "password_hash": password_hash,
        "role": "user",
        "created_at": datetime.datetime.now().isoformat(),
        "status": "active",
        "premium": False,
        "subscription_tier": "free",
        "storage_used": 0,
        "storage_limit": 10000000000  # 10GB for free users
    }
    
    users_data['users'].append(new_user)
    save_users(users_data)
    
    # Generate JWT token
    token = jwt.encode({
        'user_id': user_id,
        'username': data['username'],
        'role': 'user',
        'exp': datetime.datetime.utcnow() + datetime.timedelta(days=1)
    }, SECRET_KEY, algorithm='HS256')
    
    return jsonify({
        "message": "User created successfully",
        "user": {
            "id": user_id,
            "username": data['username'],
            "role": "user",
            "subscription_tier": "free"
        },
        "token": token
    }), 201

# Login endpoint
@auth_bp.route('/login', methods=['POST'])
def login():
    users_data = load_users()
    data = request.json
    
    # Validate required fields
    if not data or 'username' not in data or 'password' not in data:
        return jsonify({"error": "Username and password are required"}), 400
    
    # Check credentials
    password_hash = hashlib.sha256(data['password'].encode()).hexdigest()
    
    for user in users_data['users']:
        if user['username'] == data['username'] and user['password_hash'] == password_hash:
            # Check if user is banned
            if user['status'] == 'banned':
                return jsonify({"error": "Account is banned"}), 403
            
            # Generate JWT token
            token = jwt.encode({
                'user_id': user['id'],
                'username': user['username'],
                'role': user['role'],
                'exp': datetime.datetime.utcnow() + datetime.timedelta(days=1)
            }, SECRET_KEY, algorithm='HS256')
            
            return jsonify({
                "message": "Login successful",
                "user": {
                    "id": user['id'],
                    "username": user['username'],
                    "role": user['role'],
                    "subscription_tier": user['subscription_tier']
                },
                "token": token
            })
    
    return jsonify({"error": "Invalid username or password"}), 401

# Get current user endpoint
@auth_bp.route('/me', methods=['GET'])
def get_current_user():
    auth_header = request.headers.get('Authorization')
    
    if not auth_header or not auth_header.startswith('Bearer '):
        return jsonify({"error": "Authorization header is required"}), 401
    
    token = auth_header.split(' ')[1]
    
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=['HS256'])
        users_data = load_users()
        
        for user in users_data['users']:
            if user['id'] == payload['user_id']:
                return jsonify({
                    "id": user['id'],
                    "username": user['username'],
                    "role": user['role'],
                    "status": user['status'],
                    "premium": user['premium'],
                    "subscription_tier": user['subscription_tier'],
                    "storage_used": user['storage_used'],
                    "storage_limit": user['storage_limit']
                })
        
        return jsonify({"error": "User not found"}), 404
    except jwt.ExpiredSignatureError:
        return jsonify({"error": "Token has expired"}), 401
    except jwt.InvalidTokenError:
        return jsonify({"error": "Invalid token"}), 401
