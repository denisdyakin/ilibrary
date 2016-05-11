CREATE CACHED TABLE user(name VARCHAR(50) PRIMARY KEY NOT NULL,
                  password VARCHAR(50) NOT NULL);

CREATE CACHED TABLE book(isn NUMERIC PRIMARY KEY NOT NULL,
                  author VARCHAR(50) NOT NULL,
                  name VARCHAR(50) NOT NULL,
                  user_name VARCHAR(50) REFERENCES user(name));

CREATE CACHED TABLE USER_AUTHORIZATION (name VARCHAR(50) PRIMARY KEY NOT NULL, ROLE VARCHAR(50) NOT NULL);

INSERT INTO USER_AUTHORIZATION VALUES('admin','ROLE_ADMIN');
INSERT INTO USER_AUTHORIZATION VALUES('denis','ROLE_USER');

INSERT INTO user VALUES ('denis', 'dpass');
INSERT INTO user VALUES ('admin', 'pass');
INSERT INTO user VALUES ('elena', 'epass');
INSERT INTO user VALUES ('katya', 'kpass');

INSERT INTO book VALUES (1, 'Бредберри', '451 градус по Фаренгейту', 'denis');
INSERT INTO book VALUES (2, 'Булгаков', 'Мастер и Маргарита', NULL);
INSERT INTO book VALUES (3, 'Бредберри', 'Марсианские хроники', 'admin');
INSERT INTO book VALUES (4, 'Гоголь', 'Мертвые души', 'elena');
INSERT INTO book VALUES (5 ,'Пушкин', 'Капитанская дочка', 'katya');