create table usuario
(

    id                  bigint  not null auto_increment,
    nombre              varchar(100) not null,
    correoElectronico   varchar(100) not null unique,
    contrasenia         varchar(300)   not null unique,

    primary key (id)

);