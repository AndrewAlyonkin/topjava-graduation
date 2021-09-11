DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM dish;
DELETE
FROM restaurant;
DELETE
FROM vote;

alter sequence global_seq restart with 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO restaurant (name)
VALUES ('Bison'),
       ('Prestige'),
       ('Golden');

INSERT INTO dish (name, price, restaurant_id)
VALUES ('Current date soup Bison', '500', 100002),
       ('Current date soup Prestige', '600', 100003),
       ('Current date soup Golden', '700', 100004),
       ('Current date salad Bison', '500', 100002),
       ('Current date salad Prestige', '600', 100003),
       ('Current date salad Golden', '700', 100004),
       ('Current date meat Bison', '500', 100002),
       ('Current date meat Prestige', '600', 100003),
       ('Current date meat Golden', '700', 100004),
       ('Current date dessert Bison', '500', 100002),
       ('Current date dessert Prestige', '600', 100003),
       ('Current date dessert Golden', '700', 100004);

INSERT INTO dish (name, price, restaurant_id, dish_date)
VALUES ('Soup Bison', '100', 100002, '2021-08-18'),
       ('Soup Prestige', '200', 100003, '2021-08-18'),
       ('Soup Golden', '300', 100004, '2021-08-19'),
       ('Salad Bison', '100', 100002, '2021-08-18'),
       ('Salad Prestige', '200', 100003, '2021-08-18'),
       ('Salad Golden', '300', 100004, '2021-08-19'),
       ('Meat Bison', '100', 100002, '2021-08-18'),
       ('Meat Prestige', '200', 100003, '2021-08-18'),
       ('Meat Golden', '300', 100004, '2021-08-19'),
       ('Dessert Bison', '100', 100002, '2021-08-18'),
       ('Dessert Prestige', '200', 100003, '2021-08-19'),
       ('Dessert Golden', '300', 100004, '2021-08-18');

INSERT INTO vote (user_id, restaurant_id, datetime)
VALUES (100000, 100002, '2021-08-18 10:00:00'),
       (100000, 100004, '2021-08-19 10:00:00'),
       (100001, 100003, '2021-08-18 10:00:00'),
       (100001, 100004, '2021-08-19 10:00:00');

INSERT INTO vote (user_id, restaurant_id) VALUES (100000, 100002);
