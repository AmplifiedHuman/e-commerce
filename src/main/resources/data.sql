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
VALUES (current_timestamp(),'Sony Playstation 5 - Disk Edition. Purchase the best gaming console at the cheapest price in the world. !100% Legit, No Scam! Save the most amount of money compared to all existing websites.' ,
        0.1, 'https://www.kjell.com/globalassets/productimages/771530_62770_2.tif?ref=BEAE275D09&format=jpg', 0, 'Playstation 5', 3.123, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'IBOT Series Pro Mechanical Keyboard - Linear Red switches, perfect for Gaming and Work. Custimizable RGB with built in Software and a on board scroll wheel to adjust volume and change songs etc.',
        0, 'https://media.steelseriescdn.com/thumbs/catalogue/products/01057-apex-pro-tkl/623ce3f69cee4c109abd5ff939dd4921.png.350x280_q100_crop-fit_optimize.png', 0, 'IBOT Mechanical Keyboard', 149.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(), 'IBOT RGB Mousepad - Dimensions 1220 x 590 x 4 mm. Brilliant 2-zone RGB dynamic illumination Easy and intuitive setup of in-game lighting notifications. Optimized for low and high DPI tracking movements.',
        0.1, 'https://media.steelseriescdn.com/thumbs/filer_public/6c/ef/6cef91c0-7bdb-4fe5-87c3-0b959dbc645c/qck_prism_3xl_buyimg_02.png__1850x800_q100_crop-scale_optimize_subsampling-2.png', 0, 'IBOT RGB Mousepad', 119.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(), 'IBOT Gaming/Office Chair - Silk finish breathable material with all memory foam cushioning. 90 degree bend and 360 degree rotation. Seat height adjustable from 45 - 85cm, with adjustable arm rest.',
        0.33, 'https://image.made-in-china.com/2f0j00TUQfRhmrRpgw/New-Arrival-Light-Gamingchair-Sillas-Gamer-Blue-Gaming-Chair-RGB-with-LED.jpg', 0, 'IBOT Gaming/Office Chair', 309.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(), 'IBOT Wireless Gaming Mouse - Dynamic finish to fit hands of all sizes. Guaranteed to to 100 Million clicks. Aluminum Body offers the best durability in today''s market. Only weight of 99 grams with a battery that lasts 420+ hours per charge. Wireless connection works up to 12m.',
        0, 'https://s3-eu-west-1.amazonaws.com/static.dstitan/14b96f7b-d43e-4380-a145-0bf3fc893ba2/4df00aa2-2132-4d19-800d-0cf926c3269f/Ergonomic-Gaming-Gaming-Wireless-Keys-Backlit-Mouse-Computer-Rechargeable-1600-Mouse-Gamer-Laptop-mouse-Silent-0.jpg', 0, 'IBOT Wireless Mouse', 89.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(), 'The stunning 49-inch screen of IBOT''s POG101 packs wow factor, providing an immersive experience for gamers, and it lets productivity-minded users keep several windows open side by side. With an OLED, 8k Display at an refresh rate of 360Hz. With a 25 degree adjustable aluminium stand. It is every computer workers dream' ,
        0.23, 'https://i.pcmag.com/imagery/reviews/03Qnlt9aC4lMcPMuRP7HiCO-8.1569472965.fit_scale.size_760x427.jpg', 0, 'IBOT 49-inch Curved Ultrawide Monitor', 1299.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(), 'Illuminating wall lights that can be interconnected and controlled with a remote. ',
        0.175, 'https://images-na.ssl-images-amazon.com/images/I/51dHboJr-%2BL._AC_SX342_.jpg', 0, 'IBOT Illuminating Wall Light', 25, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(), 'IBOT''s best rated product. Vibranium Built casing with most comfort key switches possible. Approved by World''s rank 1 Osu Player CWT. ' ,
        0, 'https://images-na.ssl-images-amazon.com/images/I/610vGzs%2B6pL._AC_SL1001_.jpg', 0, 'IBOT Osu Keypad', 80, 35);


INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'IBOT Kimchi - The finnest Kimchi available in the world, made from 100% organic rich cabbage. Fermented in a dust clean environment with the most skilled Kimchi maker. ONLY harvested during April and June every year, proven to have health benefits such as boosting men stamina ;)',
        0.08, 'https://digitalcontent.api.tesco.com/v2/media/ghs/e5946084-0172-4019-86ee-cddc3dbedf4e/snapshotimagehandler_1986188276.jpeg?h=540&w=540', 0, 'Korean Kimchi', 5.50, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'IBOT''s home brewed Bubble tea. Everyone knows what bubble tea is - A Delicious milk tea beverage. But we are just simply better.',
        0, 'https://d1ralsognjng37.cloudfront.net/131503b9-e927-459b-aea0-332fa95387c4.jpeg', 0, 'IBOT Bubble Tea', 4.8, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Durian - a smelly but luxury fruit. 10/500g',
        0.15, 'https://img1.mashed.com/img/gallery/the-truth-about-the-worlds-stinkiest-fruits/intro-1585943639.jpg', 0, 'Durian', 10, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Dim Sum - Steam cooked Traditional Chinese dish. Requires steaming for 15 minutes.',
        0, 'https://www.discoverhongkong.com/content/dam/dhk/intl/explore/dining/hong-kong-s-most-innovative-dim-sum-restaurants/dimdimsum4_3.jpg', 0, 'Dim Sum - Ready to cook', 6.5, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Wague beef steak - Ready to Cook Japanese A5 wague beef steak. Seasonal Discount originally $125/500g',
        0.18, 'https://cdn.shopify.com/s/files/1/0242/3596/6545/products/thum_sa-roin200x2_480x.jpg?v=1569482967', 0, 'Wague Steak', 125, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Armand de Brignac Ace of Spades Brut Gold with Gift Box - The first release from Armand de Brignac, the Brut Gold remains the most iconic cuvée in the range.',
        0.12, 'https://www.wine.com/product/images/w_767,c_fit,q_auto:good,fl_progressive/91199.jpg', 0, 'Ace of Spades', 399.99, 35);
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Hennessy Paradis Imperial 70 cl - The jewel of the collection Hennessy. Hennessy Paradis Imperial is a contemporary creation of Yann Fillioux that embodies the heyday of the art of selection. The relentless pursuit of finesse over 8 generations of Master Blenders of the same family.',
        0.32, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQU63RAQcnMJJ2aoeYh_fTPWnW8ZpKnrWnMLEaNCk_pRQMl5W856gdk0oNaJvnGdJL3Z5HDFM0y&usqp=CAc', 0, 'IBOT Hennessy', 2900.67, 35);



/* books */
INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Bobby Moore - Biography of English Football star & captain of world cup winning team.'  ,
        0, 'https://bitebackpub.s3.amazonaws.com/uploads/book/image/352/cover_9781849547390.jpg', 0, 'Bobby Moore Biography', 15.49, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Messi - 2021 Edition Biography on how a young Argentinian man became the best footballer the world has ever seen.',
        0.16, 'https://m.media-amazon.com/images/I/516nrcimvnL.jpg', 0, 'Messi - 2021 Edition', 23.99, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Jaime Oliver Cook Book. 5 Ingredients - Quick & Easy Food, most Straight forward book',
        0, 'https://cdn2.penguin.com.au/covers/original/9780718187729.jpg', 0, 'Jaime Oliver''s - 5 Ingredients', 32, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Peppa Pig: Peppa''s Easter Egg Hunt Board book. Favourite leisure time story from everyone''S friend Peppa and George',
        0, 'https://images-na.ssl-images-amazon.com/images/I/81qv11Z8THL.jpg', 0, 'Peppa Pig: Peppa''s Easter Egg Hunt Board book', 4.8, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'HTML & CSS book. Will teach you everything you need to know about web design and styling. Proven by the best students in class of 18-22.',
        0.69, 'https://i.redd.it/m14hlniqyfz41.jpg', 0, 'HTML & CSS ', 38.99, 35);

INSERT INTO product (created_date, description, discount_rate, imageurl, is_hidden, name, price, quantity)
VALUES (current_timestamp(),'Pokemon Book, Learn about your favourite Pokemon and their traits. Read now to take your first step at becoming the best Pokemon trainer.',
        0, 'https://images.randomhouse.com/cover/9781524772598?width=200', 0, 'Pokemon Storybook', 4.8, 35);







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
VALUES (7, 'ELECTRONICS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (7, 'OFFERS');
INSERT INTO product_categories(products_id, categories_name)
VALUES (8, 'ELECTRONICS');

/* Users (passwords are equal to emails)*/
/* User - user@gmail.com Password:user@gmail.com */
INSERT INTO user(address, email, enabled, first_name, last_name, locked, password, user_role, cart_id)
VALUES ('University College Dublin Belfield, Dublin 4, Ireland.', 'user@gmail.com', true, 'John', 'Doe',
        false, '$2y$12$BCQxQr5YQZXNXW0zDRLEteDd0.AebaCwFiN75DrzV0O3S5uwwBzAK', 'ROLE_USER', NULL);
/* Owner - admin@gmail.com Password:admin@gmail.com */
INSERT INTO user(address, email, enabled, first_name, last_name, locked, password, user_role, cart_id)
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