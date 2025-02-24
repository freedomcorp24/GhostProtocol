CREATE TABLE message_deletion_jobs (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    message_id UUID NOT NULL,
    scheduled_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE message_deletion_history (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    message_id UUID NOT NULL,
    deleted_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_message_deletion_jobs_user_id ON message_deletion_jobs(user_id);
CREATE INDEX idx_message_deletion_jobs_scheduled_at ON message_deletion_jobs(scheduled_at);
CREATE INDEX idx_message_deletion_history_user_id ON message_deletion_history(user_id);
