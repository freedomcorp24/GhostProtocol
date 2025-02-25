import json
import uuid
from datetime import datetime

# Mock database for vault items
vault_items = {
    "1": {
        "id": "1",
        "user_id": "3",
        "type": "password",
        "name": "Email Password",
        "data": {
            "username": "premium@example.com",
            "password": "encrypted_password_data",
            "url": "https://mail.example.com"
        },
        "created_at": "2025-01-03T00:00:00Z",
        "updated_at": "2025-01-03T00:00:00Z"
    },
    "2": {
        "id": "2",
        "user_id": "3",
        "type": "note",
        "name": "Private Note",
        "data": {
            "content": "encrypted_note_content"
        },
        "created_at": "2025-01-03T01:00:00Z",
        "updated_at": "2025-01-03T01:00:00Z"
    },
    "3": {
        "id": "3",
        "user_id": "3",
        "type": "contact",
        "name": "Important Contact",
        "data": {
            "name": "John Doe",
            "email": "john@example.com",
            "phone": "+1234567890"
        },
        "created_at": "2025-01-03T02:00:00Z",
        "updated_at": "2025-01-03T02:00:00Z"
    }
}

def get_user_vault_items(user_id):
    items = [item for item_id, item in vault_items.items() if item["user_id"] == user_id]
    return {"items": items, "total": len(items)}

def get_vault_item(item_id, user_id):
    if item_id in vault_items and vault_items[item_id]["user_id"] == user_id:
        return {"item": vault_items[item_id]}
    return {"error": "Vault item not found"}, 404

def create_vault_item(user_id, data):
    item_id = str(uuid.uuid4())
    now = datetime.utcnow().isoformat() + "Z"
    new_item = {
        "id": item_id,
        "user_id": user_id,
        "type": data.get("type"),
        "name": data.get("name"),
        "data": data.get("data", {}),
        "created_at": now,
        "updated_at": now
    }
    vault_items[item_id] = new_item
    return {"item": new_item}, 201

def update_vault_item(item_id, user_id, data):
    if item_id not in vault_items or vault_items[item_id]["user_id"] != user_id:
        return {"error": "Vault item not found"}, 404
    
    item = vault_items[item_id]
    for key, value in data.items():
        if key in ["name", "data"]:
            item[key] = value
    
    item["updated_at"] = datetime.utcnow().isoformat() + "Z"
    return {"item": item}

def delete_vault_item(item_id, user_id):
    if item_id not in vault_items or vault_items[item_id]["user_id"] != user_id:
        return {"error": "Vault item not found"}, 404
    
    del vault_items[item_id]
    return {"message": "Vault item deleted successfully"}
