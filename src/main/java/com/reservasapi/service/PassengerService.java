package com.reservasapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.repository.PassengerRepository;
import com.reservasapi.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    //Listar passageiros de uma reserva
    public List<Passenger> getPassengersByReservation(Long reservationId){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            return reservation.get().getPassengers();       //Retorna os passageiros de uma reserva
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
    }

    //Adicionar um novo passageiro a uma reserva
    @Transactional
    public Passenger addPassenger(Long reservationId, Passenger passenger){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            passenger.setReservation(reservation.get());    //Associa o passageiro a reserva
            return passengerRepository.save(passenger);     //Guarda o passageiro na database
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
    }


    //Update a um passageiro
    @Transactional
    public Passenger updatePassenger(Long reservationId, Long passengerId, Passenger updatedPassenger){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Optional<Passenger> passenger = passengerRepository.findById(passengerId);
            if(passenger.isPresent()){
                Passenger existingPassenger = passenger.get();
                existingPassenger.setName(updatedPassenger.getName());
                existingPassenger.setAge(updatedPassenger.getAge());
                existingPassenger.setType(updatedPassenger.getType());
                existingPassenger.setReservation(reservation.get());    //Certifica que o passegeiro esta na reserva
                return passengerRepository.save(existingPassenger);     //Guarda o passageiro apos o update
            } else {
                throw new RuntimeException("Passenger not found with ID: " + passengerId);
            }
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
    }

    //Apagar passageiro de uma reserva
    @Transactional
    public void deletePassenger(Long reservationId, Long passengerId){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Optional<Passenger> passenger = passengerRepository.findById(passengerId);
            if(passenger.isPresent()){
                Passenger existingPassenger = passenger.get();
                if(existingPassenger.getReservation().equals(reservation.get())){
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
    }
}
