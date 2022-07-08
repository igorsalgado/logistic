create table cliente
(
    id       bigint auto_increment
        primary key,
    nome     varchar(50)  not null,
    email    varchar(255) not null,
    telefone varchar(20)  not null
);

create table entrega
(
    id                       bigint auto_increment
        primary key,
    cliente_id               bigint         not null,
    taxa                     decimal(10, 2) not null,
    status                   varchar(20)    not null,
    data_pedido              datetime       not null,
    data_entrega             datetime       null,
    destinatario_nome        varchar(60)    not null,
    destinatario_logradouro  varchar(255)   not null,
    destinatario_numero      varchar(30)    not null,
    destinatario_complemento varchar(60)    not null,
    destinatario_bairro      varchar(30)    not null,
    constraint fk_entrega_cliente
        foreign key (cliente_id) references cliente (id)
);

create table ocorrencia
(
    id            bigint auto_increment
        primary key,
    entrega_id    bigint   not null,
    descricao     text     not null,
    data_registro datetime not null,
    constraint fk_ocorrencia_entrega
        foreign key (entrega_id) references entrega (id)
);

