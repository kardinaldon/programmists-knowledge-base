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
    login varchar(300) unique,
    password varchar(300),
    PRIMARY KEY (id)
);

create table categories (
    id integer not null unique,
    title varchar(300) unique,
    description text,
    PRIMARY KEY (id)
);

create table articles (
    id integer not null unique,
    title text,
    description text,
    creator varchar(300),
    category varchar(300),
    torrentfile text,
    PRIMARY KEY (id),
    FOREIGN KEY (creator) REFERENCES users(login) ON DELETE RESTRICT,
    FOREIGN KEY (category) REFERENCES categories (title) ON DELETE RESTRICT
);