package com.challenge.forohub.controller;

import com.challenge.forohub.repository.TemaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/temas")
public class TemaController {

    final
    TemaRepository temaRepository;

    public TemaController(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @PostMapping
    public ResponseEntity<String> create() {
        System.out.println("Creating a new topic");
        return ResponseEntity.created(null).body("Topic created");
    }

    @GetMapping
    public ResponseEntity<String> list() {
        System.out.println("Listing all topics");
        return ResponseEntity.ok("Topics listed");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable Long id) {
        System.out.println("Getting topic with id " + id);
        return ResponseEntity.ok("Topic with id " + id + " retrieved");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id) {
        System.out.println("Updating topic with id " + id);
        return ResponseEntity.ok("Topic with id " + id + " updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        System.out.println("Deleting topic with id " + id);
        return ResponseEntity.ok("Topic with id " + id + " deleted");
    }
}
