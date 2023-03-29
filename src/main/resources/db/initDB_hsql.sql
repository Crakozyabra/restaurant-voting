-- TODO: make hsqldb init

/*DROP TABLE user_role IF EXISTS;
DROP TABLE menu_item IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE restaurant IF EXISTS;

CREATE TABLE users
(
    id    INTEGER IDENTITY PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE user_role
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE menu_item
(
    restaurant_id  INTEGER      NOT NULL,
    is_visible     BOOLEAN      NOT NULL,
    menu_item_name VARCHAR(255) NOT NULL,
    price          DECIMAL      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            INTEGER IDENTITY PRIMARY KEY,
    user_id       INTEGER UNIQUE          NOT NULL,
    restaurant_id INTEGER                 NOT NULL,
    voting_date   TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);*/


/*
CREATE UNIQUE INDEX meal_unique_user_datetime_idx
    ON meal (user_id, date_time)*/
