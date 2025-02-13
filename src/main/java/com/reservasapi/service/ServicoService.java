package com.reservasapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.model.servico.Servico;
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
    public List<Servico> getServicosByReservation(Long reservationId){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            return reservation.get().getServicos();
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId); 
        }
    }

    //Adicionar um novo serviço a uma reserva
    @Transactional
    public Servico addServico(Long reservationId, Servico servico){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Reservation existingReservation = reservation.get();
            servico.setReservation(existingReservation);

            //Guarda o servico
            Servico savedServico = servicoRepository.save(servico);

            //Atualiza o preco total da reserva
            existingReservation.calculateTotalPrice();
            reservationRepository.save(existingReservation);

            return savedServico;
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
    }

    //Update um servico existente
    @Transactional
    public Servico updateServico(Long reservationId, Long servicoId, Servico updatedServico){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if(reservation.isPresent()){
            Optional<Servico> servico = servicoRepository.findById(servicoId);
            if(servico.isPresent()){
                Servico existingServico = servico.get();
                existingServico.setName(updatedServico.getName());
                existingServico.setDescription(updatedServico.getDescription());
                existingServico.setPrice(updatedServico.getPrice());

                Servico savedServico = servicoRepository.save(existingServico);

                //Atualiza o preco total da reserva
                reservation.get().calculateTotalPrice();
                reservationRepository.save(reservation.get());

                return savedServico;
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
            Optional<Servico> servico = servicoRepository.findById(servicoId);
            if(servico.isPresent()){
                Servico existingServico = servico.get();
                if(existingServico.getReservation().equals(existingReservation)){
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
