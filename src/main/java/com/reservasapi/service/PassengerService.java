package com.reservasapi.service;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.requests.PassengerRequest;
import com.reservasapi.dto.responses.PassengersResponse;
import com.reservasapi.exceptions.NotFoundException;
import com.reservasapi.model.mapper.PassengerMapper;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.repository.PassengerRepository;
import com.reservasapi.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PassengerDTO addPassenger(PassengerRequest request) {
        Reservation reservation = reservationRepository
            .findById(request.getReservationId())
            .orElseThrow(() ->
                new NotFoundException(
                    RESERVATION_NOT_FOUND + request.getReservationId()
                )
            );

        Passenger passenger = Optional.ofNullable(
            request.getPassenger().getId()
        )
            .flatMap(passengerRepository::findById)
            .orElseGet(() -> MAPPER.toPassenger(request.getPassenger()));

        passenger.addReservation(reservation);
        passengerRepository.save(passenger);

        return MAPPER.toPassengerDTO(passenger);
    }

    //Update a um passageiro
    @Transactional
    public Passenger updatePassenger(
        Long reservationId,
        Long passengerId,
        PassengerDTO updatedPassengerDTO
    ) {
        Optional<Reservation> reservation = reservationRepository.findById(
            reservationId
        );
        if (reservation.isPresent()) {
            Optional<Passenger> passenger = passengerRepository.findById(
                passengerId
            );
            if (passenger.isPresent()) {
                Passenger existingPassenger = passenger.get();
                existingPassenger.setName(
                    MAPPER.toPassenger(updatedPassengerDTO).getName()
                );
                existingPassenger.setAge(
                    MAPPER.toPassenger(updatedPassengerDTO).getAge()
                );
                existingPassenger.setType(
                    MAPPER.toPassenger(updatedPassengerDTO).getType()
                );
                return passengerRepository.save(existingPassenger); //Guarda o passageiro apos o update
            } else {
                throw new RuntimeException(
                    "Passenger not found with ID: " + passengerId
                );
            }
        } else {
            throw new RuntimeException(
                "Reservation not found with ID: " + reservationId
            );
        }
    }

    //Apagar passageiro de uma reserva
    @Transactional
    public void deletePassenger(Long reservationId, Long passengerId) {
        /* TODO: Fix

        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent()) {
            Optional<Passenger> passenger = passengerRepository.findById(passengerId);
            if (passenger.isPresent()) {
                Passenger existingPassenger = passenger.get();
                if (existingPassenger.getReservation().equals(reservation.get())) {
                    passengerRepository.deleteById(passengerId);    //Apaga o passeiro 
                } else {
                    throw new RuntimeException("This passenger does not belong to the reservation with ID: " + reservationId);
                }
            } else {
                throw new RuntimeException("Passenger not found with ID: " + passengerId);
            }
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
         */
    }
}
