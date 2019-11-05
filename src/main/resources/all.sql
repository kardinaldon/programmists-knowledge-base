DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE hibernate_sequence
    OWNER TO postgres;

create table users (
                       userId serial,
                       name varchar(300) unique not null,
                       login varchar(300) unique not null,
                       password varchar(300) not null,
                       role varchar(300) not null,
                       PRIMARY KEY (userId)
);

create table categories (
                            categoryId serial,
                            title varchar(300) unique not null,
                            description text,
                            PRIMARY KEY (categoryId)
);

create table articles (
                          articleId serial,
                          title text not null,
                          smallDescription text,
                          description text,
                          dateOfCreation date,
                          creator integer,
                          category integer,
                          PRIMARY KEY (articleId),
                          FOREIGN KEY (creator) REFERENCES users(userId) ON DELETE RESTRICT,
                          FOREIGN KEY (category) REFERENCES categories (categoryId) ON DELETE RESTRICT
);

select * from users;
select * from categories;
select * from articles;