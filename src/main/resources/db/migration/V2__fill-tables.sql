/* Categories */
INSERT INTO category (name)
VALUES ('OFFERS');
INSERT INTO category (name)
VALUES ('ELECTRONICS');
INSERT INTO category (name)
VALUES ('FOOD');
INSERT INTO category (name)
VALUES ('BOOKS');
INSERT INTO category (name)
VALUES ('FEATURED');

/* Products */
/*Electronics*/
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Sony Playstation 5 - Disk Edition. Purchase the best gaming console at the cheapest price in the world. !100% Legit, No Scam! Save the most amount of money compared to all existing websites.' ,
        0.1, 'https://res.cloudinary.com/internationalbots/image/upload/v1615599197/product-1.jpg', false, 'Playstation 5', 3.123, 0);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'IBOT Series Pro Mechanical Keyboard - Linear Red switches, perfect for Gaming and Work. Custimizable RGB with built in Software and a on board scroll wheel to adjust volume and change songs etc.',
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601520/product-2.png', false, 'IBOT Mechanical Keyboard', 149.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp, 'IBOT RGB Mousepad - Dimensions 1220 x 590 x 4 mm. Brilliant 2-zone RGB dynamic illumination Easy and intuitive setup of in-game lighting notifications. Optimized for low and high DPI tracking movements.',
        0.1, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601520/product-3.png', false, 'IBOT RGB Mousepad', 119.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp, 'IBOT Gaming/Office Chair - Silk finish breathable material with all memory foam cushioning. 90 degree bend and 360 degree rotation. Seat height adjustable from 45 - 85cm, with adjustable arm rest.',
        0.33, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601516/product-4.jpg', false, 'IBOT Gaming/Office Chair', 309.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp, 'IBOT Wireless Gaming Mouse - Dynamic finish to fit hands of all sizes. Guaranteed to to 100 Million clicks. Aluminum Body offers the best durability in today''s market. Only weight of 99 grams with a battery that lasts 420+ hours per charge. Wireless connection works up to 12m.',
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601513/product-5.jpg', false, 'IBOT Wireless Mouse', 89.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp, 'The stunning 49-inch screen of IBOT''s POG101 packs wow factor, providing an immersive experience for gamers, and it lets productivity-minded users keep several windows open side by side. With an OLED, 8k Display at an refresh rate of 360Hz. With a 25 degree adjustable aluminium stand. It is every computer workers dream' ,
        0.23, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601516/product-6.jpg', false, 'IBOT 49-inch Curved Ultrawide Monitor', 1299.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp, 'Illuminating wall lights which looks pretty cool.',
        0.175, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601518/product-7.jpg', false, 'IBOT Neon Light', 25, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp, 'IBOT''s best rated product. Vibranium Built casing with most comfort key switches possible. Approved by World''s rank 1 Osu Player CWT. ' ,
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601515/product-8.jpg', false, 'IBOT Osu Keypad', 80, 35);

/** food **/
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'IBOT Kimchi - The finnest Kimchi available in the world, made from 100% organic rich cabbage. Fermented in a dust clean environment with the most skilled Kimchi maker. ONLY harvested during April and June every year, proven to have health benefits such as boosting men stamina ;)',
        0.08, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601515/product-9.jpg', false, 'Korean Kimchi', 5.50, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'IBOT''s home brewed Bubble tea. Everyone knows what bubble tea is - A Delicious milk tea beverage. But we are just simply better.',
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601515/product-10.jpg', false, 'IBOT Bubble Tea', 4.8, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Durian - a smelly but luxury fruit. 10/500g',
        0.15, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601514/product-11.jpg', false, 'Durian', 10, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Dim Sum - Steam cooked Traditional Chinese dish. Requires steaming for 15 minutes.',
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601514/product-12.jpg', false, 'Dim Sum - Ready to cook', 6.5, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Wague beef steak - Ready to Cook Japanese A5 wague beef steak. Seasonal Discount originally $125/500g',
        0.18, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601513/product-13.jpg', false, 'Wague Steak', 125, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Armand de Brignac Ace of Spades Brut Gold with Gift Box - The first release from Armand de Brignac, the Brut Gold remains the most iconic cuvée in the range.',
        0.12, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601512/product-14.jpg', false, 'Ace of Spades', 399.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Hennessy Paradis Imperial 70 cl - The jewel of the collection Hennessy. Hennessy Paradis Imperial is a contemporary creation of Yann Fillioux that embodies the heyday of the art of selection. The relentless pursuit of finesse over 8 generations of Master Blenders of the same family.',
        0.32, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601512/product-15.jpg', false, 'IBOT Hennessy', 2900.67, 35);

/* books */
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Bobby Moore - Biography of English Football star & captain of world cup winning team.'  ,
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601512/product-16.jpg', false, 'Bobby Moore Biography', 15.49, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Messi - 2021 Edition Biography on how a young Argentinian man became the best footballer the world has ever seen.',
        0.16, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601511/product-17.jpg', false, 'Messi - 2021 Edition', 23.99, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Jaime Oliver Cook Book. 5 Ingredients - Quick & Easy Food, most Straight forward book',
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601516/product-18.jpg', false, 'Jaime Oliver''s - 5 Ingredients', 32, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Peppa Pig: Peppa''s Easter Egg Hunt Board book. Favourite leisure time story from everyone''S friend Peppa and George',
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601511/product-19.jpg', false, 'Peppa Pig: Peppa''s Easter Egg Hunt Board book', 4.8, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'HTML & CSS book. Will teach you everything you need to know about web design and styling. Proven by the best students in class of 18-22.',
        0.69, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601511/product-20.jpg', false, 'HTML & CSS ', 38.99, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'Pokemon Book, Learn about your favourite Pokemon and their traits. Read now to take your first step at becoming the best Pokemon trainer.',
        0, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601511/product-21.jpg', false, 'Pokemon Storybook', 4.8, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp,'IBOT Dynamic Microphone, Best possible streaming mic in today''s market. Filters out all echoing, fuzz and unnecessary static. Because we all know ~ It happens all the time when your trying to teach some important materials that can heavily effect some young people''s lives. The mic also blocks your clicky keyboard noises which sounds like a snare drum. And some coughing which sounds like a tornado as well as squeeky chairs from the university that claims to be poor. But get your IBOT mic today and save your students'' lives. We''ll even give it to you for basically free!',
        0.999, 'https://res.cloudinary.com/internationalbots/image/upload/v1615601512/product-22.jpg', false, 'IBOT Dynamic Microphone', 499.99, 35);

/* Relationships */
INSERT INTO product_categories(products_id, categories_name)
VALUES (1, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (1, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (1, 'FEATURED');
INSERT INTO product_categories(products_id, categories_name)
VALUES (2, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (3, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (3, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (4, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (4, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (5, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (6, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (6, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (6, 'FEATURED');
INSERT INTO product_categories(products_id, categories_name)
VALUES (7, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (7, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (8, 'ELECTRONICS');

/* Users (passwords are equal to emails)*/
/* User - user@gmail.com Password:user@gmail.com */
INSERT INTO users(address, email, enabled, first_name, last_name, locked, password, user_role, cart_id)
VALUES ('University College Dublin Belfield, Dublin 4, Ireland.', 'user@gmail.com', true, 'John', 'Doe',
        false, '$2y$12$BCQxQr5YQZXNXW0zDRLEteDd0.AebaCwFiN75DrzV0O3S5uwwBzAK', 'ROLE_USER', NULL);
/* Owner - admin@gmail.com Password:admin@gmail.com */
INSERT INTO users(address, email, enabled, first_name, last_name, locked, password, user_role, cart_id)
VALUES ('University College Dublin Belfield, Dublin 4, Ireland.', 'admin@gmail.com', true, 'Shop', 'Owner',
        false, '$2y$12$mLgWin7zUOASwe7ybRNAceoiv4ENXhyJXTb/MFtUT4fix0p2kEqAO', 'ROLE_ADMIN', NULL);


INSERT INTO message (id, created_date, message_content, type, subject, user_id)
VALUES ('1', '2020-12-01', 'Hello there how are you? please help me with my order!!!', 'NEW', 'Account Help', '1');


/*Food category*/
INSERT INTO product_categories(products_id, categories_name)
VALUES (9, 'FOOD');
INSERT INTO product_categories(products_id, categories_name)
VALUES (9, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (10, 'FOOD');
INSERT INTO product_categories(products_id, categories_name)
VALUES (10, 'FEATURED');
INSERT INTO product_categories(products_id, categories_name)
VALUES (11, 'FOOD');
INSERT INTO product_categories(products_id, categories_name)
VALUES (11, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (12, 'FOOD');
INSERT INTO product_categories(products_id, categories_name)
VALUES (13, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (13, 'FOOD');
INSERT INTO product_categories(products_id, categories_name)
VALUES (14, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (14, 'FOOD');
INSERT INTO product_categories(products_id, categories_name)
VALUES (14, 'FEATURED');
INSERT INTO product_categories(products_id, categories_name)
VALUES (15, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (15, 'FOOD');


/*Books categories*/

INSERT INTO product_categories(products_id, categories_name)
VALUES (16, 'BOOKS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (17, 'BOOKS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (17, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (18, 'BOOKS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (18, 'FEATURED');
INSERT INTO product_categories(products_id, categories_name)
VALUES (19, 'BOOKS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (20, 'BOOKS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (20, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (21, 'BOOKS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (21, 'FEATURED');
INSERT INTO product_categories(products_id, categories_name)
VALUES (22, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (22, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (22, 'FEATURED');