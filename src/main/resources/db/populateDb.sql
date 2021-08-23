DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM dish;
DELETE
FROM restaraunt;
DELETE
FROM vote;

alter sequence global_seq restart with 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restaurant (name)
VALUES ('Bison'),
       ('Prestige'),
       ('Golden rain'),
       ('Millionaire'),
       ('Point');

INSERT INTO dish (name, price, restaurant_id)
VALUES ('Current date soup Bison', '500', 100002),
       ('Current date soup Prestige', '600', 100003),
       ('Current date soup Golden Rain', '700', 100004),
       ('Current date soup Millionaire', '800', 100005),
       ('Current date soup Point', '900', 100006),
       ('Current date salad Bison', '500', 100002),
       ('Current date salad Prestige', '600', 100003),
       ('Current date salad Golden Rain', '700', 100004),
       ('Current date salad Millionaire', '800', 100005),
       ('Current date salad Point', '900', 100006),
       ('Current date meat Bison', '500', 100002),
       ('Current date meat Prestige', '600', 100003),
       ('Current date meat Golden rain', '700', 100004),
       ('Current date meat Millionaire', '800', 100005),
       ('Current date meat Point', '900', 100006),
       ('Current date dessert Bison', '500', 100002),
       ('Current date dessert Prestige', '600', 100003),
       ('Current date dessert Golden rain', '700', 100004),
       ('Current date dessert Millionaire', '800', 100005),
       ('Current date dessert Point', '900', 100006);

INSERT INTO dish (name, price, restaurant_id, dish_date)
VALUES ('Soup Bison', '100', 100002, '2021-08-18'),
       ('Soup Prestige', '200', 100003, '2021-08-18'),
       ('Soup Golden rain', '300', 100004, '2021-08-19'),
       ('Soup Millionaire', '400', 100005, '2021-08-18'),
       ('Soup Point', '500', 100006, '2021-08-19'),
       ('Salad Bison', '100', 100002, '2021-08-18'),
       ('Salad Prestige', '200', 100003, '2021-08-18'),
       ('Salad Golden rain', '300', 100004, '2021-08-19'),
       ('Salad Millionaire', '400', 100005, '2021-08-18'),
       ('Salad Point', '500', 100006, '2021-08-19'),
       ('Meat Bison', '100', 100002, '2021-08-18'),
       ('Meat Prestige', '200', 100003, '2021-08-18'),
       ('Meat Golden rain', '300', 100004, '2021-08-19'),
       ('Meat Millionaire', '400', 100005, '2021-08-19'),
       ('Meat Point', '500', 100006, '2021-08-19'),
       ('Meat Bison', '100', 100002, '2021-08-18'),
       ('Meat Prestige', '200', 100003, '2021-08-18'),
       ('Meat Golden rain', '300', 100004, '2021-08-19'),
       ('Meat Millionaire', '400', 100005, '2021-08-18'),
       ('Meat Point', '500', 100006, '2021-08-19'),
       ('Dessert Bison', '100', 100002, '2021-08-18'),
       ('Dessert Prestige', '200', 100003, '2021-08-19'),
       ('Dessert Golden rain', '300', 100004, '2021-08-18'),
       ('Dessert Millionaire', '400', 100005, '2021-08-19'),
       ('Dessert Point', '500', 100006, '2021-08-19');

INSERT INTO vote (user_id, restaurant_id, vote_datetime)
VALUES (100000, 100002, '2021-08-18 10:00:00'),
       (100000, 100005, '2021-08-19 10:00:00'),
       (100001, 100003, '2021-08-18 10:00:00'),
       (100001, 100006, '2021-08-19 10:00:00');
