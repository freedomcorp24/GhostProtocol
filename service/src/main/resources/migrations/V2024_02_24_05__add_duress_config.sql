CREATE TABLE duress_config (
    user_id UUID PRIMARY KEY,
    duress_password VARCHAR(255) NOT NULL,
    custom_message TEXT,
    emergency_contact UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE decoy_contacts (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE decoy_messages (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    contact_id UUID NOT NULL REFERENCES decoy_contacts(id),
    content TEXT NOT NULL,
    sent_by_user BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_decoy_contacts_user_id ON decoy_contacts(user_id);
CREATE INDEX idx_decoy_messages_user_id ON decoy_messages(user_id);
CREATE INDEX idx_decoy_messages_contact_id ON decoy_messages(contact_id);
