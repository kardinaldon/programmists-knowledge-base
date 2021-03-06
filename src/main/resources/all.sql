DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS user_table;
DROP TABLE IF EXISTS generated_values;
DROP TABLE IF EXISTS letter_template;
DROP TABLE IF EXISTS session_table;


create table user_table (
    userId serial,
    email varchar(300) unique not null,
    password varchar(300) not null,
    role numeric,
    status numeric,
    sessionid varchar(300),
    PRIMARY KEY (userId)
);

create table categories (
    categoryId serial,
    title varchar(300) unique not null,
    description text,
    parentId int,
    level int,
    PRIMARY KEY (categoryId)
);

insert into categories (title,description,parentId,lft,rght,level) values ('Категории','описание для категории','0','1','2','1');

create table articles (
    articleId serial,
    title text not null,
    smallDescription text,
    description text,
    dateOfCreation date,
    creator integer,
    category integer,
    PRIMARY KEY (articleId),
    FOREIGN KEY (creator) REFERENCES user_table(userId) ON DELETE RESTRICT,
    FOREIGN KEY (category) REFERENCES categories (categoryId) ON DELETE RESTRICT
);

create table generated_values (
    generatedValueId serial,
    generatedValue varchar unique not null
);

create table letter_template (
  letterId serial,
  letterHeader text,
  letterText text,
  primary key (letterId)
);

create table session_table (
    sessionId serial,
    sessionValue varchar unique not null
);