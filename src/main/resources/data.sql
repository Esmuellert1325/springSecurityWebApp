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

-- Könyvek beszúrása
insert into books(author, release, title) values ('JK Rowling', 1997, 'Harry Potter and the Philosophers Stone')
insert into books(author, release, title) values ('Stephen King', 1977, 'The Shining')
insert into books(author, release, title) values ('Ernest Cline', 2011, 'Ready Player One')
-- Kategóriák beszúrása
insert into categories (category) values ('Fantasy')
insert into categories (category) values ('Horror')
insert into categories (category) values ('Sci-Fi')
-- Könyvek és kategóriák összekapcsolása
insert into BOOK_CATEGORIES (book_id, category_id) values (1, 1)
insert into BOOK_CATEGORIES (book_id, category_id) values (2, 2)
insert into BOOK_CATEGORIES (book_id, category_id) values (3, 3)

--Még kellhet:
--select categories.category
--from categories, books, book_categories
--where book_categories.book_id = books.id and book_categories.category_id = categories.id