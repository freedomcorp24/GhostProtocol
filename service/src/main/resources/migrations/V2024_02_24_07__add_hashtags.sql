CREATE TABLE message_hashtags (
    message_id UUID NOT NULL,
    hashtag VARCHAR NOT NULL,
    group_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (message_id, hashtag)
);

CREATE INDEX idx_message_hashtags_group ON message_hashtags(group_id, hashtag);
