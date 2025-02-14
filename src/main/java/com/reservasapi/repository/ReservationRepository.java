package com.reservasapi.repository;

import com.reservasapi.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    //Encontrar reservas pelo nome do cliente
    List<Reservation> findByCostumerName(String costumerName);

    //Listar todas as reservas
    List<Reservation> findAll();

    //Encontrar reserva por ID
    Optional<Reservation> findById(Long id);

}
