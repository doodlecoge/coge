CREATE TABLE users
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX unique_id ON users ( id );
CREATE UNIQUE INDEX unique_username ON users ( username );
