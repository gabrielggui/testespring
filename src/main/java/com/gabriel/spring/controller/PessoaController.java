package com.gabriel.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.spring.model.Pessoa;
import com.gabriel.spring.repository.PessoaRepository;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
    public ModelAndView inicio() {
        return listaDePessoas();
    }

    @PostMapping("/salvarpessoa")
    public ModelAndView salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        return listaDePessoas();
    }

    @GetMapping("/listapessoas")
    public ModelAndView listaDePessoas() {
        Iterable<Pessoa> pessoaIterable = pessoaRepository.findAll();
        return new ModelAndView("cadastro/cadastropessoa")
                .addObject("pessoas", pessoaIterable);
    }
}
