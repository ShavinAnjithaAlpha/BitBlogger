CREATE SEQUENCE IF NOT EXISTS users_seq
AS BIGINT
START 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE IF NOT EXISTS users(
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('users_seq'),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(1024) NOT NULL,
    salt VARCHAR(50) NOT NULL,
    hashing_algorithm VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NULL,
    last_login TIMESTAMP NULL,
    status VARCHAR(50) NOT NULL,
    locked BOOLEAN NULL DEFAULT FALSE,
    failure_login_attempts INT NULL DEFAULT 0,
    oauth_provider VARCHAR(50) NULL,
    oauth_id VARCHAR(255) NULL
);

CREATE SEQUENCE IF NOT EXISTS roles_seq
    AS INT
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS roles (
    id INT UNIQUE PRIMARY KEY DEFAULT nextval('roles_seq'),
    name VARCHAR(25) NOT NULL,
    description VARCHAR(1024) NOT NULL
);


CREATE SEQUENCE IF NOT EXISTS user_roles_seq
    AS BIGINT
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS user_roles (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('user_roles_seq'),
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

/* to keep email verification tokens separately */
CREATE SEQUENCE IF NOT EXISTS email_token_seq
    AS BIGINT
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS email_verification_token (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('email_token_seq'),
    user_id BIGINT NOT NULL,
    token VARCHAR(1024) NOT NULL,
    expires_at TIMESTAMP NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

/* to manage and keep password reset tokens  */
CREATE SEQUENCE IF NOT EXISTS reset_token_seq
    AS BIGINT
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS password_reset_token (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('reset_token_seq'),
    user_id BIGINT NOT NULL,
    token VARCHAR(1024) NOT NULL,
    expires_at TIMESTAMP NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id)
);

/* table for keep audit details of the user's activities */
CREATE SEQUENCE IF NOT EXISTS audit_log_seq
    AS BIGINT
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS audit_log (
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('audit_log_seq'),
    user_id BIGINT NOT NULL,
    event VARCHAR(100) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    details TEXT NULL
);