package com.challenge.forohub.domain.service;

import com.challenge.forohub.domain.dto.DatosActualizarTema;
import com.challenge.forohub.domain.dto.DatosRegistroTema;
import com.challenge.forohub.domain.dto.DatosRespuestaTema;
import com.challenge.forohub.domain.model.curso.Curso;
import com.challenge.forohub.domain.model.tema.Tema;
import com.challenge.forohub.domain.model.usuario.Usuario;
import com.challenge.forohub.domain.repository.CursoRepository;
import com.challenge.forohub.domain.repository.TemaRepository;
import com.challenge.forohub.domain.repository.UsuarioRepository;
import com.challenge.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TemaService {

    final
    TemaRepository temaRepository;
    final
    CursoRepository cursoRepository;
    final
    UsuarioRepository usuarioRepository;

    public TemaService(TemaRepository temaRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.temaRepository = temaRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public DatosRespuestaTema generarTema(DatosRegistroTema datos) {

        Optional<Usuario> usuario = usuarioRepository.findById(datos.autorId());
        Optional<Curso> curso = cursoRepository.findByNombre(datos.nombreCurso());

        if(temaRepository.findByTitulo(datos.titulo()).isPresent())
            throw new ValidacionDeIntegridad("El tema ya existe");

        if (usuario.isEmpty())
            throw new ValidacionDeIntegridad("El usuario no existe");

        if(curso.isEmpty())
            throw new ValidacionDeIntegridad("El curso no existe");

        Tema temaGuardado = temaRepository.save(new Tema(curso.get(), usuario.get(), datos));

        return new DatosRespuestaTema(temaGuardado);
    }

    public Page<DatosRespuestaTema> listarTemas(Pageable page){
        Page<Tema> temas = temaRepository.findByActivoTrue(page);
        if(temas.isEmpty())
            throw new ValidacionDeIntegridad("No hay temas que mostrar");

        return temas.map(DatosRespuestaTema::new);
    }

    public DatosRespuestaTema getById(Long id){
        Optional<Tema> tema = temaRepository.findById(id);

        if(tema.isEmpty())
            throw new ValidacionDeIntegridad("El id del  tema no existe");

        if(!tema.get().isActivo())
            throw new ValidacionDeIntegridad("El tema que desea recuperar no esta activo");

        return new DatosRespuestaTema(tema.get());
    }

    @Transactional
    public DatosRespuestaTema actualizarTema(DatosActualizarTema datos) {
        Optional<Tema> tema = temaRepository.findById(datos.id());

        if (tema.isEmpty())
            throw new ValidacionDeIntegridad("El id del  tema  que desea actualizar no existe");

        if(!tema.get().isActivo())
            throw new ValidacionDeIntegridad("El tema que desea actualizar no esta activo");

        tema.get().actualizardatos(datos);
        return new DatosRespuestaTema(tema.get());
    }

    @Transactional
    public void borrarTema(Long id) {
        Optional<Tema> tema = temaRepository.findById(id);

        if (tema.isEmpty())
            throw new ValidacionDeIntegridad("El id del  tema  que desea borrar no existe");

        tema.get().desactivarTema();
    }

}
