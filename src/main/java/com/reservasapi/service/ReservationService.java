package com.reservasapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.repository.ReservationRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    
    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    //Criar uma nova reserva
    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    //Procurar uma reserva por ID
    public Optional<Reservation> getReservationById(Long id){
        return reservationRepository.findById(id);
    }

    //Atualizar uma reserva existente
    public Reservation updateReservation(Long id, Reservation updatedReservation){
        Optional<Reservation> existingReservation = reservationRepository.findById(id);

        if(existingReservation.isPresent()){
            //Copiar os valores da updatedReservation para a reserva existente
            Reservation reservation = existingReservation.get();
            reservation.setCustomerName(updatedReservation.getCustomerName());
            reservation.setReservationDate(updatedReservation.getReservationDate());
            reservation.setPassengers(updatedReservation.getPassengers());
            reservation.setServicos(updatedReservation.getServicos());

            //Recalcular o preco total
            reservation.calculateTotalPrice();

            return reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation not found with ID" + id);
        }
    }

    //Apagar uma reserva
    public void deleteReservation(Long id){
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if(existingReservation.isPresent()){
            reservationRepository.delete(existingReservation.get());
        } else {
            throw new RuntimeException("Reservation not found with ID" + id);
        }
    }

    //Listar todas as reservas
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    //Encontrar reservas atraves do nome do cliente
    public List<Reservation> getReservationsByClientName(String customerName){
        return reservationRepository.findByCostumerName(customerName);
    } 

    //Calcular preco total de uma reserva
    public double calculateTotalPrice(Reservation reservation){
        reservation.calculateTotalPrice();
        return reservation.getTotalPrice();
    }

}
