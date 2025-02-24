ALTER TABLE accounts DROP COLUMN number;
ALTER TABLE accounts DROP COLUMN pni;
ALTER TABLE accounts ADD COLUMN username VARCHAR(255) NOT NULL;
ALTER TABLE accounts ADD COLUMN password VARCHAR(255) NOT NULL;
CREATE UNIQUE INDEX accounts_username_idx ON accounts(username);
