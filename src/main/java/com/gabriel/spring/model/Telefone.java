package com.gabriel.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long pessoaId;

    @NotNull(message = "Telefone não pode ser nulo")
    @NotEmpty(message = "Telefone não pode ser vazio")
    private String numero;

    @NotNull(message = "Categoria não pode ser nulo")
    @NotEmpty(message = "Categoria não pode ser vazio")
    private String categoria;
    @ManyToOne
    private Pessoa pessoa;

    public Telefone() {

    }

    public Telefone(Long id) {
        this.id = id;
    }

    public Telefone(String numero, String categoria, Pessoa pessoa) {
        this.numero = numero;
        this.categoria = categoria;
        this.pessoa = pessoa;
    }

    public Telefone(Long id, String numero, String categoria, Pessoa pessoa) {
        this.id = id;
        this.numero = numero;
        this.categoria = categoria;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPessoaId() {
        return this.pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
