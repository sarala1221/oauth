INSERT INTO users (id, created, last_updated, username, password) VALUES ('abc', '2017-11-20 13:14:37', NULL, 'abc@gmail.com','$2y$12$CygHhSAFFkNJa.6SnoASGOKB1TszEAJ6s3hZLb/mAnxeLhWxx8QFC');
INSERT INTO users (id, created, last_updated, username, password) VALUES ('2c', '2017-11-20 13:14:37', NULL,  'def@gmail.com','$2y$12$3BTHXLwO7tzete459Oy5dOCyobYQO8G3CuGwPy4ITbOCDBRoQuIma');
INSERT INTO users (id, created, last_updated, username, password) VALUES ('abcd', '2017-11-20 13:14:37', NULL, 'abcd@gmail.com','$2y$12$CygHhSAFFkNJa.6SnoASGOKB1TszEAJ6s3hZLb/mAnxeLhWxx8QFC');
insert into granted_authorities_list (user_auth_id, auth_id, authority ) values ('abc',1,  'ROLE_USER' );
insert into granted_authorities_list (user_auth_id, auth_id, authority ) values ('abc',2,  'ROLE_ADMIN' );
insert into granted_authorities_list (user_auth_id, auth_id, authority ) values ('abcd',2,  'ROLE_MODERATOR' );
INSERT INTO oauth_client_details (id, created, last_updated, access_token_validity, additional_information, authorities, autoapprove, client_id, client_secret, authorized_grant_types, refresh_token_validity, resource_ids, scope, web_server_redirect_uri) VALUES ('1ec', '2017-11-20 13:14:37', NULL, 5000, NULL, 'ROLE_USER', NULL, '6nuajulfgbkfhm1umjr05clniu', '$2y$12$sj6b9FMSv7bBe3amzVLys.SbAsi6R3GQIbyoyBbaCoi1/9wNlhW0q', 'password', 1209600000, NULL, 'write', NULL);
