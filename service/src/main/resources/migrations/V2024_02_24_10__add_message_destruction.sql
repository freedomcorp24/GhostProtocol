-- Add message destruction support
ALTER TABLE messages ADD COLUMN destruction_time BIGINT;
ALTER TABLE messages ADD COLUMN is_destroyed BOOLEAN DEFAULT FALSE;
ALTER TABLE messages ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Index for finding messages that need to be destroyed
CREATE INDEX idx_messages_destruction ON messages(destruction_time) WHERE destruction_time IS NOT NULL AND is_destroyed = FALSE;
