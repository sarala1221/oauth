DROP TABLE IF EXISTS oauth_client_details;
drop table if exists granted_authorities_list;
DROP TABLE IF EXISTS users;

create table oauth_client_details (
                                      id VARCHAR(256) PRIMARY KEY,
                                      created timestamp,
                                      last_updated timestamp,
                                      client_id VARCHAR(256),
                                      resource_ids VARCHAR(256),
                                      client_secret VARCHAR(256),
                                      scope VARCHAR(256),
                                      authorized_grant_types VARCHAR(256),
                                      web_server_redirect_uri VARCHAR(256),
                                      authorities VARCHAR(256),
                                      access_token_validity INTEGER,
                                      refresh_token_validity INTEGER,
                                      additional_information VARCHAR(4096),
                                      autoapprove VARCHAR(256)
);

CREATE TABLE users (
                       id VARCHAR(256) PRIMARY KEY,
                       created timestamp,
                       last_updated timestamp,
                       username VARCHAR(45),
                       password VARCHAR(60)
                       
);

CREATE TABLE granted_authorities_list (
    auth_id VARCHAR(256) PRIMARY KEY ,
    authority VARCHAR(50),
    user_auth_id VARCHAR(256)
);

ALTER TABLE granted_authorities_list ADD CONSTRAINT FK_users_1 FOREIGN KEY (user_auth_id) REFERENCES users(id) ON DELETE CASCADE;