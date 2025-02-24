# GhostProtocol API Documentation

## Authentication
All API endpoints require authentication using a JWT token in the Authorization header:
```
Authorization: Bearer <token>
```

## Vault Storage API

### Store Item
```http
POST /v1/vault/store
Content-Type: application/json

{
  "type": "PASSWORD|CONTACT|FILE|NOTE",
  "encryptedData": "base64_encoded_encrypted_data",
  "metadata": {
    // Type-specific metadata
  }
}

Response: 201 Created
{
  "id": "uuid",
  "createdAt": "timestamp"
}
```

### Get Item
```http
GET /v1/vault/items/{id}

Response: 200 OK
{
  "id": "uuid",
  "type": "PASSWORD|CONTACT|FILE|NOTE",
  "encryptedData": "base64_encoded_encrypted_data",
  "metadata": {},
  "createdAt": "timestamp"
}
```

### List Items
```http
GET /v1/vault/items

Response: 200 OK
{
  "items": [
    {
      "id": "uuid",
      "type": "PASSWORD|CONTACT|FILE|NOTE",
      "metadata": {},
      "createdAt": "timestamp"
    }
  ]
}
```

## Premium Subscription API

### Start Trial
```http
POST /v1/subscriptions/trial

Response: 200 OK
{
  "trialStart": "timestamp",
  "trialEnd": "timestamp",
  "storageLimit": 104857600 // 100MB in bytes
}
```

### Subscribe
```http
POST /v1/subscriptions/premium
Content-Type: application/json

{
  "paymentType": "MONTHLY|YEARLY",
  "paymentMethod": "BTC|XMR|USDT"
}

Response: 200 OK
{
  "subscriptionId": "uuid",
  "start": "timestamp",
  "end": "timestamp",
  "storageLimit": 1073741824 // 1GB in bytes
}
```

## Admin Management API

### Assign Role
```http
POST /v1/admin/roles
Content-Type: application/json

{
  "userId": "uuid",
  "role": "MASTER_ADMIN|ADMIN|SUPPORT_STAFF"
}

Response: 200 OK
```

### Ban User
```http
POST /v1/admin/users/{userId}/ban

Response: 200 OK
```

### Unban User
```http
POST /v1/admin/users/{userId}/unban

Response: 200 OK
```

## Premium Tier Management API (Master Admin Only)

### Create Tier
```http
POST /v1/admin/tiers
Content-Type: application/json

{
  "name": "string",
  "monthlyPrice": 9.99,
  "yearlyPrice": 99.99,
  "storageLimit": 1073741824 // 1GB in bytes
}

Response: 201 Created
{
  "id": "uuid"
}
```

### Update Tier
```http
PUT /v1/admin/tiers/{id}
Content-Type: application/json

{
  "name": "string",
  "monthlyPrice": 9.99,
  "yearlyPrice": 99.99,
  "storageLimit": 1073741824
}

Response: 200 OK
```

### Delete Tier
```http
DELETE /v1/admin/tiers/{id}

Response: 204 No Content
```

## Support Ticket API

### Create Ticket
```http
POST /v1/support/tickets
Content-Type: application/json

{
  "subject": "string",
  "description": "string"
}

Response: 201 Created
{
  "id": "uuid"
}
```

### Update Ticket Status
```http
PUT /v1/support/tickets/{id}/status
Content-Type: application/json

{
  "status": "OPEN|IN_PROGRESS|ESCALATED|RESOLVED|CLOSED"
}

Response: 200 OK
```

### Add Comment
```http
POST /v1/support/tickets/{id}/comments
Content-Type: application/json

{
  "content": "string",
  "internal": false
}

Response: 201 Created
{
  "id": "uuid"
}
```

## Duress System API

### Configure Duress
```http
POST /v1/security/duress/configure
Content-Type: application/json

{
  "duressPassword": "string",
  "customMessage": "string", // Optional, max 500 chars
  "emergencyContact": "uuid" // Optional
}

Response: 200 OK
```

### Activate Duress
```http
POST /v1/security/duress/activate
Content-Type: application/json

{
  "password": "string"
}

Response: 204 No Content
```

## Message Management API

### Schedule Message Deletion
```http
POST /v1/messages/{id}/delete
Content-Type: application/json

{
  "deleteAt": "timestamp"
}

Response: 200 OK
{
  "jobId": "uuid"
}
```

### Cancel Scheduled Deletion
```http
DELETE /v1/messages/{id}/delete/{jobId}

Response: 204 No Content
```
