package com.challenge.forohub.domain.model.tema;

import com.challenge.forohub.domain.dto.DatosRegistroTema;
import com.challenge.forohub.domain.dto.DatosActualizarTema;
import com.challenge.forohub.domain.model.curso.Curso;
import com.challenge.forohub.domain.model.respuesta.Respuesta;
import com.challenge.forohub.domain.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Usuario autorId;
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso cursoId;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @OneToMany(mappedBy = "temaId", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //momentaneamente
    private List<Respuesta> respuestas;
    private boolean activo;

    public Tema(Curso curso, Usuario usuario, DatosRegistroTema body) {
        this.titulo = body.titulo();
        this.mensaje = body.mensaje();
        this.autorId = usuario;
        this.fecha = LocalDateTime.now();
        this.cursoId = curso;
        this.status = Estado.ABIERTO;
        this.respuestas = new ArrayList<>();
        this.activo = true;
    }

    public void setRespuestas(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }

    public void actualizardatos(DatosActualizarTema body) {
        if(body.titulo() != null){
            this.titulo = body.titulo();
        }
        if(body.mensaje() != null){
            this.mensaje = body.mensaje();
        }
        if (body.status() != null){
            this.status = body.status();
        }
    }

    public void desactivarTema() {
        this.activo = false;
    }
}
