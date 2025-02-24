-- Remove phone number dependency and enforce username-based authentication
ALTER TABLE accounts 
  DROP COLUMN phone_number,
  DROP COLUMN phone_number_identifier,
  ALTER COLUMN username_hash SET NOT NULL,
  ADD COLUMN public_username_hash BYTEA NOT NULL,
  ADD UNIQUE (public_username_hash);

-- Add indexes for username lookups
CREATE INDEX idx_accounts_username_hash ON accounts(username_hash);
CREATE INDEX idx_accounts_public_username_hash ON accounts(public_username_hash);

-- Add columns for biometric authentication
ALTER TABLE devices
  ADD COLUMN biometric_enabled BOOLEAN DEFAULT FALSE,
  ADD COLUMN biometric_key_hash BYTEA;

-- Add columns for 2FA configuration
ALTER TABLE accounts
  ADD COLUMN two_factor_type VARCHAR(32),
  ADD COLUMN two_factor_secret BYTEA,
  ADD COLUMN backup_codes BYTEA[];

-- Add constraints
ALTER TABLE accounts
  ADD CONSTRAINT chk_two_factor_type 
  CHECK (two_factor_type IN ('PATTERN', 'PASSWORD', 'TOTP', NULL));

-- Update existing accounts to require username
UPDATE accounts SET username_hash = uuid_generate_v4() 
WHERE username_hash IS NULL;
