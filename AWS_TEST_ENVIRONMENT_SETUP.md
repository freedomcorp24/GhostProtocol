# Setting Up AWS Test Environment for GhostProtocol

This document contains instructions for setting up an AWS test environment for GhostProtocol.

## Prerequisites

- AWS account with appropriate permissions
- AWS CLI installed and configured
- EC2 instance with the GhostProtocol code

## Setting Up the Test Environment

1. **Launch an EC2 Instance**:
   ```bash
   aws ec2 run-instances \
     --image-id ami-0c55b159cbfafe1f0 \
     --instance-type t2.micro \
     --key-name YourKeyPair \
     --security-group-ids sg-12345678 \
     --subnet-id subnet-12345678 \
     --tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=GhostProtocol-Test}]'
   ```

2. **Connect to the EC2 Instance**:
   ```bash
   ssh -i YourKeyPair.pem ec2-user@your-instance-public-ip
   ```

3. **Install Required Software**:
   ```bash
   sudo yum update -y
   sudo yum install -y git nodejs npm
   ```

4. **Clone the GhostProtocol Repository**:
   ```bash
   git clone https://github.com/freedomcorp24/GhostProtocol.git
   cd GhostProtocol
   ```

5. **Set Up the Test Environments**:
   ```bash
   mkdir -p test_environments/web/public
   mkdir -p test_environments/ios/public
   mkdir -p test_environments/android/public
   mkdir -p test_environments/server/config
   ```

6. **Create the Test Environment Files**:
   - Copy the test environment files from your local machine to the EC2 instance
   - Start the test environment servers

7. **Create an Application Load Balancer**:
   ```bash
   # Create target groups
   aws elbv2 create-target-group \
     --name GhostProtocol-Web \
     --protocol HTTP \
     --port 3000 \
     --vpc-id vpc-12345678 \
     --health-check-path / \
     --health-check-protocol HTTP

   aws elbv2 create-target-group \
     --name GhostProtocol-iOS \
     --protocol HTTP \
     --port 3001 \
     --vpc-id vpc-12345678 \
     --health-check-path / \
     --health-check-protocol HTTP

   aws elbv2 create-target-group \
     --name GhostProtocol-Android \
     --protocol HTTP \
     --port 3002 \
     --vpc-id vpc-12345678 \
     --health-check-path / \
     --health-check-protocol HTTP

   # Register targets
   aws elbv2 register-targets \
     --target-group-arn arn:aws:elasticloadbalancing:region:account-id:targetgroup/GhostProtocol-Web/12345678 \
     --targets Id=i-12345678,Port=3000

   aws elbv2 register-targets \
     --target-group-arn arn:aws:elasticloadbalancing:region:account-id:targetgroup/GhostProtocol-iOS/12345678 \
     --targets Id=i-12345678,Port=3001

   aws elbv2 register-targets \
     --target-group-arn arn:aws:elasticloadbalancing:region:account-id:targetgroup/GhostProtocol-Android/12345678 \
     --targets Id=i-12345678,Port=3002

   # Create load balancer
   aws elbv2 create-load-balancer \
     --name GhostProtocol-LB \
     --subnets subnet-12345678 subnet-87654321 \
     --security-groups sg-12345678

   # Create listeners
   aws elbv2 create-listener \
     --load-balancer-arn arn:aws:elasticloadbalancing:region:account-id:loadbalancer/app/GhostProtocol-LB/12345678 \
     --protocol HTTP \
     --port 80 \
     --default-actions Type=forward,TargetGroupArn=arn:aws:elasticloadbalancing:region:account-id:targetgroup/GhostProtocol-Web/12345678

   aws elbv2 create-listener \
     --load-balancer-arn arn:aws:elasticloadbalancing:region:account-id:loadbalancer/app/GhostProtocol-LB/12345678 \
     --protocol HTTP \
     --port 81 \
     --default-actions Type=forward,TargetGroupArn=arn:aws:elasticloadbalancing:region:account-id:targetgroup/GhostProtocol-iOS/12345678

   aws elbv2 create-listener \
     --load-balancer-arn arn:aws:elasticloadbalancing:region:account-id:loadbalancer/app/GhostProtocol-LB/12345678 \
     --protocol HTTP \
     --port 82 \
     --default-actions Type=forward,TargetGroupArn=arn:aws:elasticloadbalancing:region:account-id:targetgroup/GhostProtocol-Android/12345678
   ```

8. **Access the Test Environments**:
   - Web: http://GhostProtocol-LB-12345678.region.elb.amazonaws.com
   - iOS: http://GhostProtocol-LB-12345678.region.elb.amazonaws.com:81
   - Android: http://GhostProtocol-LB-12345678.region.elb.amazonaws.com:82

## Notes

- Replace placeholders like `YourKeyPair`, `sg-12345678`, `subnet-12345678`, etc. with your actual values
- Make sure the security groups allow traffic on ports 3000, 3001, and 3002
- The load balancer DNS name will be different for your setup
