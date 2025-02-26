from flask import Blueprint, jsonify, request
import json
import os
import uuid
from datetime import datetime

vault_bp = Blueprint('vault', __name__)

# Helper function to load vault items data
def load_vault_items():
    try:
        with open('static/data/vault_items.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        # Initialize with empty vault items
        default_vault_items = {
            "vault_items": []
        }
        save_vault_items(default_vault_items)
        return default_vault_items

# Helper function to save vault items data
def save_vault_items(vault_items_data):
    with open('static/data/vault_items.json', 'w') as f:
        json.dump(vault_items_data, f, indent=2)

# Get all vault items for a user
@vault_bp.route('/user/<user_id>', methods=['GET'])
def get_user_vault_items(user_id):
    vault_items_data = load_vault_items()
    user_items = [item for item in vault_items_data['vault_items'] if item['user_id'] == user_id]
    return jsonify({"vault_items": user_items})

# Get vault item by ID
@vault_bp.route('/<item_id>', methods=['GET'])
def get_vault_item(item_id):
    vault_items_data = load_vault_items()
    for item in vault_items_data['vault_items']:
        if item['id'] == item_id:
            return jsonify(item)
    return jsonify({"error": "Vault item not found"}), 404

# Create new vault item
@vault_bp.route('/', methods=['POST'])
def create_vault_item():
    vault_items_data = load_vault_items()
    new_item = request.json
    
    # Validate required fields
    required_fields = ['user_id', 'type', 'name', 'content']
    for field in required_fields:
        if field not in new_item:
            return jsonify({"error": f"Missing required field: {field}"}), 400
    
    # Validate item type
    valid_types = ['password', 'contact', 'file', 'note']
    if new_item['type'] not in valid_types:
        return jsonify({"error": f"Invalid item type. Must be one of: {', '.join(valid_types)}"}), 400
    
    # Add default fields
    new_item['id'] = str(uuid.uuid4())
    new_item['created_at'] = datetime.now().isoformat()
    new_item['updated_at'] = datetime.now().isoformat()
    
    vault_items_data['vault_items'].append(new_item)
    save_vault_items(vault_items_data)
    
    return jsonify(new_item), 201

# Update vault item
@vault_bp.route('/<item_id>', methods=['PUT'])
def update_vault_item(item_id):
    vault_items_data = load_vault_items()
    update_data = request.json
    
    for i, item in enumerate(vault_items_data['vault_items']):
        if item['id'] == item_id:
            # Update fields
            for key, value in update_data.items():
                # Don't allow changing certain fields
                if key not in ['id', 'created_at', 'user_id']:
                    item[key] = value
            
            # Update the updated_at timestamp
            item['updated_at'] = datetime.now().isoformat()
            
            vault_items_data['vault_items'][i] = item
            save_vault_items(vault_items_data)
            return jsonify(item)
    
    return jsonify({"error": "Vault item not found"}), 404

# Delete vault item
@vault_bp.route('/<item_id>', methods=['DELETE'])
def delete_vault_item(item_id):
    vault_items_data = load_vault_items()
    
    for i, item in enumerate(vault_items_data['vault_items']):
        if item['id'] == item_id:
            deleted_item = vault_items_data['vault_items'].pop(i)
            save_vault_items(vault_items_data)
            return jsonify({"message": "Vault item deleted successfully", "item": deleted_item})
    
    return jsonify({"error": "Vault item not found"}), 404

# Generate a random password
@vault_bp.route('/generate-password', methods=['GET'])
def generate_password():
    import random
    import string
    
    length = int(request.args.get('length', 12))
    include_uppercase = request.args.get('uppercase', 'true').lower() == 'true'
    include_lowercase = request.args.get('lowercase', 'true').lower() == 'true'
    include_numbers = request.args.get('numbers', 'true').lower() == 'true'
    include_symbols = request.args.get('symbols', 'true').lower() == 'true'
    
    # Ensure at least one character type is selected
    if not any([include_uppercase, include_lowercase, include_numbers, include_symbols]):
        include_lowercase = True
    
    # Define character sets
    chars = ''
    if include_uppercase:
        chars += string.ascii_uppercase
    if include_lowercase:
        chars += string.ascii_lowercase
    if include_numbers:
        chars += string.digits
    if include_symbols:
        chars += string.punctuation
    
    # Generate password
    password = ''.join(random.choice(chars) for _ in range(length))
    
    return jsonify({"password": password})
