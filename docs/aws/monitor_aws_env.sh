#!/bin/bash
# Script to monitor GhostProtocol AWS development environment

# Set variables
REGION="us-west-1"
STACK_NAME="ghostprotocol-dev"
EC2_INSTANCE_ID="i-0123456789abcdef0"

# Check EC2 instance status
echo "Checking EC2 instance status..."
aws ec2 describe-instance-status \
  --instance-ids $EC2_INSTANCE_ID \
  --region $REGION

# Check CloudWatch metrics for CPU utilization
echo "Checking CPU utilization..."
aws cloudwatch get-metric-statistics \
  --namespace AWS/EC2 \
  --metric-name CPUUtilization \
  --dimensions Name=InstanceId,Value=$EC2_INSTANCE_ID \
  --start-time $(date -u -d '1 hour ago' +%Y-%m-%dT%H:%M:%SZ) \
  --end-time $(date -u +%Y-%m-%dT%H:%M:%SZ) \
  --period 300 \
  --statistics Average \
  --region $REGION

# Check S3 bucket sizes
echo "Checking S3 bucket sizes..."
aws s3 ls s3://ghostprotocol-dev-profiles --summarize --region $REGION
aws s3 ls s3://ghostprotocol-dev-vault --summarize --region $REGION
aws s3 ls s3://ghostprotocol-dev-media --summarize --region $REGION
aws s3 ls s3://ghostprotocol-dev-attachments --summarize --region $REGION

# Check DynamoDB table status
echo "Checking DynamoDB table status..."
aws dynamodb describe-table \
  --table-name ghostprotocol-dev-accounts \
  --region $REGION

# Check application logs
echo "Checking application logs..."
ssh -i ~/.ssh/ghostprotocol-dev-key.pem ec2-user@54.123.45.67 "tail -n 50 /var/log/ghostprotocol/service.log"

echo "Monitoring complete!"
