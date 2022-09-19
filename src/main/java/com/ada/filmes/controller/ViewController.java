package com.ada.filmes.controller;

import com.ada.filmes.domain.Ator;
import com.ada.filmes.domain.Filme;
import com.ada.filmes.service.AtorService;
import com.ada.filmes.service.FilmeService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ViewController {

    private FilmeService filmeService;
    private AtorService atorService;

    @GetMapping("/home")
    public String home(Model model) {
        List<Filme> lista = this.filmeService.listarFilmes();

        model.addAttribute("filmes", lista);
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Filme filme = new Filme();
        filme.setListaDeAtores(List.of
                (new Ator(), new Ator(), new Ator()));

        model.addAttribute("filme", filme);
        model.addAttribute("atores", atorService.listarAtores());
        return "create";
    }

    @GetMapping("/create-ator")
    public String createAtor(Model model) {
        model.addAttribute("ator", new Ator());
        return "create-ator";
    }

    @PostMapping("/save")
    public String save(@Valid Filme filme, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        filmeService.adicionaFilme(filme);
        return "redirect:/home";
    }

    @PostMapping("/save-ator")
    public String saveComposto(@Valid Ator ator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        atorService.adicionaAtor(ator);
        return "redirect:/home";
    }
}
