# Commands to deploy the authentication system to the EC2 instance

# Copy the deployment package to the EC2 instance
scp -r deploy/auth_deployment.tar.gz ubuntu@54.215.16.4:~/

# SSH to the EC2 instance and run the deployment script
ssh ubuntu@54.215.16.4 << 'ENDSSH'
mkdir -p ~/auth_system
tar -xzvf ~/auth_deployment.tar.gz -C ~/
cd ~/auth_system
./deploy.sh
ENDSSH
