--Notes
----1. For system users and roles start from -100, -99 and so on.

--Roles
INSERT INTO roles(id, authority) VALUES(-53, 'ROLE_registered');
INSERT INTO roles(id, authority) VALUES(-55, 'ROLE_admin_users');
INSERT INTO roles(id, authority) VALUES(-56, 'ROLE_admin_products');


--Mario1
INSERT INTO users(id, username, password, create_at, email) VALUES(-100, 'mario1', '$2a$10$zyEAgLnFSxOzlww/V7DlNeWShEAwLp.fOo3Ds25nCrS5XyElb55Xm', now(), 'mario1@email.com');

INSERT INTO users_roles(user_id, role_id) VALUES(-100, -55);
INSERT INTO users_roles(user_id, role_id) VALUES(-100, -56);

--Mario2
INSERT INTO users(id, username, password, create_at, email) VALUES(-99, 'mario2', '$2a$10$gSr8onLyPPNGe029IdJMPOD9lkv9q6kCfIcieOX4gSJ6rYIpW1rJi', now(), 'mario2@email.com');

INSERT INTO users_roles(user_id, role_id) VALUES(-99, -56);


--Products

INSERT INTO products(id, name, price, amount, description, created_at) VALUES(1, 'Ceramic Mug', 11.99, 50, 'A stylish ceramic mug for your favorite beverages.', '2024-01-10 10:00:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(2, 'Stainless Steel Water Bottle', 15.50, 30, 'Keep your drinks cold or hot with this durable water bottle.', '2024-01-11 11:15:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(3, 'Bluetooth Speaker', 29.99, 20, 'Portable speaker with excellent sound quality.', '2024-01-12 09:30:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(4, 'Yoga Mat', 24.99, 40, 'Eco-friendly yoga mat for your fitness routine.', '2024-01-13 14:45:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(5, 'Desk Organizer', 19.99, 15, 'Keep your workspace tidy with this desk organizer.', '2024-01-14 16:20:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(6, 'Wireless Earbuds', 49.99, 25, 'Experience true wireless freedom with these earbuds.', '2024-01-15 08:10:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(7, 'Backpack', 39.99, 100, 'Spacious and stylish backpack for everyday use.', '2024-01-16 12:05:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(8, 'LED Desk Lamp', 34.99, 35, 'Brighten your workspace with this adjustable desk lamp.', '2024-01-17 17:35:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(9, 'Notebooks Set', 12.50, 60, 'A set of colorful notebooks for school or office use.', '2024-01-18 09:50:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(10, 'Cooking Utensils Set', 19.99, 15, 'Essential kitchen tools for every home chef.', '2024-01-19 13:15:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(11, 'Wall Art Print', 25.00, 71, 'Beautiful art print to decorate your space.', '2024-01-20 11:30:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(12, 'Portable Charger', 29.99, 22, 'Never run out of battery with this portable charger.', '2024-01-21 15:45:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(13, 'Fitness Tracker', 49.99, 40, 'Monitor your health and fitness with this tracker.', '2024-01-22 10:25:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(14, 'Coffee Maker', 89.99, 60, 'Brew your perfect cup of coffee every morning.', '2024-01-23 14:40:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(15, 'Smartphone Stand', 14.99, 18, 'Adjustable stand for your smartphone or tablet.', '2024-01-24 18:55:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(16, 'Pet Bed', 34.99, 10, 'Cozy bed for your furry friend.', '2024-01-25 09:00:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(17, 'Potted Plant', 16.99, 12, 'Bring life to your home with this lovely potted plant.', '2024-01-26 20:15:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(18, 'Travel Pillow', 19.99, 25, 'Comfortable pillow for your travels.', '2024-01-27 12:30:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(19, 'Skincare Set', 39.99, 35, 'Complete set for glowing skin.', '2024-01-28 11:45:00');
INSERT INTO products(id, name, price, amount, description, created_at) VALUES(20, 'Board Game', 29.99, 15, 'Fun board game for family and friends.', '2024-01-29 08:00:00');
