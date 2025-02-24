CREATE TABLE payment_addresses (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    currency VARCHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    amount DECIMAL(20,8) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL
);

CREATE TABLE payment_transactions (
    id UUID PRIMARY KEY,
    payment_address_id UUID NOT NULL REFERENCES payment_addresses(id),
    transaction_hash VARCHAR(255) NOT NULL,
    amount DECIMAL(20,8) NOT NULL,
    confirmations INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_payment_addresses_user_id ON payment_addresses(user_id);
CREATE INDEX idx_payment_addresses_status ON payment_addresses(status);
CREATE INDEX idx_payment_addresses_expires_at ON payment_addresses(expires_at);
CREATE INDEX idx_payment_transactions_payment_address_id ON payment_transactions(payment_address_id);
