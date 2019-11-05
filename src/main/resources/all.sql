DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS user_table;

create table user_table (
                            userId serial,
                            email varchar(300) unique not null,
                            password varchar(300) not null,
                            role numeric default 0,
                            status numeric default 1,
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
                          FOREIGN KEY (creator) REFERENCES user_table(userId) ON DELETE RESTRICT,
                          FOREIGN KEY (category) REFERENCES categories (categoryId) ON DELETE RESTRICT
);


select * from users;
select * from categories;
select * from articles;