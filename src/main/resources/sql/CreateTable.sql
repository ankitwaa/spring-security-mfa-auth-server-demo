-- Postgres Query for Creating users table.

CREATE TABLE IF NOT EXISTS user_details (
id bigserial primary key,
username VARCHAR(45) NOT NULL,
password VARCHAR(100) NOT NULL,
mobile VARCHAR(10) NOT NULL,
is_account_expired VARCHAR(5),
is_account_locked VARCHAR(5),
is_credential_expired VARCHAR(5),
is_enabled VARCHAR(5) not null
);

CREATE TABLE IF NOT EXISTS user_grant_details (
id bigserial primary key,
username VARCHAR(45) NOT NULL,
authority VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS otp_details (
    id bigserial primary key,
    username VARCHAR(45) NOT NULL,
    otp VARCHAR(200) NOT NULL,
    valid_dtm DATE NOT NULL
)