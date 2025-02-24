CREATE TABLE premium_tiers (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    monthly_price DECIMAL(10,2) NOT NULL,
    yearly_price DECIMAL(10,2) NOT NULL,
    storage_limit BIGINT NOT NULL,
    created_by UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE user_subscriptions (
    user_id UUID PRIMARY KEY,
    tier_id UUID NOT NULL REFERENCES premium_tiers(id),
    trial_used BOOLEAN NOT NULL DEFAULT FALSE,
    trial_start TIMESTAMP,
    subscription_start TIMESTAMP,
    subscription_end TIMESTAMP,
    payment_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_premium_tiers_created_by ON premium_tiers(created_by);
CREATE INDEX idx_user_subscriptions_tier_id ON user_subscriptions(tier_id);
