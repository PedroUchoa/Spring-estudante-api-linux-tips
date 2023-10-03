create table students(
    id bigint not null auto_increment,
    name varchar(255) not null UNIQUE,
    endereco varchar(10) not null,
    curso varchar(255) not null,
    criado_em DATE not null,
    atualizado_em DATE not null,
    PRIMARY KEY (id)
);
