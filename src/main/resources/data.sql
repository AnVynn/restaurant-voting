INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User2', 'user2@gmail.com', '{noop}password2');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3);

INSERT INTO RESTAURANT (name, description)
VALUES ('Kryjivka', 'Ukrainian restaurant serving only ukrainian food, not pelmeni'),
       ('NOA', 'Asian restaurant serving sushi, ramen and poke'),
       ('Green Caffe', 'Vegetarian restaurant serving vegetarian, vegan, raw food, gluten-free and sugar-free sweets'),
       ('Gas Lamp', 'Ukrainian restaurant-museum serving chemical experiments'),
       ('Ramen Mo', 'Japanese restaurant serving ramen, wok and gyoza');

INSERT INTO DISH (description, menu_per_day, price, restaurant_id)
VALUES ('Pickled peppers salad with white radish', CURRENT_DATE, 96, 1),
       ('Minced Beef Steak with roasted oyster mushrooms and pepper', CURRENT_DATE, 168, 1),
       ('Zenyk from Lviv', CURRENT_DATE, 48, 1),
       ('Nihoshi ramen with teriyaki chicken', CURRENT_DATE, 129, 2),
       ('Kaiso with nut sauce', CURRENT_DATE, 99, 2),
       ('Salad Dario', CURRENT_DATE, 98, 3),
       ('Hummus with crackers', CURRENT_DATE, 38, 3),
       ('Burger with "steaks"', CURRENT_DATE, 87, 3),
       ('Pan of varenyky', CURRENT_DATE, 98, 4),
       ('Backed chicken salad', CURRENT_DATE, 88, 4),
       ('Ramen with shrimps', CURRENT_DATE, 166, 5),
       ('Mochi', CURRENT_DATE, 55, 5),
       ('Bograch', '2021-11-20', 92, 1),
       ('Vegetable salad with feta cheese', '2021-11-20', 82, 1),
       ('Green Salad', '2021-11-20', 99, 2),
       ('Green Roll', '2021-11-20', 219, 2),
       ('Berry cheesecake', '2021-11-20', 89, 2),
       ('Amigo-bowl', '2021-11-20', 108, 3),
       ('Pancakes', '2021-11-20', 91, 3),
       ('Chicken sizzling pan', '2021-11-20', 109, 4),
       ('Chemical experiments', '2021-11-20', 140, 4),
       ('Cottage cheese spreads with baguette', '2021-11-20', 92, 4),
       ('Noodles with vegetables and tofu', '2021-11-20', 138, 5),
       ('Dumplings with pork', '2021-11-20', 98, 5),
       ('Ice-cream', '2021-11-20', 65, 5);

INSERT INTO VOTE (registered_vote, restaurant_id, user_id)
VALUES (CURRENT_DATE, 4, 1),
       (CURRENT_DATE, 5, 2),
       ('2021-11-20', 1, 1),
       ('2021-11-20', 1, 2);
