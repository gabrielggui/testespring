package com.gabriel.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome não pode ser vazio")
    private String nome;
    
    @NotNull(message = "Sobrenome não pode ser nulo")
    @NotEmpty(message = "Sobrenome não pode ser vazio")
    private String sobrenome;
    
    @Min(value = 18, message = "Idade inválida")
    @NotNull(message = "Idade não pode ser vazio")
    private Integer idade;

    @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();

    public Pessoa() {

    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(String nome, String sobrenome, Integer idade, List<Telefone> telefones) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.telefones = telefones;
    }

    public Pessoa(Long id, String nome, String sobrenome, Integer idade, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.telefones = telefones;
    }

    public List<Telefone> getTelefones() {
        return this.telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

}
