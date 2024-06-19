package com.challenge.forohub.model.tema;

import com.challenge.forohub.model.respuesta.Repuesta;
import com.challenge.forohub.model.usuario.Usuario;
import com.challenge.forohub.model.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tema")
@Getter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "id")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @ManyToOne
    private Usuario autor;
    private LocalDateTime fecha;
    @ManyToOne
    private Curso curso;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Repuesta> respuestas;
}
