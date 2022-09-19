package com.ada.filmes.mockito;

import com.ada.filmes.domain.Ator;
import com.ada.filmes.domain.Filme;
import com.ada.filmes.repository.FilmeRepository;
import com.ada.filmes.service.FilmeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AtualizaFilmeTest {

    @Mock
    private FilmeRepository filmeRepository;

    @InjectMocks
    private FilmeService filmeService;

    @Test
    public void deveAtualizarFilme() {
        Filme flmeAntigo = Filme.builder()
                .genero("Gênero antigo")
                .id(1L)
                .nome("Nome antigo!")
                .listaDeAtores(List.of(Ator.builder().nome("cafeina").build()
                )).build();


        Filme filmeNovo = Filme.builder()
                .genero("Gênero novo")
                .id(1L)
                .nome("Nome novo!")
                .listaDeAtores(Collections.emptyList())
                .build();

        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.of(flmeAntigo));
        Mockito.when(filmeRepository.save(filmeNovo)).thenReturn(filmeNovo);

        Filme filmeRetornado = filmeService.atualizaFilme(filmeNovo);
        Mockito.verify(filmeRepository, Mockito.times(1)).save(filmeNovo);

        Assertions.assertEquals(filmeRetornado, filmeNovo);
    }

    @Test
    public void naoDeveAtualizarFilme() {
        Filme remedioNovo = Filme.builder()
                .genero("Gênero novo")
                .id(1L)
                .nome("Nome novo!")
                .listaDeAtores(Collections.emptyList())
                .build();

        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filmeService.atualizaFilme(remedioNovo));

    }
}
