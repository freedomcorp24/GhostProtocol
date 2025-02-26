import json
import os
import uuid
import datetime

# Helper function to load vault data
def load_vault_data():
    try:
        os.makedirs('static/data', exist_ok=True)
        with open('static/data/vault.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        default_vault = {"vault_items": {}}
        save_vault_data(default_vault)
        return default_vault

# Helper function to save vault data
def save_vault_data(vault_data):
    os.makedirs('static/data', exist_ok=True)
    with open('static/data/vault.json', 'w') as f:
        json.dump(vault_data, f, indent=2)

# Get all vault items for a user
def get_user_vault(user_id):
    vault_data = load_vault_data()
    
    if user_id not in vault_data['vault_items']:
        vault_data['vault_items'][user_id] = []
        save_vault_data(vault_data)
    
    return vault_data['vault_items'][user_id]

# Get a specific vault item
def get_vault_item(user_id, item_id):
    vault_data = load_vault_data()
    
    if user_id not in vault_data['vault_items']:
        return {"error": "User vault not found"}, 404
    
    for item in vault_data['vault_items'][user_id]:
        if item['id'] == item_id:
            return item
    
    return {"error": "Vault item not found"}, 404

# Create a new vault item
def create_vault_item(user_id, data):
    if not data or 'name' not in data or 'content' not in data:
        return {"error": "Name and content are required"}, 400
    
    vault_data = load_vault_data()
    
    if user_id not in vault_data['vault_items']:
        vault_data['vault_items'][user_id] = []
    
    item_id = str(uuid.uuid4())
    
    new_item = {
        "id": item_id,
        "name": data['name'],
        "content": data['content'],
        "created_at": datetime.datetime.now().isoformat(),
        "updated_at": datetime.datetime.now().isoformat()
    }
    
    vault_data['vault_items'][user_id].append(new_item)
    save_vault_data(vault_data)
    
    return new_item, 201

# Update a vault item
def update_vault_item(user_id, item_id, data):
    if not data:
        return {"error": "No data provided"}, 400
    
    vault_data = load_vault_data()
    
    if user_id not in vault_data['vault_items']:
        return {"error": "User vault not found"}, 404
    
    for item in vault_data['vault_items'][user_id]:
        if item['id'] == item_id:
            if 'name' in data:
                item['name'] = data['name']
            if 'content' in data:
                item['content'] = data['content']
            
            item['updated_at'] = datetime.datetime.now().isoformat()
            save_vault_data(vault_data)
            
            return item, 200
    
    return {"error": "Vault item not found"}, 404

# Delete a vault item
def delete_vault_item(user_id, item_id):
    vault_data = load_vault_data()
    
    if user_id not in vault_data['vault_items']:
        return {"error": "User vault not found"}, 404
    
    for i, item in enumerate(vault_data['vault_items'][user_id]):
        if item['id'] == item_id:
            del vault_data['vault_items'][user_id][i]
            save_vault_data(vault_data)
            
            return {"message": "Vault item deleted successfully"}, 200
    
    return {"error": "Vault item not found"}, 404
