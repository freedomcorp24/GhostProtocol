CREATE TABLE vault_items (
    user_id UUID NOT NULL,
    item_id UUID NOT NULL,
    encrypted_data BYTEA NOT NULL,
    type VARCHAR(255) NOT NULL,
    size BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, item_id)
);

CREATE INDEX idx_vault_items_user_id ON vault_items(user_id);
CREATE INDEX idx_vault_items_created_at ON vault_items(created_at);
