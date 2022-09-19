package com.ada.filmes.controller;

import com.ada.filmes.domain.Ator;
import com.ada.filmes.service.AtorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compostos")
@AllArgsConstructor
public class AtorController {

    private final AtorService atorService;

    @PostMapping
    public ResponseEntity<Ator> adicionaAtor(@RequestBody Ator ator) {
        atorService.adicionaAtor(ator);
        return new ResponseEntity<>(ator, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscaAtorPorId(@PathVariable Long id) {
        Ator ator = atorService.buscaPorId(id);
        return new ResponseEntity<>(ator, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Ator> atualizaAtor(@RequestBody Ator novoAtor) {
        Ator ator = atorService.atualizaAtor(novoAtor);
        return new ResponseEntity<>(ator, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Ator>> listarAtores() {
        List<Ator> listarAtores = atorService.listarAtores();
        return new ResponseEntity<>(listarAtores, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ator> removerAtor(@PathVariable Long id) {
        Ator ator = atorService.removeAtor(id);
        return new ResponseEntity<>(ator, HttpStatus.OK);
    }
}
