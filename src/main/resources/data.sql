/* Categories */
INSERT INTO category (name)
VALUES ('OFFERS');
INSERT INTO category (name)
VALUES ('ELECTRONICS');
INSERT INTO category (name)
VALUES ('FOOD');
INSERT INTO category (name)
VALUES ('BOOKS');

/* Products */
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0.1, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Playstation 5', 3.123, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Mechanical Keyboard', 100, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Intel® Core™ i7-1065G7', 1000, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0.5, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Intel® Core™ i7-1065G7', 1000, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Intel® Core™ i7-1065G7', 1000, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Intel® Core™ i7-1065G7', 1000, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Intel® Core™ i7-1065G7', 1000, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),
        'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        0, 'https://res.cloudinary.com/demo/image/upload/lady.jpg', 0, 'Intel® Core™ i7-1065G7', 1000, 35);

/* Relationships */
INSERT INTO product_categories(products_id, categories_name)
VALUES (1, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (1, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (2, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (3, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (4, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (4, 'OFFERS');

/* Users (passwords are equal to emails)*/
/* User - user@gmail.com Password:user@gmail.com */
INSERT INTO user(address, email, enabled, first_name, last_name, locked, password, user_role, cart_id)
VALUES ('University College Dublin Belfield, Dublin 4, Ireland.', 'user@gmail.com', true, 'John', 'Doe',
        false, '$2y$12$BCQxQr5YQZXNXW0zDRLEteDd0.AebaCwFiN75DrzV0O3S5uwwBzAK', 'ROLE_USER', NULL);
/* Owner - admin@gmail.com Password:admin@gmail.com */
INSERT INTO user(address, email, enabled, first_name, last_name, locked, password, user_role, cart_id)
VALUES ('University College Dublin Belfield, Dublin 4, Ireland.', 'admin@gmail.com', true, 'Shop', 'Owner',
        false, '$2y$12$mLgWin7zUOASwe7ybRNAceoiv4ENXhyJXTb/MFtUT4fix0p2kEqAO', 'ROLE_ADMIN', NULL);


