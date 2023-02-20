package com.gabriel.spring.controller;

import java.util.ArrayList;

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
import com.gabriel.spring.model.Telefone;
import com.gabriel.spring.repository.PessoaRepository;
import com.gabriel.spring.repository.TelefoneRepository;

@Controller
@RequestMapping("/cadastropessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ModelAndView inicio() {
        return new ModelAndView("cadastro/cadastropessoa")
                .addObject("pessoas", pessoaRepository.findAll());
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        return inicio();
    }

    @GetMapping("/editar/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") Long id) {
        return new ModelAndView("cadastro/editarpessoa")
                .addObject("pessoaobj", pessoaRepository.findById(id));
    }

    @GetMapping("/excluir/{idpessoa}")
    public ModelAndView excluir(@PathVariable("idpessoa") Long id) {
        pessoaRepository.deleteById(id);
        return inicio();
    }

    @PostMapping("/buscar")
    public ModelAndView buscar(@RequestParam("nomepesquisa") String nome) {
        return new ModelAndView("cadastro/cadastropessoa")
                .addObject("pessoas", pessoaRepository.findByName(nome));
    }

    @GetMapping("/detalhar/{idpessoa}")
    public ModelAndView detalhar(@PathVariable("idpessoa") Long id) {
        return new ModelAndView("cadastro/detalharpessoa")
                .addObject("pessoaobj", pessoaRepository.findById(id).get())
                .addObject("telefones", telefoneRepository.findAllByPessoaId(id));
    }

    @PostMapping("/telefone/salvar")
    public ModelAndView salvarTelefone(@RequestParam("pessoa_id") Long pessoaIdParam,
            @RequestParam("telefone") String telefoneParam,
            @RequestParam("tipotelefone") String tipoTelefoneParam) {

        Pessoa pessoa = pessoaRepository.findById(pessoaIdParam).get();
        telefoneRepository.save(new Telefone(telefoneParam, tipoTelefoneParam, pessoa));

        return new ModelAndView("cadastro/detalharpessoa")
                .addObject("pessoaobj", pessoa)
                .addObject("telefones", telefoneRepository.findAllByPessoaId(pessoaIdParam));
    }

    @GetMapping("/telefone/excluir/{idtelefone}")
    public ModelAndView excluirTelefone(@PathVariable("idtelefone") Long id) {
        Long pessoaId = telefoneRepository.findById(id).get().getPessoa().getId();
        telefoneRepository.deleteById(id);
        return new ModelAndView("cadastro/detalharpessoa")
                .addObject("pessoaobj", pessoaRepository.findById(pessoaId).get())
                .addObject("telefones", telefoneRepository.findAllByPessoaId(pessoaId));
    }
}
