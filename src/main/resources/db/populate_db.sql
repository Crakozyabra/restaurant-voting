DELETE
FROM restaurant;
DELETE
FROM users;
DELETE
FROM role;
DELETE
FROM menu;

SET TIME ZONE 'Europe/Moscow';

INSERT INTO restaurant (name)
VALUES ('Italian restaurant'),
       ('France restaurant'),
       ('Russian restaurant');

INSERT INTO users (name, email)
VALUES ('Anton', 'anton@mail.com'),
       ('Elena', 'elena@mail.com'),
       ('Boris', 'boris@mail.com'),
       ('Daria', 'daria@mail.com'),
       ('Maxim', 'maxim@mail.com');

INSERT INTO role(user_id, role)
VALUES (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'USER'),
       (4, 'USER'),
       (5, 'USER');

INSERT INTO menu (restaurant_id, menu_item_name, price, is_visible)
VALUES (1, 'pizza', 106.5, 'true'),
       (1, 'risotto', 201.4, 'true'),
       (1, 'lasagne', 302.3, 'true'),
       (1, 'ravioli', 403.2, 'true'),
       (1, 'bryschetta', 503.1, 'true'),

       (2, 'Creme brulee', 212.1, 'true'),
       (2, 'Onion soup', 162.1, 'true'),
       (2, 'Souffle', 812.1, 'true'),
       (2, 'Croissant', 312.1, 'true'),
       (2, 'Duck confit', 112.1, 'true'),

       (3, 'Щи', 212.1, 'true'),
       (3, 'Блины', 162.1, 'true'),
       (3, 'Картошка с курицей', 812.1, 'true'),
       (3, 'Голубцы', 312.1, 'true'),
       (3, 'Холодец', 112.1, 'true');

INSERT INTO vote (user_id, restaurant_id, voting_date, voting_time)
VALUES (1, 1, current_date, current_time),
       (2, 1, current_date, current_time),
       (3, 1, current_date, current_time),
       (4, 2, current_date, current_time),
       (5, 2, current_date, current_time);

INSERT INTO supplier (name)
VALUES ('Milks products'),
       ('Milks products other supplier'),
       ('Meats products'),
       ('Meats products other supplier'),
       ('Vegetables'),
       ('Vegetables other supplier'),
       ('Flour ideas'),
       ('Flour ideas other supplier'),
       ('Cutlery supplier'),
       ('Other supplier'),
       ('Fruits');

INSERT INTO restaurant_supplier (restaurant_id, supplier_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 3),
       (3, 4),
       (3, 5);