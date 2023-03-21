DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant;

/*show timezone;*/
SET TIME ZONE 'Europe/Moscow';


CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE role
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
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
    id             SERIAL PRIMARY KEY,
    restaurant_id  INTEGER          NOT NULL,
    menu_item_name VARCHAR(255)     NOT NULL,
    price          DOUBLE PRECISION NOT NULL,
    is_visible     BOOLEAN          NOT NULL,
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


/*
CREATE UNIQUE INDEX meal_unique_user_datetime_idx
    ON meal (user_id, date_time)*/
