package org.ghostprotocol.service.admin;

public enum AdminRole {
    MASTER_ADMIN,  // Can do everything including managing other admins
    ADMIN,         // Can ban/unban users
    SUPPORT_STAFF  // Can only handle support tickets
}
