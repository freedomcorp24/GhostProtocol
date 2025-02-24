CREATE TABLE admin_roles (
    user_id UUID PRIMARY KEY,
    role VARCHAR(255) NOT NULL,
    assigned_by UUID NOT NULL,
    assigned_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_admin_roles_assigned_by ON admin_roles(assigned_by);
CREATE INDEX idx_admin_roles_assigned_at ON admin_roles(assigned_at);
