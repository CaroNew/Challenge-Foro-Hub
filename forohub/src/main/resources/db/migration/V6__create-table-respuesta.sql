create table respuesta (
    id bigint not null auto_increment,
    mensaje varchar(5000) not null ,
    autor_id bigint not null,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tema_id bigint not null,
    solucion tinyint DEFAULT 0,
    primary key (id),
    foreign key (autor_id) references usuario(id),
    foreign key (tema_id) references tema(id)
);