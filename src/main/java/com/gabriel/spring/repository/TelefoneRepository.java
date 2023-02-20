package com.gabriel.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.spring.model.Telefone;

@Repository
@Transactional
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {

    @Query("select t from Telefone t where t.pessoa.id = ?1")
    public Iterable<Telefone> findAllByPessoaId(Long id);
}
