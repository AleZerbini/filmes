package com.ada.filmes.service;


import com.ada.filmes.domain.Ator;
import com.ada.filmes.repository.AtorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AtorService {

    private AtorRepository atorRepository;

    public Ator adicionaAtor(Ator Ator) {
        return atorRepository.save(Ator);
    }

    public List<Ator> listarAtores() {
        return atorRepository.findAll();
    }

    public Ator buscaPorId(Long id) {
        Optional<Ator> AtorOptional = atorRepository.findById(id);
        if (AtorOptional.isEmpty()) {
            throw new IllegalArgumentException("Id não é valido");
        }
        return AtorOptional.get();
    }

    public Ator removeAtor(Long id) {
        Ator Composto = this.buscaPorId(id);
        atorRepository.delete(Composto);
        return Composto;
    }

    public Ator atualizaAtor(Ator novoAtor) {
        Ator atorBanco = this.buscaPorId(novoAtor.getId());

        atorBanco.setNome(novoAtor.getNome());

        return this.adicionaAtor(atorBanco);
    }
}
