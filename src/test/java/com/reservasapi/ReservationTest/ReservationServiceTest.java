package com.reservasapi.ReservationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.reservasapi.dto.ReservationDTO;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.repository.ReservationRepository;
import com.reservasapi.service.ReservationService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void testCreateReservation() {
        // Criar um objeto DTO de exemplo
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("Daniel");
        reservationDTO.setCreationDate(LocalDate.of(2025, 1, 1));

        // Criar um objeto de reserva correspondente
        Reservation reservation = new Reservation();
        reservation.setCustomerName("Daniel");
        reservation.setCreationDate(LocalDate.of(2025, 1, 1));

        // Stubbing: Quando o repositório salva a reserva e retorna o mesmo objeto
        when(reservationRepository.save(any())).thenReturn(reservation);

        // Act: Chama o método createReservation do serviço
        Reservation createdReservation = reservationService.createReservation(
            reservationDTO
        );

        // Assert: Verifica se o repositório foi chamado e se o retorno é o esperado
        assertNotNull(
            createdReservation,
            "The created reservation should not be null."
        );
        assertEquals("Daniel", createdReservation.getCustomerName());
        assertEquals(
            LocalDate.of(2025, 1, 1),
            createdReservation.getCreationDate()
        );

        // Verifica se o mapper e o repositório foram chamados corretamente
        verify(reservationRepository, times(1)).save(any());
    }
}
