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

