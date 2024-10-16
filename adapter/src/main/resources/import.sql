
--Mario1
INSERT INTO users(id, username, password, create_at) VALUES(1, 'mario1', '$2a$10$zyEAgLnFSxOzlww/V7DlNeWShEAwLp.fOo3Ds25nCrS5XyElb55Xm', now());

INSERT INTO roles(id, authority) VALUES(55, 'admin_users');
INSERT INTO roles(id, authority) VALUES(56, 'admin_products');

INSERT INTO users_roles(user_id, role_id) VALUES(1, 55);
INSERT INTO users_roles(user_id, role_id) VALUES(1, 56);
