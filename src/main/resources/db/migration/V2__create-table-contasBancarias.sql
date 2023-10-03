create table contas_bancarias(
    id bigint not null auto_increment,
    agencia INT not null,
    conta INT not null,
    digito INT not null,
    tipo_conta_bancaria INT not null,
    PRIMARY KEY (id)
);
