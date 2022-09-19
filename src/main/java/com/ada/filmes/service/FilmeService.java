package com.ada.filmes.service;

import com.ada.filmes.domain.Filme;
import com.ada.filmes.repository.FilmeRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public Filme adicionaRemedio(Filme filme) {
        return filmeRepository.save(filme);
    }

    public List<Filme> listarRemedios() {
        return filmeRepository.findAll();
    }

    public Filme buscaPorId(Long id) {
        Optional<Filme> filmeOptional = filmeRepository.findById(id);
        if (filmeOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não é valido");
        }
        return filmeOptional.get();
    }

    public Filme removeFilme(Long id) {
        Filme filme = this.buscaPorId(id);
        filmeRepository.delete(filme);
        return filme;
    }

    public Filme atualizaFilme(Filme novoFilme) {
        Filme filmeBanco = this.buscaPorId(novoFilme.getId());

        filmeBanco.setGenero(novoFilme.getGenero());
        filmeBanco.setNome(novoFilme.getNome());;
        filmeBanco.setListaDeAtores(novoFilme.getListaDeAtores());

        return this.adicionaRemedio(filmeBanco);
    }
}
