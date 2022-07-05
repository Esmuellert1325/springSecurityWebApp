-- Felhasználók létrehozása
insert into users (name, password, last_login) values ('admin', '{noop}admin', current_date())
insert into users (name, password, last_login) values ('user1', '{noop}user1', current_date())
insert into users (name, password, last_login) values ('user2', '{noop}user2', current_date())
insert into users (name, password, last_login) values ('user3', '{noop}user3', current_date())
-- Szerepkörök létrehozása
insert into roles (role) values ('Adminisztrator')
insert into roles (role) values ('Tartalomszerkeszto')
insert into roles (role) values ('Bejelentkezett Felhasznalok')
-- Felhasználók és szerepkörök összekapcsolása
insert into users_roles (user_id, role_id) values (1, 1)
insert into users_roles (user_id, role_id) values (2, 2)
insert into users_roles (user_id, role_id) values (2, 3)
insert into users_roles (user_id, role_id) values (3, 2)
insert into users_roles (user_id, role_id) values (4, 3)