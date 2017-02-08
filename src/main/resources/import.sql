-- functions
-- INSERT INTO public.function(id, code, description, view) VALUES (101, 'ADMIN_HOME_VIEW', null, 'admin-home');
-- INSERT INTO public.function(id, code, description, view) VALUES (102, 'ADMIN_SECRET_VIEW', null, 'admin-secret');
-- INSERT INTO public.function(id, code, description, view) VALUES (103, 'HOME', null, '');
INSERT INTO public.function(id, active, code, description, function_enum, menu_group, menu_name, name, view) VALUES (101, true, 'ADMIN_HOME', null, 'ADMIN_HOME', 'Administracja', 'Pulpit', 'Widok domowy administratora', 'admin-home');
INSERT INTO public.function(id, active, code, description, function_enum, menu_group, menu_name, name, view) VALUES (102, true, 'ADMIN_SECRET', null, 'ADMIN_SECRET', 'Admin', 'Admin Secret View', 'Secret View', 'admin-secret');
INSERT INTO public.function(id, active, code, description, function_enum, menu_group, menu_name, name, view) VALUES (103, true, 'HOME', null, 'HOME', 'Home', 'Home', 'Home', '');
INSERT INTO public.function(id, active, code, description, function_enum, menu_group, menu_name, name, view) VALUES (104, true, 'REGISTER_VIEW', null, 'REGISTER', 'Register', 'Register', 'Register', 'register');
-- INSERT INTO public.function(id, code, description, view) VALUES (104, 'REGISTER_VIEW', null, 'register');

INSERT INTO public.function(id, active, code, description, function_enum, menu_group, menu_name, name, view) VALUES (105, true, 'USER_HOME_VIEW', null, 'USER_HOME', 'User', 'User Home', 'User Home', 'user-home');

-- INSERT INTO public.function(id, code, description, view) VALUES (106, 'ACCESS_CONTROL_VIEW', null, 'access');
INSERT INTO public.function(id, active, code, description, function_enum, menu_group, menu_name, name, view) VALUES (107, true, 'USER_LIST', null, 'USER_LIST', 'Administracja', 'Lista użytkowników', 'Lista użytkowników', 'user-list');




-- ROLE_ADMIN
INSERT INTO public.role (id, name) VALUES (101, 'ROLE_ADMIN');
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 101);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 102);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 103);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 104);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 105);
-- INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 106);
INSERT INTO public.roles_functions(role_id, function_id) VALUES (101, 107);

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
INSERT INTO public.roles_functions(role_id, function_id) VALUES (103, 104);

-- user - ROLE_ADMIN
INSERT INTO public.app_user(id, name, surname, login, password) VALUES (101, 'Admin', 'Admin', 'admin', 'p');
INSERT INTO public.users_roles(user_id, role_id) VALUES (101, 101);

-- user - ROLE_USER
INSERT INTO public.app_user(id, name, surname, login, password) VALUES (102, 'User', 'User', 'user', 'p');
INSERT INTO public.users_roles(user_id, role_id) VALUES (102, 102);

-- user - ROLE_USER
INSERT INTO public.app_user(id, name, surname, login, password) VALUES (103, 'anonymousUser', 'anonymousUser', 'anonymousUser', '');
INSERT INTO public.users_roles(user_id, role_id) VALUES (103, 103);



