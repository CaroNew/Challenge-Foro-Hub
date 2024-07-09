package com.challenge.forohub.controller;

import com.challenge.forohub.domain.dto.DatosRegistroTema;
import com.challenge.forohub.domain.dto.DatosRespuestaTema;
import com.challenge.forohub.domain.dto.DatosActualizarTema;
import com.challenge.forohub.domain.service.TemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/temas")
@SecurityRequirement(name = "bearer-key")
public class TemaController {

    final
    TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }



    @PostMapping
    public ResponseEntity<DatosRespuestaTema> create(@RequestBody @Valid DatosRegistroTema body,
                                                     UriComponentsBuilder uriBuilder) {
        DatosRespuestaTema respuestaTema = temaService.generarTema(body);
        URI url = uriBuilder.path("/api/temas/{id}").buildAndExpand(respuestaTema.id()).toUri();
        return ResponseEntity.created(url).body(respuestaTema);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTema>> list(@PageableDefault(size = 5) Pageable page) {
        return ResponseEntity.ok(temaService.listarTemas(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTema> get(@PathVariable Long id) {
        return ResponseEntity.ok(temaService.getById(id));
    }

    @PutMapping
    public ResponseEntity<DatosRespuestaTema> update(@RequestBody @Valid DatosActualizarTema body) {
        return ResponseEntity.ok(temaService.actualizarTema(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        temaService.borrarTema(id);
        return ResponseEntity.noContent().build();
    }
}
