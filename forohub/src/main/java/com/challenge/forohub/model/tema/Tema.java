package com.challenge.forohub.model.tema;

import com.challenge.forohub.model.respuesta.Respuesta;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autorId;
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso cursoId;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @OneToMany(mappedBy = "temaId", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //momentaneamente
    private List<Respuesta> respuestas;
}
