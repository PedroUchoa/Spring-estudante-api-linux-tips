create table users(
    id bigint not null auto_increment,
    login varchar(255) not null UNIQUE,
    password varchar(255) not null,
    PRIMARY KEY (id)
);
