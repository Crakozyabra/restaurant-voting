DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant;

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled  BOOL         NOT NULL DEFAULT true
);

CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE role
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_constraint UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT unique_restaurant_name UNIQUE (name)
);

CREATE TABLE menu
(
    id            SERIAL PRIMARY KEY,
    restaurant_id INTEGER          NOT NULL,
    name          VARCHAR(255)     NOT NULL,
    price         DOUBLE PRECISION NOT NULL,
    enabled       BOOL             NOT NULL DEFAULT true,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            SERIAL PRIMARY KEY,
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    voting_date   DATE    NOT NULL,
    voting_time   TIME    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    CONSTRAINT user_date_unique_constraint UNIQUE (user_id, voting_date)
);
