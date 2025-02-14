package com.reservasapi.service;

import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.model.servico.ReservationService;
import com.reservasapi.repository.ReservationRepository;
import com.reservasapi.repository.ReservationServiceRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceService {

  @Autowired
  private ReservationServiceRepository reservationServiceRepository;

  @Autowired
  private ReservationRepository reservationRepository;

  //Listar os servicos de uma reserva
  public List<ReservationService> getServicosByReservation(Long reservationId) {
    Optional<Reservation> reservation = reservationRepository.findById(
      reservationId
    );
    if (reservation.isPresent()) {
      return reservation.get().getServices();
    } else {
      throw new RuntimeException(
        "Reservation not found with ID: " + reservationId
      );
    }
  }

  //Adicionar um novo serviço a uma reserva
  @Transactional
  public ReservationService addServico(
    Long reservationId,
    ReservationService reservationService
  ) {
    Optional<Reservation> reservation = reservationRepository.findById(
      reservationId
    );
    if (reservation.isPresent()) {
      Reservation existingReservation = reservation.get();

      //Guarda o servico
      ReservationService savedReservationService =
        reservationServiceRepository.save(reservationService);

      reservationRepository.save(existingReservation);

      return savedReservationService;
    } else {
      throw new RuntimeException(
        "Reservation not found with ID: " + reservationId
      );
    }
  }

  //Update um servico existente
  @Transactional
  public ReservationService updateServico(
    Long reservationId,
    Long servicoId,
    ReservationService updatedReservationService
  ) {
    Optional<Reservation> reservation = reservationRepository.findById(
      reservationId
    );
    if (reservation.isPresent()) {
      Optional<ReservationService> servico =
        reservationServiceRepository.findById(servicoId);
      if (servico.isPresent()) {
        ReservationService existingReservationService = servico.get();
        existingReservationService.setName(updatedReservationService.getName());
        existingReservationService.setDescription(
          updatedReservationService.getDescription()
        );
        existingReservationService.setPrice(
          updatedReservationService.getPrice()
        );

        ReservationService savedReservationService =
          reservationServiceRepository.save(existingReservationService);

        reservationRepository.save(reservation.get());

        return savedReservationService;
      } else {
        throw new RuntimeException("Service not found with ID: " + servicoId);
      }
    } else {
      throw new RuntimeException(
        "Reservation not found with ID: " + reservationId
      );
    }
  }

  //Apagar um servico de uma reserva
  @Transactional
  public void deleteServico(Long reservationId, Long servicoId) {
    /* TODO Fix
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Reservation existingReservation = reservation.get();
            Optional<ReservationService> servico = servicoRepository.findById(servicoId);
            if(servico.isPresent()){
                ReservationService existingReservationService = servico.get();
                if(existingReservationService.getReservation().equals(existingReservation)){
                    servicoRepository.deleteById(servicoId);

                    reservationRepository.save(existingReservation);
                } else {
                    throw new RuntimeException("This service does not belong to the reservation with ID: " + reservationId);
                }
            } else {
                throw new RuntimeException("Service not found with ID: " + servicoId);
            }
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
         */
  }
}
