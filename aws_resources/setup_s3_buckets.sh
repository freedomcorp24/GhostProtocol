#!/bin/bash
# Script to set up S3 buckets for GhostProtocol development environment

# Set AWS region
AWS_REGION="us-west-1"

# Define bucket names
PROFILES_BUCKET="ghostprotocol-dev-profiles"
VAULT_BUCKET="ghostprotocol-dev-vault"
MEDIA_BUCKET="ghostprotocol-dev-media"
ATTACHMENTS_BUCKET="ghostprotocol-dev-attachments"

# Create buckets if they don't exist
echo "Creating S3 buckets..."

# Create profiles bucket
if aws s3 ls "s3://$PROFILES_BUCKET" 2>&1 | grep -q 'NoSuchBucket'; then
  echo "Creating $PROFILES_BUCKET bucket..."
  aws s3 mb "s3://$PROFILES_BUCKET" --region $AWS_REGION
else
  echo "Bucket $PROFILES_BUCKET already exists."
fi

# Create vault bucket
if aws s3 ls "s3://$VAULT_BUCKET" 2>&1 | grep -q 'NoSuchBucket'; then
  echo "Creating $VAULT_BUCKET bucket..."
  aws s3 mb "s3://$VAULT_BUCKET" --region $AWS_REGION
else
  echo "Bucket $VAULT_BUCKET already exists."
fi

# Create media bucket
if aws s3 ls "s3://$MEDIA_BUCKET" 2>&1 | grep -q 'NoSuchBucket'; then
  echo "Creating $MEDIA_BUCKET bucket..."
  aws s3 mb "s3://$MEDIA_BUCKET" --region $AWS_REGION
else
  echo "Bucket $MEDIA_BUCKET already exists."
fi

# Create attachments bucket
if aws s3 ls "s3://$ATTACHMENTS_BUCKET" 2>&1 | grep -q 'NoSuchBucket'; then
  echo "Creating $ATTACHMENTS_BUCKET bucket..."
  aws s3 mb "s3://$ATTACHMENTS_BUCKET" --region $AWS_REGION
else
  echo "Bucket $ATTACHMENTS_BUCKET already exists."
fi

# Enable versioning on all buckets
echo "Enabling versioning on all buckets..."
aws s3api put-bucket-versioning --bucket $PROFILES_BUCKET --versioning-configuration Status=Enabled
aws s3api put-bucket-versioning --bucket $VAULT_BUCKET --versioning-configuration Status=Enabled
aws s3api put-bucket-versioning --bucket $MEDIA_BUCKET --versioning-configuration Status=Enabled
aws s3api put-bucket-versioning --bucket $ATTACHMENTS_BUCKET --versioning-configuration Status=Enabled

# Enable encryption on all buckets
echo "Enabling encryption on all buckets..."
aws s3api put-bucket-encryption --bucket $PROFILES_BUCKET --server-side-encryption-configuration '{"Rules": [{"ApplyServerSideEncryptionByDefault": {"SSEAlgorithm": "AES256"}}]}'
aws s3api put-bucket-encryption --bucket $VAULT_BUCKET --server-side-encryption-configuration '{"Rules": [{"ApplyServerSideEncryptionByDefault": {"SSEAlgorithm": "AES256"}}]}'
aws s3api put-bucket-encryption --bucket $MEDIA_BUCKET --server-side-encryption-configuration '{"Rules": [{"ApplyServerSideEncryptionByDefault": {"SSEAlgorithm": "AES256"}}]}'
aws s3api put-bucket-encryption --bucket $ATTACHMENTS_BUCKET --server-side-encryption-configuration '{"Rules": [{"ApplyServerSideEncryptionByDefault": {"SSEAlgorithm": "AES256"}}]}'

# Configure CORS on all buckets
echo "Configuring CORS on all buckets..."
CORS_CONFIG='{
  "CORSRules": [
    {
      "AllowedHeaders": ["*"],
      "AllowedMethods": ["GET", "PUT", "POST", "DELETE", "HEAD"],
      "AllowedOrigins": ["*"],
      "ExposeHeaders": ["ETag", "x-amz-server-side-encryption"]
    }
  ]
}'

aws s3api put-bucket-cors --bucket $PROFILES_BUCKET --cors-configuration "$CORS_CONFIG"
aws s3api put-bucket-cors --bucket $VAULT_BUCKET --cors-configuration "$CORS_CONFIG"
aws s3api put-bucket-cors --bucket $MEDIA_BUCKET --cors-configuration "$CORS_CONFIG"
aws s3api put-bucket-cors --bucket $ATTACHMENTS_BUCKET --cors-configuration "$CORS_CONFIG"

# Configure public access for media bucket (for profile pictures, etc.)
echo "Configuring public access for media bucket..."
MEDIA_BUCKET_POLICY='{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::'$MEDIA_BUCKET'/*"
    }
  ]
}'

aws s3api put-bucket-policy --bucket $MEDIA_BUCKET --policy "$MEDIA_BUCKET_POLICY"

# Block public access for sensitive buckets
echo "Blocking public access for sensitive buckets..."
aws s3api put-public-access-block --bucket $PROFILES_BUCKET --public-access-block-configuration "BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true"
aws s3api put-public-access-block --bucket $VAULT_BUCKET --public-access-block-configuration "BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true"
aws s3api put-public-access-block --bucket $ATTACHMENTS_BUCKET --public-access-block-configuration "BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=true,RestrictPublicBuckets=true"

echo "S3 bucket setup completed successfully!"
echo ""
echo "Bucket Information:"
echo "==================="
echo "Profiles Bucket: $PROFILES_BUCKET"
echo "Vault Bucket: $VAULT_BUCKET"
echo "Media Bucket: $MEDIA_BUCKET"
echo "Attachments Bucket: $ATTACHMENTS_BUCKET"
echo ""
echo "All buckets have versioning enabled, AES256 encryption, and CORS configured."
echo "Media bucket allows public read access for profile pictures and other public media."
echo "Sensitive buckets (profiles, vault, attachments) have public access blocked."
