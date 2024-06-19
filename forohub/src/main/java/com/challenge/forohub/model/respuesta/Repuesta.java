package com.challenge.forohub.model.respuesta;

import com.challenge.forohub.model.tema.Tema;
import com.challenge.forohub.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "repuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Repuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    private Usuario autor;
    private LocalDateTime fecha;
    @ManyToOne
    private Tema tema;
}
