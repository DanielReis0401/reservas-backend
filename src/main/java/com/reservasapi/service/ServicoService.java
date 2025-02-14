package com.reservasapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.model.servico.ReservationService;
import com.reservasapi.repository.ReservationRepository;
import com.reservasapi.repository.ServicoRepository;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    //Listar os servicos de uma reserva
    public List<ReservationService> getServicosByReservation(Long reservationId){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            return reservation.get().getReservationServices();
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId); 
        }
    }

    //Adicionar um novo serviço a uma reserva
    @Transactional
    public ReservationService addServico(Long reservationId, ReservationService reservationService){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Reservation existingReservation = reservation.get();
            reservationService.setReservation(existingReservation);

            //Guarda o servico
            ReservationService savedReservationService = servicoRepository.save(reservationService);

            //Atualiza o preco total da reserva
            existingReservation.calculateTotalPrice();
            reservationRepository.save(existingReservation);

            return savedReservationService;
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
    }

    //Update um servico existente
    @Transactional
    public ReservationService updateServico(Long reservationId, Long servicoId, ReservationService updatedReservationService){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Optional<ReservationService> servico = servicoRepository.findById(servicoId);
            if(servico.isPresent()){
                ReservationService existingReservationService = servico.get();
                existingReservationService.setName(updatedReservationService.getName());
                existingReservationService.setDescription(updatedReservationService.getDescription());
                existingReservationService.setPrice(updatedReservationService.getPrice());

                ReservationService savedReservationService = servicoRepository.save(existingReservationService);

                //Atualiza o preco total da reserva
                reservation.get().calculateTotalPrice();
                reservationRepository.save(reservation.get());

                return savedReservationService;
            } else {
                throw new RuntimeException("Service not found with ID: " + servicoId);
            }
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
    }

    //Apagar um servico de uma reserva
    @Transactional
    public void deleteServico(Long reservationId, Long servicoId){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Reservation existingReservation = reservation.get();
            Optional<ReservationService> servico = servicoRepository.findById(servicoId);
            if(servico.isPresent()){
                ReservationService existingReservationService = servico.get();
                if(existingReservationService.getReservation().equals(existingReservation)){
                    servicoRepository.deleteById(servicoId);

                    //Atualiza o preco total da reserva
                    existingReservation.calculateTotalPrice();
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
    }
}
