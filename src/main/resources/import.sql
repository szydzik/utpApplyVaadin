-- functions
-- INSERT INTO public.function(id, code, description, view) VALUES (101, 'ADMIN_HOME_VIEW', null, 'admin-home');
-- INSERT INTO public.function(id, code, description, view) VALUES (102, 'ADMIN_SECRET_VIEW', null, 'admin-secret');
-- INSERT INTO public.function(id, code, description, view) VALUES (103, 'HOME', null, '');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (101, true, 'ADMIN_HOME', null, 'ADMIN_HOME', 'Pulpit', 'Widok domowy administratora');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (102, true, 'ADMIN_SECRET', null, 'ADMIN_SECRET', 'Admin Secret View', 'Sekretny widok administratora');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (103, true, 'HOME', null, 'HOME', 'Home', 'Dostęp do strony głównej');
-- INSERT INTO public.function(id, active, code, description, function_enum, menu_group, menu_name, name) VALUES (104, true, 'REGISTER_VIEW', null, 'REGISTER', 'Register', 'Register', 'Register');
-- INSERT INTO public.function(id, code, description, view) VALUES (104, 'REGISTER_VIEW', null, 'register');

INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (105, true, 'USER_HOME_VIEW', null, 'USER_HOME', 'User Home', 'Dostęp do pulpitu użytkownika');

-- INSERT INTO public.function(id, code, description, view) VALUES (106, 'ACCESS_CONTROL_VIEW', null, 'access');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (107, true, 'USER_LIST', null, 'USER_LIST', 'Lista użytkowników', 'Przegląd listy użytkowników');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (108, true, 'USER_DETAILS', null, 'USER_DETAILS', 'Detale użytkownika', 'Przegląd danych użytkownika');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (109, true, 'USER_CREATE', null, 'USER_CREATE', 'Utwórz użytkownika', 'Tworzenie użytkownika');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (110, true, 'USER_EDIT', null, 'USER_EDIT', 'Edytuj użytkownika', 'Edycja użytkownika');

INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (111, true, 'FUNCTION_LIST', null, 'FUNCTION_LIST', 'Lista funkcji', 'Przegląd listy funkcji systemu');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (112, true, 'FUNCTION_DETAILS', null, 'FUNCTION_DETAILS',  'Szczegóły funkcji', 'Szczegóły funkcji');

INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (113, true, 'ROLE_LIST', null, 'ROLE_LIST', 'Lista ról', 'Przegląd listy ról');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (114, true, 'ROLE_DETAILS', null, 'ROLE_DETAILS',  'Szczegóły roli', 'Przegląd szczegółów roli');
INSERT INTO public.function(id, active, code, description, function_enum, menu_name, name) VALUES (115, true, 'ROLE_CREATE', null, 'ROLE_CREATE', 'Dodaj rolę', 'Dodawanie roli');





-- ROLE_ADMIN
INSERT INTO public.role (id, name) VALUES (101, 'ROLE_ADMIN');
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 101);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 102);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 103);
-- INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 104);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 105);
-- INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 106);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 107);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 108);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 109);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 110);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 111);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 112);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 113);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 114);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 115);

-- ROLE_USER
INSERT INTO public.role (id, name) VALUES (102, 'ROLE_USER');
-- INSERT INTO public.roles_functions(role_id, function_id) VALUES (102, 101);
-- INSERT INTO public.roles_functions(role_id, function_id) VALUES (102, 102);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (102, 103);
-- INSERT INTO public.roles_functions(role_id, function_id) VALUES (102, 104);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (102, 105);

-- ROLE_ANONYMOUS
INSERT INTO public.role (id, name) VALUES (103, 'ROLE_ANONYMOUS');
INSERT INTO public.roles_functions(role_id, function_id) VALUES (103, 103);
-- INSERT INTO public.roles_functions(role_id, function_id) VALUES (103, 104);

-- user - ROLE_ADMIN
INSERT INTO public.app_user(id, name, surname, login, password) VALUES (101, 'Admin', 'Admin', 'admin', 'p');
INSERT INTO public.users_roles(user_id, role_id) VALUES (101, 101);

-- user - ROLE_USER
INSERT INTO public.app_user(id, name, surname, login, password) VALUES (102, 'User', 'User', 'user', 'p');
INSERT INTO public.users_roles(user_id, role_id) VALUES (102, 102);

-- user - ROLE_USER
INSERT INTO public.app_user(id, name, surname, login, password) VALUES (103, 'anonymousUser', 'anonymousUser', 'anonymousUser', '');
INSERT INTO public.users_roles(user_id, role_id) VALUES (103, 103);



