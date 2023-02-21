package com.gabriel.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

import jakarta.validation.Valid;

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
    public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> erros = new ArrayList<>();
            for (ObjectError objectError : bindingResult.getAllErrors())
                erros.add(objectError.getDefaultMessage());

            return new ModelAndView("cadastro/cadastropessoa")
                    .addObject("pessoas", pessoaRepository.findAll())
                    .addObject("erros", erros);
        }

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
                .addObject("campospreenchidos", new Telefone("","",null))
                .addObject("telefones", telefoneRepository.findAllByPessoaId(id));
    }

    @PostMapping("/telefone/salvar")
    public ModelAndView salvarTelefone(@Valid Telefone telefone, BindingResult bindingResult) {
        Pessoa pessoa = pessoaRepository.findById(telefone.getPessoaId()).get();

        if (bindingResult.hasErrors()) {
            List<String> erros = new ArrayList<>();
            for (ObjectError objectError : bindingResult.getAllErrors())
                erros.add(objectError.getDefaultMessage());
            return new ModelAndView("cadastro/detalharpessoa")
                    .addObject("pessoaobj", pessoa)
                    .addObject("erros", erros)
                    .addObject("campospreenchidos", telefone)
                    .addObject("telefones", telefoneRepository.findAllByPessoaId(telefone.getPessoaId()));
        }

        telefone.setPessoa(pessoa);
        telefoneRepository.save(telefone);

        return new ModelAndView("cadastro/detalharpessoa")
                .addObject("pessoaobj", pessoa)
                .addObject("campospreenchidos", new Telefone("","",null))
                .addObject("telefones", telefoneRepository.findAllByPessoaId(telefone.getPessoaId()));
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
