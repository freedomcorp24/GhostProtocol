from fastapi import FastAPI, HTTPException, Depends, Header
from fastapi.middleware.cors import CORSMiddleware
from fastapi.staticfiles import StaticFiles
from fastapi.responses import JSONResponse
from pydantic import BaseModel
import os
import json
import hashlib
import jwt
import datetime
import uuid
from typing import Optional

# Create FastAPI app
app = FastAPI(title="GhostProtocol API")

# Configure CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Secret key for JWT token generation
SECRET_KEY = os.environ.get('JWT_SECRET_KEY', 'ghostprotocol-dev-secret')

# Models
class UserLogin(BaseModel):
    username: str
    password: str

class UserSignup(BaseModel):
    username: str
    password: str

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

# Mount static files
app.mount("/static", StaticFiles(directory="static"), name="static")

# Health check endpoint
@app.get("/healthz")
def health_check():
    return {"status": "ok"}

# API info endpoint
@app.get("/")
def api_info():
    return {"message": "GhostProtocol API", "version": "1.0.0"}

# API routes
@app.post("/auth/signup")
async def signup(user: UserSignup):
    users_data = load_users()
    
    # Check if username already exists
    for existing_user in users_data['users']:
        if existing_user['username'] == user.username:
            raise HTTPException(status_code=400, detail="Username already exists")
    
    # Create new user
    user_id = str(uuid.uuid4())
    password_hash = hashlib.sha256(user.password.encode()).hexdigest()
    
    new_user = {
        "id": user_id,
        "username": user.username,
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
        'username': user.username,
        'role': 'user',
        'exp': datetime.datetime.utcnow() + datetime.timedelta(days=1)
    }, SECRET_KEY, algorithm='HS256')
    
    return {
        "message": "User created successfully",
        "user": {
            "id": user_id,
            "username": user.username,
            "role": "user",
            "subscription_tier": "free"
        },
        "token": token
    }

@app.post("/auth/login")
async def login(user: UserLogin):
    users_data = load_users()
    
    # Check credentials
    password_hash = hashlib.sha256(user.password.encode()).hexdigest()
    
    for existing_user in users_data['users']:
        if existing_user['username'] == user.username and existing_user['password_hash'] == password_hash:
            # Check if user is banned
            if existing_user['status'] == 'banned':
                raise HTTPException(status_code=403, detail="Account is banned")
            
            # Generate JWT token
            token = jwt.encode({
                'user_id': existing_user['id'],
                'username': existing_user['username'],
                'role': existing_user['role'],
                'exp': datetime.datetime.utcnow() + datetime.timedelta(days=1)
            }, SECRET_KEY, algorithm='HS256')
            
            return {
                "message": "Login successful",
                "user": {
                    "id": existing_user['id'],
                    "username": existing_user['username'],
                    "role": existing_user['role'],
                    "subscription_tier": existing_user['subscription_tier']
                },
                "token": token
            }
    
    raise HTTPException(status_code=401, detail="Invalid username or password")

@app.get("/auth/me")
async def get_current_user(authorization: Optional[str] = Header(None)):
    if not authorization or not authorization.startswith('Bearer '):
        raise HTTPException(status_code=401, detail="Authorization header is required")
    
    token = authorization.split(' ')[1]
    
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=['HS256'])
        users_data = load_users()
        
        for user in users_data['users']:
            if user['id'] == payload['user_id']:
                return {
                    "id": user['id'],
                    "username": user['username'],
                    "role": user['role'],
                    "status": user['status'],
                    "premium": user['premium'],
                    "subscription_tier": user['subscription_tier'],
                    "storage_used": user['storage_used'],
                    "storage_limit": user['storage_limit']
                }
        
        raise HTTPException(status_code=404, detail="User not found")
    except jwt.ExpiredSignatureError:
        raise HTTPException(status_code=401, detail="Token has expired")
    except jwt.InvalidTokenError:
        raise HTTPException(status_code=401, detail="Invalid token")
