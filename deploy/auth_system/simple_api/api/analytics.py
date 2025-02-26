import json
from datetime import datetime, timedelta
import random

def get_user_stats():
    # Mock data for user statistics
    total_users = 15432
    active_users = 8721
    new_users_today = 143
    
    return {
        "total_users": total_users,
        "active_users": active_users,
        "new_users_today": new_users_today,
        "user_growth": {
            "daily": 2.3,
            "weekly": 8.7,
            "monthly": 15.2
        },
        "user_retention": 87.5,
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }

def get_message_stats():
    # Mock data for message statistics
    messages_today = 256789
    media_shared_today = 45321
    active_groups = 3245
    
    return {
        "messages_today": messages_today,
        "media_shared_today": media_shared_today,
        "active_groups": active_groups,
        "message_volume": {
            "daily": messages_today,
            "weekly": messages_today * 7,
            "monthly": messages_today * 30
        },
        "peak_hours": [
            {"hour": 9, "volume": 15432},
            {"hour": 12, "volume": 18765},
            {"hour": 18, "volume": 21543}
        ],
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }

def get_subscription_stats():
    # Mock data for subscription statistics
    free_users = 12345
    premium_users = 2345
    business_users = 742
    
    return {
        "subscription_distribution": {
            "free": free_users,
            "premium": premium_users,
            "business": business_users
        },
        "conversion_rate": 20.1,
        "average_subscription_length": 8.3,
        "revenue": {
            "daily": 3245.67,
            "weekly": 22543.89,
            "monthly": 97654.32
        },
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }

def get_system_health():
    # Mock data for system health
    return {
        "status": "healthy",
        "api_response_time": 45,
        "storage_usage": 42,
        "cpu_usage": 38,
        "memory_usage": 45,
        "active_connections": 3245,
        "errors_last_hour": 12,
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }

def get_dashboard_stats():
    return {
        "users": get_user_stats(),
        "messages": get_message_stats(),
        "subscriptions": get_subscription_stats(),
        "system": get_system_health()
    }
