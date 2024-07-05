package com.challenge.forohub.domain.repository;


import com.challenge.forohub.domain.model.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String s);
}
