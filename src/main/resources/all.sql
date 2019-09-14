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
    OWNER TO aesehxwtplugav;

create table users (
                       id integer not null unique,
                       name varchar(300) unique not null,
                       login varchar(300) unique not null,
                       password varchar(300) not null,
                       PRIMARY KEY (login)
);

create table categories (
                            id integer not null unique,
                            title varchar(300) unique not null,
                            description text,
                            PRIMARY KEY (title)
);

create table articles (
                          id integer not null unique,
                          title text not null,
                          description text,
                          creator integer,
                          category integer,
                          PRIMARY KEY (id),
                          FOREIGN KEY (creator) REFERENCES users(id) ON DELETE RESTRICT,
                          FOREIGN KEY (category) REFERENCES categories (id) ON DELETE RESTRICT
);

select * from users;
select * from categories;
select * from articles;