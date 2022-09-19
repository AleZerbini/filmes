package com.ada.filmes.service;

import com.ada.filmes.domain.Filme;
import com.ada.filmes.repository.FilmeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class FilmesServiceTest {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private FilmeRepository filmeRepository;

    @BeforeEach
    public void limpaBanco() { filmeRepository.deleteAll(); }

    @Test
    public void testaInsereNoBanco() {
        Filme filme = Filme.builder()
                .nome("Grease")
                .genero("Musical")
                .build();

        filmeService.adicionaFilme(filme);

        List<Filme> filmes = filmeService.listarFilmes();

        // Inseriu mesmo?
        Assertions.assertEquals(1, filmes.size());
        // Inseriu certo?
        Assertions.assertEquals("Grease", filmes.get(0).getNome());
        Assertions.assertEquals("Musical", filmes.get(0).getGenero());
    }

    @Test
    public void testBuscaPorId() {
        filmeService.buscaPorId(1L);

        // Pelo @BeforeEach que não vamos ter nada no banco.
    }

}
