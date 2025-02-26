from flask import Blueprint, jsonify, request
import json
import os
import random
from datetime import datetime, timedelta

analytics_bp = Blueprint('analytics', __name__)

# Helper function to generate mock analytics data
def generate_mock_analytics():
    # Generate data for the last 30 days
    end_date = datetime.now()
    start_date = end_date - timedelta(days=30)
    
    # Daily user activity
    daily_activity = []
    current_date = start_date
    active_users = random.randint(100, 200)
    
    while current_date <= end_date:
        # Simulate some growth and fluctuation
        change = random.randint(-10, 20)
        active_users += change
        active_users = max(100, active_users)  # Ensure at least 100 active users
        
        daily_activity.append({
            "date": current_date.strftime("%Y-%m-%d"),
            "active_users": active_users,
            "messages_sent": random.randint(active_users * 5, active_users * 15),
            "new_users": random.randint(5, 25)
        })
        
        current_date += timedelta(days=1)
    
    # Subscription statistics
    subscription_stats = {
        "total_users": random.randint(1000, 2000),
        "free_users": random.randint(700, 1500),
        "premium_users": random.randint(200, 500),
        "enterprise_users": random.randint(50, 200)
    }
    
    # Storage usage
    storage_usage = {
        "total_storage_used": random.randint(50000000000, 200000000000),  # 50GB to 200GB
        "available_storage": random.randint(800000000000, 1000000000000),  # 800GB to 1TB
        "storage_by_type": {
            "messages": random.randint(10000000000, 50000000000),  # 10GB to 50GB
            "media": random.randint(20000000000, 100000000000),  # 20GB to 100GB
            "vault": random.randint(10000000000, 30000000000),  # 10GB to 30GB
            "other": random.randint(5000000000, 20000000000)  # 5GB to 20GB
        }
    }
    
    # Feature usage
    feature_usage = {
        "messaging": random.randint(80, 100),  # Percentage of users
        "group_chat": random.randint(60, 90),
        "screen_sharing": random.randint(30, 70),
        "video_calls": random.randint(40, 80),
        "voice_calls": random.randint(50, 90),
        "vault": random.randint(20, 60)
    }
    
    # System health
    system_health = {
        "cpu_usage": random.randint(20, 80),  # Percentage
        "memory_usage": random.randint(30, 90),
        "disk_usage": random.randint(40, 70),
        "network_traffic": random.randint(100, 1000),  # Mbps
        "api_response_time": random.randint(50, 200)  # ms
    }
    
    return {
        "daily_activity": daily_activity,
        "subscription_stats": subscription_stats,
        "storage_usage": storage_usage,
        "feature_usage": feature_usage,
        "system_health": system_health,
        "generated_at": datetime.now().isoformat()
    }

# Get analytics dashboard data
@analytics_bp.route('/dashboard', methods=['GET'])
def get_dashboard():
    analytics_data = generate_mock_analytics()
    return jsonify(analytics_data)

# Get user activity data
@analytics_bp.route('/user-activity', methods=['GET'])
def get_user_activity():
    analytics_data = generate_mock_analytics()
    return jsonify({"daily_activity": analytics_data['daily_activity']})

# Get subscription statistics
@analytics_bp.route('/subscriptions', methods=['GET'])
def get_subscription_stats():
    analytics_data = generate_mock_analytics()
    return jsonify({"subscription_stats": analytics_data['subscription_stats']})

# Get storage usage data
@analytics_bp.route('/storage', methods=['GET'])
def get_storage_usage():
    analytics_data = generate_mock_analytics()
    return jsonify({"storage_usage": analytics_data['storage_usage']})

# Get feature usage data
@analytics_bp.route('/features', methods=['GET'])
def get_feature_usage():
    analytics_data = generate_mock_analytics()
    return jsonify({"feature_usage": analytics_data['feature_usage']})

# Get system health data
@analytics_bp.route('/system-health', methods=['GET'])
def get_system_health():
    analytics_data = generate_mock_analytics()
    return jsonify({"system_health": analytics_data['system_health']})
