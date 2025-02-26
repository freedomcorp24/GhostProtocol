import http.server
import socketserver
import json
import re
import os
import sys

# Add the current directory to the path
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

# Import API modules
from api.v1.auth_custom import signup, login, get_current_user
from api.users import (
    get_users, get_user, create_user, update_user, delete_user, ban_user
)
from api.subscriptions import (
    get_subscription_tiers, get_subscription_tier, create_subscription_tier,
    update_subscription_tier, delete_subscription_tier, get_user_subscription,
    update_user_subscription
)
from api.vault import (
    get_user_vault, get_vault_item, create_vault_item, update_vault_item, delete_vault_item
)
from api.analytics import (
    get_user_analytics, get_message_analytics, get_subscription_analytics,
    get_system_health, get_dashboard_stats
)

# Define the port
PORT = 8080

# Create the HTTP request handler
class GhostProtocolHandler(http.server.BaseHTTPRequestHandler):
    def _set_headers(self, status_code=200):
        self.send_response(status_code)
        self.send_header('Content-type', 'application/json')
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS')
        self.send_header('Access-Control-Allow-Headers', 'Content-Type, Authorization')
        self.end_headers()
    
    def _get_request_body(self):
        content_length = int(self.headers.get('Content-Length', 0))
        body = self.rfile.read(content_length).decode('utf-8')
        return json.loads(body) if body else {}
    
    def _send_response(self, data, status_code=200):
        self._set_headers(int(status_code) if isinstance(status_code, str) else status_code)
        self.wfile.write(json.dumps(data).encode())
    
    def do_OPTIONS(self):
        self._set_headers()
    
    def do_GET(self):
        try:
            path = self.path
            
            # Root endpoint
            if path == '/':
                self._send_response({
                    "message": "GhostProtocol API",
                    "version": "1.0.0",
                    "endpoints": [
                        "/health",
                        "/api/users",
                        "/api/users/{id}",
                        "/api/subscriptions",
                        "/api/subscriptions/{id}",
                        "/api/users/{id}/subscription",
                        "/api/users/{id}/vault",
                        "/api/users/{id}/vault/{item_id}",
                        "/api/analytics/users",
                        "/api/analytics/messages",
                        "/api/analytics/subscriptions",
                        "/api/analytics/system",
                        "/api/analytics/dashboard",
                        "/api/auth/signup",
                        "/api/auth/login",
                        "/api/auth/me"
                    ]
                })
                return
            
            # Health check endpoint
            if path == '/health':
                self._send_response({"status": "ok"})
                return
            
            # User endpoints
            users_match = re.match(r'^/api/users/?$', path)
            user_match = re.match(r'^/api/users/([^/]+)/?$', path)
            
            # Vault endpoints
            user_vault_match = re.match(r'^/api/users/([^/]+)/vault/?$', path)
            vault_item_match = re.match(r'^/api/users/([^/]+)/vault/([^/]+)/?$', path)
            
            # Subscription endpoints
            subscriptions_match = re.match(r'^/api/subscriptions/?$', path)
            subscription_match = re.match(r'^/api/subscriptions/([^/]+)/?$', path)
            user_subscription_match = re.match(r'^/api/users/([^/]+)/subscription/?$', path)
            
            # Auth endpoints
            auth_signup_match = re.match(r'^/api/auth/signup/?$', path)
            auth_login_match = re.match(r'^/api/auth/login/?$', path)
            auth_me_match = re.match(r'^/api/auth/me/?$', path)
            
            # Analytics endpoints
            analytics_users_match = re.match(r'^/api/analytics/users/?$', path)
            analytics_messages_match = re.match(r'^/api/analytics/messages/?$', path)
            analytics_subscriptions_match = re.match(r'^/api/analytics/subscriptions/?$', path)
            analytics_system_match = re.match(r'^/api/analytics/system/?$', path)
            analytics_dashboard_match = re.match(r'^/api/analytics/dashboard/?$', path)
            
            if users_match:
                self._send_response(get_users())
            elif user_match:
                user_id = user_match.group(1)
                response, status_code = get_user(user_id)
                self._send_response(response, status_code)
            elif user_vault_match:
                user_id = user_vault_match.group(1)
                self._send_response(get_user_vault(user_id))
            elif vault_item_match:
                user_id = vault_item_match.group(1)
                item_id = vault_item_match.group(2)
                response, status_code = get_vault_item(user_id, item_id)
                self._send_response(response, status_code)
            elif subscriptions_match:
                self._send_response(get_subscription_tiers())
            elif subscription_match:
                tier_id = subscription_match.group(1)
                response, status_code = get_subscription_tier(tier_id)
                self._send_response(response, status_code)
            elif user_subscription_match:
                user_id = user_subscription_match.group(1)
                self._send_response(get_user_subscription(user_id))
            elif analytics_users_match:
                self._send_response(get_user_analytics())
            elif analytics_messages_match:
                self._send_response(get_message_analytics())
            elif analytics_subscriptions_match:
                self._send_response(get_subscription_analytics())
            elif analytics_system_match:
                self._send_response(get_system_health())
            elif analytics_dashboard_match:
                self._send_response(get_dashboard_stats())
            elif auth_me_match:
                response, status_code = get_current_user(self.headers)
                self._send_response(response, status_code)
            else:
                self._send_response({"error": "Endpoint not found"}, 404)
        except Exception as e:
            self._send_response({"error": str(e)}, 500)
    
    def do_POST(self):
        try:
            path = self.path
            data = self._get_request_body()
            
            # User endpoints
            users_match = re.match(r'^/api/users/?$', path)
            
            # Vault endpoints
            user_vault_match = re.match(r'^/api/users/([^/]+)/vault/?$', path)
            
            # Auth endpoints
            auth_signup_match = re.match(r'^/api/auth/signup/?$', path)
            auth_login_match = re.match(r'^/api/auth/login/?$', path)
            
            # Subscription endpoints
            subscriptions_match = re.match(r'^/api/subscriptions/?$', path)
            
            if users_match:
                response, status_code = create_user(data)
                self._send_response(response, status_code)
            elif user_vault_match:
                user_id = user_vault_match.group(1)
                response, status_code = create_vault_item(user_id, data)
                self._send_response(response, status_code)
            elif subscriptions_match:
                response, status_code = create_subscription_tier(data)
                self._send_response(response, status_code)
            elif auth_signup_match:
                response, status_code = signup(data)
                self._send_response(response, status_code)
            elif auth_login_match:
                response, status_code = login(data)
                self._send_response(response, status_code)
            else:
                self._send_response({"error": "Endpoint not found"}, 404)
        except Exception as e:
            self._send_response({"error": str(e)}, 500)
    
    def do_PUT(self):
        try:
            path = self.path
            data = self._get_request_body()
            
            # User endpoints
            user_match = re.match(r'^/api/users/([^/]+)/?$', path)
            
            # Vault endpoints
            vault_item_match = re.match(r'^/api/users/([^/]+)/vault/([^/]+)/?$', path)
            
            # Subscription endpoints
            subscription_match = re.match(r'^/api/subscriptions/([^/]+)/?$', path)
            user_subscription_match = re.match(r'^/api/users/([^/]+)/subscription/?$', path)
            
            if user_match:
                user_id = user_match.group(1)
                response, status_code = update_user(user_id, data)
                self._send_response(response, status_code)
            elif vault_item_match:
                user_id = vault_item_match.group(1)
                item_id = vault_item_match.group(2)
                response, status_code = update_vault_item(user_id, item_id, data)
                self._send_response(response, status_code)
            elif subscription_match:
                tier_id = subscription_match.group(1)
                response, status_code = update_subscription_tier(tier_id, data)
                self._send_response(response, status_code)
            elif user_subscription_match:
                user_id = user_subscription_match.group(1)
                response, status_code = update_user_subscription(user_id, data)
                self._send_response(response, status_code)
            else:
                self._send_response({"error": "Endpoint not found"}, 404)
        except Exception as e:
            self._send_response({"error": str(e)}, 500)
    
    def do_DELETE(self):
        try:
            path = self.path
            
            # User endpoints
            user_match = re.match(r'^/api/users/([^/]+)/?$', path)
            
            # Vault endpoints
            vault_item_match = re.match(r'^/api/users/([^/]+)/vault/([^/]+)/?$', path)
            
            # Subscription endpoints
            subscription_match = re.match(r'^/api/subscriptions/([^/]+)/?$', path)
            
            if user_match:
                user_id = user_match.group(1)
                response, status_code = delete_user(user_id)
                self._send_response(response, status_code)
            elif vault_item_match:
                user_id = vault_item_match.group(1)
                item_id = vault_item_match.group(2)
                response, status_code = delete_vault_item(user_id, item_id)
                self._send_response(response, status_code)
            elif subscription_match:
                tier_id = subscription_match.group(1)
                response, status_code = delete_subscription_tier(tier_id)
                self._send_response(response, status_code)
            else:
                self._send_response({"error": "Endpoint not found"}, 404)
        except Exception as e:
            self._send_response({"error": str(e)}, 500)

# Create the HTTP server
def run_server():
    print(f"Starting GhostProtocol API server on port {PORT}...")
    httpd = socketserver.TCPServer(("", PORT), GhostProtocolHandler)
    httpd.serve_forever()

if __name__ == "__main__":
    run_server()
