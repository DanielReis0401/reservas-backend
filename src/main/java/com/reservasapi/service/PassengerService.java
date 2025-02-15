package com.reservasapi.service;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.responses.PassengersResponse;
import com.reservasapi.exceptions.NotFoundException;
import com.reservasapi.model.mapper.PassengerMapper;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.repository.PassengerRepository;
import com.reservasapi.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService {

    private static final PassengerMapper MAPPER = Mappers.getMapper(
        PassengerMapper.class
    );

    private static final String RESERVATION_NOT_FOUND =
        "Reservation not found with ID: ";

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public PassengersResponse getPassengers(Long reservationId) {
        Reservation reservation = reservationRepository
            .findById(reservationId)
            .orElseThrow(() ->
                new NotFoundException(RESERVATION_NOT_FOUND + reservationId)
            );

        return PassengersResponse.builder()
            .passengers(
                reservation
                    .getPassengers()
                    .stream()
                    .map(MAPPER::toPassengerDTO)
                    .toList()
            )
            .build();
    }

    @Transactional
    public PassengerDTO addPassenger(
        Long reservationId,
        PassengerDTO passengerDTO
    ) {
        // Buscar a reserva pelo ID
        Reservation reservation = reservationRepository
            .findById(reservationId)
            .orElseThrow(() ->
                new NotFoundException(
                    "Reserva não encontrada com ID: " + reservationId
                )
            );

        // Verificar se o passageiro já existe, caso contrário, criar um novo
        Passenger passenger = Optional.ofNullable(passengerDTO.getId())
            .flatMap(passengerRepository::findById)
            .orElseGet(() -> MAPPER.toPassenger(passengerDTO));

        // Adicionar o passageiro à reserva
        passenger.addReservation(reservation);
        passengerRepository.save(passenger);

        // Retornar o PassengerDTO com os dados do passageiro atualizado
        return MAPPER.toPassengerDTO(passenger);
    }

    //Update a um passageiro
    @Transactional
    public Passenger updatePassenger(
        Long reservationId,
        Long passengerId,
        PassengerDTO updatedPassengerDTO
    ) {
        // Buscar a reserva
        Reservation reservation = reservationRepository
            .findById(reservationId)
            .orElseThrow(() ->
                new RuntimeException(
                    "Reservation not found with ID: " + reservationId
                )
            );

        // Buscar o passageiro
        Passenger existingPassenger = passengerRepository
            .findById(passengerId)
            .orElseThrow(() ->
                new RuntimeException(
                    "Passenger not found with ID: " + passengerId
                )
            );

        // Atualizar os dados do passageiro
        existingPassenger.setName(updatedPassengerDTO.getName());
        existingPassenger.setAge(updatedPassengerDTO.getAge());
        existingPassenger.setType(updatedPassengerDTO.getType());

        // Salvar o passageiro atualizado
        return passengerRepository.save(existingPassenger);
    }

    //Apagar passageiro de uma reserva
    @Transactional
    public void deletePassenger(Long reservationId, Long passengerId) {
        // Verificar se a reserva existe
        Reservation reservation = reservationRepository
            .findById(reservationId)
            .orElseThrow(() ->
                new NotFoundException(
                    "Reservation not found with ID: " + reservationId
                )
            );

        // Verificar se o passageiro existe
        Passenger passenger = passengerRepository
            .findById(passengerId)
            .orElseThrow(() ->
                new NotFoundException(
                    "Passenger not found with ID: " + passengerId
                )
            );

        // Remover a associação do passageiro com a reserva, se existir
        if (reservation.getPassengers().contains(passenger)) {
            reservation.getPassengers().remove(passenger);
            reservationRepository.save(reservation); // Salvar a reserva com a associação removida
        }

        // Excluir o passageiro
        passengerRepository.delete(passenger); // Excluir o passageiro
    }
}
