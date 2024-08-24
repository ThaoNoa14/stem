DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;
DROP TABLE IF EXISTS roles_privileges CASCADE;
DROP TABLE IF EXISTS privileges CASCADE;

CREATE TABLE users  (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    email VARCHAR(255) UNIQUE NOT NULL,
    telephone VARCHAR(20) UNIQUE NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    gender VARCHAR(20),
    password VARCHAR(255) NOT NULL
);

CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
    users_id UUID NOT NULL REFERENCES users(id),
    roles_id UUID NOT NULL REFERENCES roles(id),
    PRIMARY KEY (users_id, roles_id)
);

CREATE TABLE privileges (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE roles_privileges (
    roles_id UUID NOT NULL REFERENCES roles(id),
    privileges_id UUID NOT NULL REFERENCES privileges(id),
    PRIMARY KEY (roles_id, privileges_id)
)