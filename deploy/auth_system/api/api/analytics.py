import json
import os
import datetime
import random

# Helper function to load analytics data
def load_analytics_data():
    try:
        os.makedirs('static/data', exist_ok=True)
        with open('static/data/analytics.json', 'r') as f:
            return json.load(f)
    except (FileNotFoundError, json.JSONDecodeError):
        # Generate mock analytics data
        default_analytics = generate_mock_analytics()
        save_analytics_data(default_analytics)
        return default_analytics

# Helper function to save analytics data
def save_analytics_data(analytics_data):
    os.makedirs('static/data', exist_ok=True)
    with open('static/data/analytics.json', 'w') as f:
        json.dump(analytics_data, f, indent=2)

# Generate mock analytics data
def generate_mock_analytics():
    today = datetime.datetime.now()
    
    # Generate daily data for the past 30 days
    daily_data = []
    for i in range(30):
        day = today - datetime.timedelta(days=i)
        day_str = day.strftime('%Y-%m-%d')
        
        daily_data.append({
            "date": day_str,
            "new_users": random.randint(10, 100),
            "active_users": random.randint(100, 1000),
            "messages_sent": random.randint(1000, 10000),
            "calls_made": random.randint(50, 500),
            "screen_shares": random.randint(10, 100),
            "files_shared": random.randint(20, 200),
            "premium_conversions": random.randint(1, 20)
        })
    
    # Generate subscription data
    subscription_data = {
        "total_subscribers": random.randint(500, 2000),
        "free_users": random.randint(5000, 10000),
        "premium_users": random.randint(300, 1000),
        "enterprise_users": random.randint(50, 500),
        "monthly_revenue": random.uniform(5000, 20000),
        "churn_rate": random.uniform(1, 5)
    }
    
    # Generate system health data
    system_health = {
        "cpu_usage": random.uniform(10, 80),
        "memory_usage": random.uniform(20, 70),
        "disk_usage": random.uniform(30, 60),
        "api_response_time": random.uniform(50, 200),
        "error_rate": random.uniform(0.1, 2),
        "uptime": random.uniform(99, 99.99)
    }
    
    return {
        "daily_data": daily_data,
        "subscription_data": subscription_data,
        "system_health": system_health
    }

# Get user analytics
def get_user_analytics():
    analytics_data = load_analytics_data()
    
    # Extract user-related metrics from daily data
    user_metrics = []
    for day in analytics_data['daily_data']:
        user_metrics.append({
            "date": day['date'],
            "new_users": day['new_users'],
            "active_users": day['active_users']
        })
    
    return {
        "daily_metrics": user_metrics,
        "total_users": analytics_data['subscription_data']['free_users'] + 
                      analytics_data['subscription_data']['premium_users'] + 
                      analytics_data['subscription_data']['enterprise_users'],
        "active_users_today": analytics_data['daily_data'][0]['active_users'],
        "new_users_today": analytics_data['daily_data'][0]['new_users'],
        "user_growth_rate": random.uniform(1, 10)
    }

# Get message analytics
def get_message_analytics():
    analytics_data = load_analytics_data()
    
    # Extract message-related metrics from daily data
    message_metrics = []
    for day in analytics_data['daily_data']:
        message_metrics.append({
            "date": day['date'],
            "messages_sent": day['messages_sent'],
            "files_shared": day['files_shared']
        })
    
    return {
        "daily_metrics": message_metrics,
        "total_messages_today": analytics_data['daily_data'][0]['messages_sent'],
        "total_files_today": analytics_data['daily_data'][0]['files_shared'],
        "average_messages_per_user": analytics_data['daily_data'][0]['messages_sent'] / 
                                    max(1, analytics_data['daily_data'][0]['active_users']),
        "peak_hour": f"{random.randint(19, 22)}:00"
    }

# Get subscription analytics
def get_subscription_analytics():
    analytics_data = load_analytics_data()
    
    # Extract subscription-related metrics from daily data
    subscription_metrics = []
    for day in analytics_data['daily_data']:
        subscription_metrics.append({
            "date": day['date'],
            "premium_conversions": day['premium_conversions']
        })
    
    return {
        "daily_metrics": subscription_metrics,
        "subscription_data": analytics_data['subscription_data'],
        "conversion_rate": (analytics_data['subscription_data']['premium_users'] + 
                          analytics_data['subscription_data']['enterprise_users']) / 
                          max(1, analytics_data['subscription_data']['free_users']) * 100,
        "average_revenue_per_user": analytics_data['subscription_data']['monthly_revenue'] / 
                                  max(1, analytics_data['subscription_data']['total_subscribers'])
    }

# Get system health
def get_system_health():
    analytics_data = load_analytics_data()
    return analytics_data['system_health']

# Get dashboard stats
def get_dashboard_stats():
    analytics_data = load_analytics_data()
    
    return {
        "total_users": analytics_data['subscription_data']['free_users'] + 
                      analytics_data['subscription_data']['premium_users'] + 
                      analytics_data['subscription_data']['enterprise_users'],
        "premium_users": analytics_data['subscription_data']['premium_users'] + 
                        analytics_data['subscription_data']['enterprise_users'],
        "active_users_today": analytics_data['daily_data'][0]['active_users'],
        "messages_today": analytics_data['daily_data'][0]['messages_sent'],
        "calls_today": analytics_data['daily_data'][0]['calls_made'],
        "screen_shares_today": analytics_data['daily_data'][0]['screen_shares'],
        "files_shared_today": analytics_data['daily_data'][0]['files_shared'],
        "monthly_revenue": analytics_data['subscription_data']['monthly_revenue'],
        "system_health": analytics_data['system_health']
    }
