package com.reservasapi.repository;

import com.reservasapi.model.passenger.Passenger;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
  //Encontrar passageiros por nome
  List<Passenger> findByName(String name);

  //Listar todos passageiros
  List<Passenger> findAll();

  //Encontrar passageiro por ID
  Optional<Passenger> findById(Long id);
}
