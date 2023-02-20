package com.gabriel.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
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
