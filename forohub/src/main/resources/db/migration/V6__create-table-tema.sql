create table tema (
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensaje varchar(5000) not null,
    autor_id bigint not null,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    curso_id bigint not null,
    status ENUM('ABIERTO', 'CERRADO') NOT NULL DEFAULT 'ABIERTO',
    activo tinyint not null default 1,

    primary key (id),
    foreign key (autor_id) references usuario(id),
    foreign key (curso_id) references curso(id)
);