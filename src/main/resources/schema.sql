DROP TABLE user_roles IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE users IF EXISTS;

DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ START WITH 100000;

CREATE TABLE restaurant
(
    id   INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
ALTER TABLE restaurant
    ADD CONSTRAINT restaurant_unique_name_idx UNIQUE (name);

CREATE TABLE users
(
    id         INTEGER   DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
ALTER TABLE users
    ADD CONSTRAINT users_unique_email_idx UNIQUE (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE dish
(
    id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    restaurant_id INTEGER               NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    price         INTEGER               NOT NULL,
    dish_date     DATE    DEFAULT now() NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
ALTER TABLE dish
    ADD CONSTRAINT dish_unique_restaurant_name_date_idx UNIQUE (restaurant_id, name, dish_date);


CREATE TABLE vote
(
    id            INTEGER   DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    user_id       INTEGER                 NOT NULL,
    restaurant_id INTEGER                 NOT NULL,
    vote_date      DATE DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
ALTER TABLE vote
    ADD CONSTRAINT vote_unique_user_date_idx UNIQUE (user_id, vote_date);

