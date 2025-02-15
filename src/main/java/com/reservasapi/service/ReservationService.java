package com.reservasapi.service;

import com.reservasapi.dto.ReservationDTO;
import com.reservasapi.model.mapper.ReservationMapper;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.repository.ReservationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private static final ReservationMapper MAPPER = Mappers.getMapper(
        ReservationMapper.class
    );

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    //Criar uma nova reserva
    public Reservation createReservation(ReservationDTO reservationDTO) {
        return reservationRepository.save(MAPPER.toReservation(reservationDTO));
    }

    //Procurar uma reserva por ID
    public Optional<ReservationDTO> getReservationById(Long id) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElse(null);
        if (reservation == null) {
            return Optional.empty();
        } else {
            return Optional.of(MAPPER.toReservationDTO(reservation));
        }
    }

    //Atualizar uma reserva existente
    public Reservation updateReservation(
        Long id,
        ReservationDTO updatedReservationDTO
    ) {
        Optional<Reservation> existingReservation =
            reservationRepository.findById(id);

        if (existingReservation.isPresent()) {
            //Copiar os valores da updatedReservation para a reserva existente
            Reservation reservation = existingReservation.get();
            reservation.setCustomerName(
                MAPPER.toReservation(updatedReservationDTO).getCustomerName()
            );
            reservation.setCreationDate(
                MAPPER.toReservation(updatedReservationDTO).getCreationDate()
            );
            reservation.setPassengers(
                MAPPER.toReservation(updatedReservationDTO).getPassengers()
            );
            reservation.setServices(
                MAPPER.toReservation(updatedReservationDTO).getServices()
            );

            return reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation not found with ID" + id);
        }
    }

    //Apagar uma reserva
    public void deleteReservation(Long id) {
        Optional<Reservation> existingReservation =
            reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            reservationRepository.delete(existingReservation.get());
        } else {
            throw new RuntimeException("Reservation not found with ID" + id);
        }
    }

    //Listar todas as reservas
    public List<ReservationDTO> getAllReservations() {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(MAPPER.toReservationDTO(reservation));
        }
        return reservationDTOS;
    }

    //Encontrar reservas atraves do nome do cliente
    public List<ReservationDTO> getReservationsByClientName(
        String customerName
    ) {
        List<Reservation> reservations =
            reservationRepository.findByCustomerName(customerName);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(MAPPER.toReservationDTO(reservation));
        }
        return reservationDTOS;
    }
}
