# GhostProtocol Authentication System Deployment

This package contains the files needed to deploy the GhostProtocol authentication system to an EC2 instance.

## Files

- `app.py`: The FastAPI server with authentication endpoints
- `setup_users.py`: Script to initialize the users data with a default admin user
- `web/public/login.html`: The login page
- `web/public/signup.html`: The signup page
- `nginx/ghostprotocol.conf`: Nginx configuration for the authentication system
- `ghostprotocol-api.service`: Systemd service file for the API server
- `deploy.sh`: Script to deploy the authentication system

## Deployment

1. Copy this package to the EC2 instance:
   ```
   scp -r deploy/auth_system ubuntu@54.215.16.4:~/
   ```

2. SSH to the EC2 instance:
   ```
   ssh ubuntu@54.215.16.4
   ```

3. Run the deployment script:
   ```
   cd ~/auth_system
   ./deploy.sh
   ```

4. Verify the deployment:
   - Login page: http://54.215.16.4/login.html
   - Signup page: http://54.215.16.4/signup.html
   - API: http://54.215.16.4/api
   - Admin panel: http://54.215.16.4/admin

## Default Admin User

- Username: admin
- Password: admin123
