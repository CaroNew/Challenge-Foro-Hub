create table usuarios_roles
(
    usuario_id bigint not null,
    roles_id   bigint not null,

    primary key (usuario_id, roles_id),
    constraint fk_usuarios_roles_usuario_id
        foreign key (usuario_id) references usuario (id),
    constraint fk_usuarios_roles_roles_id
        foreign key (roles_id) references roles (id)
);