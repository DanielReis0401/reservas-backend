package com.reservasapi.repository;

import com.reservasapi.model.service.ReservationService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationServiceRepository
    extends JpaRepository<ReservationService, Long> {
    //Encontrar servicos por nome
    List<ReservationService> findByName(String name);

    //Listar todos os servicos
    List<ReservationService> findAll();

    //Encontrar servico por ID
    Optional<ReservationService> findById(Long id);
}
