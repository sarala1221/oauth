Run maven build to generate Mapstruct mapper implementation

Used lombok for logger and data, please follow below link to setup in IDE, currently not working disabled
https://projectlombok.org/setup/eclipse

"testuser1", "testuser1$" - ADMIN
"testuser2", "testuser2$" - MODERATOR
"testuser3", "testuser3$" - USER

Swagger API - stage
http://localhost:8083/swagger-ui.html

Stage Creds to get Token::
password: stage_$tudentcase
username: stage%studentcase

Dev
http://localhost:8082/swagger-ui.html

Dev Creds to get Token:
password: dev_$tudentcase
username: dev%studentcase

#DB
http://localhost:8083/h2-console/login.do

Stage creds:
url: jdbc:h2:mem:stagedb
password: stage
username: stage

http://localhost:8082/h2-console/login.do

Dev creds:
url: jdbc:h2:mem:dev
password: dev
username: dev