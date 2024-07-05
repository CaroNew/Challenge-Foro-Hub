create table usuario
(

    id                  bigint  not null auto_increment,
    nombre              varchar(100) not null,
    correo_electronico   varchar(100) not null unique,
    clave         varchar(300)   not null,
    activo             tinyint not null default 1,

    primary key (id)


);