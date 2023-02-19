package com.gabriel.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.spring.model.Pessoa;
import com.gabriel.spring.repository.PessoaRepository;

/**
 * Cadastrar pessoa
 * listar pessoas
 * Editar pessoa
 * Deletar pessoa
 */

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

    @GetMapping("/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).get();
        return new ModelAndView("cadastro/editarpessoa")
                .addObject("pessoaobj",pessoa);
    }

    @GetMapping("/excluirpessoa/{idpessoa}")
    public ModelAndView excluir(@PathVariable("idpessoa") Long id) {
        pessoaRepository.deleteById(id);
        return listaDePessoas();
    }
    
    @PostMapping("/buscarpessoa")
    public ModelAndView buscar(@RequestParam("nomepesquisa") String nome) {
        return new ModelAndView("cadastro/cadastropessoa")
                .addObject("pessoas",pessoaRepository.findByName(nome));
    }

}
