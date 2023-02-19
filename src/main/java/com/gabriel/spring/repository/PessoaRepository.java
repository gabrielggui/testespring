package com.gabriel.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.spring.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
    
    @Query("select p from Pessoa p where p.nome like %?1%")
    public Iterable<Pessoa> findByName(String nome);
}
