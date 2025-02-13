package com.reservasapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservasapi.model.servico.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{

    //Encontrar servicos por nome
    List<Servico> findByName(String name);

    //Listar todos os servicos
    List<Servico> findAll();

    //Encontrar servico por ID
    Optional<Servico> findById(Long id);

}
