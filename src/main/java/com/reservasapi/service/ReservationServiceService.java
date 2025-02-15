package com.reservasapi.service;

import com.reservasapi.dto.ReservationServiceDTO;
import com.reservasapi.exceptions.NotFoundException;
import com.reservasapi.model.mapper.ReservationServiceMapper;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.model.service.ReservationService;
import com.reservasapi.repository.ReservationRepository;
import com.reservasapi.repository.ReservationServiceRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceService {

    private static final ReservationServiceMapper MAPPER = Mappers.getMapper(
        ReservationServiceMapper.class
    );

    @Autowired
    private ReservationServiceRepository reservationServiceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    //Listar os servicos de uma reserva
    public List<ReservationService> getServicosByReservation(
        Long reservationId
    ) {
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
        ReservationServiceDTO reservationServiceDTO
    ) {
        // Busca a reserva pelo ID
        Reservation reservation = reservationRepository
            .findById(reservationId)
            .orElseThrow(() ->
                new RuntimeException(
                    "Reservation not found with ID: " + reservationId
                )
            );

        // Criar o serviço e adicioná-lo à reserva
        ReservationService newService = MAPPER.toReservationService(
            reservationServiceDTO
        );
        reservation.getServices().add(newService); // Associar serviço à reserva

        // Salvar o serviço e a reserva
        reservationServiceRepository.save(newService);
        reservationRepository.save(reservation); //

        return newService;
    }

    //Update um servico existente
    @Transactional
    public ReservationService updateServico(
        Long reservationId,
        Long servicoId,
        ReservationServiceDTO updatedReservationServiceDTO
    ) {
        Optional<Reservation> reservation = reservationRepository.findById(
            reservationId
        );
        if (reservation.isPresent()) {
            Optional<ReservationService> servico =
                reservationServiceRepository.findById(servicoId);
            if (servico.isPresent()) {
                ReservationService existingReservationService = servico.get();
                existingReservationService.setName(
                    MAPPER.toReservationService(
                        updatedReservationServiceDTO
                    ).getName()
                );
                existingReservationService.setDescription(
                    MAPPER.toReservationService(
                        updatedReservationServiceDTO
                    ).getDescription()
                );
                existingReservationService.setPrice(
                    MAPPER.toReservationService(
                        updatedReservationServiceDTO
                    ).getPrice()
                );

                ReservationService savedReservationService =
                    reservationServiceRepository.save(
                        existingReservationService
                    );

                reservationRepository.save(reservation.get());

                return savedReservationService;
            } else {
                throw new RuntimeException(
                    "Service not found with ID: " + servicoId
                );
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
        // Buscar a reserva pelo ID
        Reservation reservation = reservationRepository
            .findById(reservationId)
            .orElseThrow(() ->
                new NotFoundException(
                    "Reservation not found with ID: " + reservationId
                )
            );

        // Buscar o serviço dentro da reserva
        ReservationService serviceToRemove = reservation
            .getServices()
            .stream()
            .filter(service -> service.getId().equals(servicoId))
            .findFirst()
            .orElseThrow(() ->
                new NotFoundException("Service not found with ID: " + servicoId)
            );

        // Remover o serviço da reserva
        reservation.getServices().remove(serviceToRemove);

        // Salvar a reserva atualizada
        reservationRepository.save(reservation);

        // Apagar o serviço do repositório
        reservationServiceRepository.delete(serviceToRemove);
    }
}
