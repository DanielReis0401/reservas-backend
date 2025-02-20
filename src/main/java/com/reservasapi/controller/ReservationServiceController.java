package com.reservasapi.controller;

import com.reservasapi.dto.ReservationServiceDTO;
import com.reservasapi.model.service.ReservationService;
import com.reservasapi.service.ReservationServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservationsServices")
@Tag(
    name = "Reservation Services",
    description = "Endpoints for managing services related to reservations"
)
public class ReservationServiceController {

    @Autowired
    private ReservationServiceService reservationServiceService;

    @GetMapping("/{reservationId}")
    @Operation(
        summary = "Get services by reservation",
        description = "Fetches all services associated with a specific reservation"
    )
    public List<ReservationService> getServicosByReservation(
        @PathVariable Long reservationId
    ) {
        return reservationServiceService.getServicosByReservation(
            reservationId
        );
    }

    @PostMapping("/{reservationId}")
    @Operation(
        summary = "Add a service to a reservation",
        description = "Adds a new service to the specified reservation"
    )
    public ResponseEntity<ReservationService> addServico(
        @PathVariable Long reservationId,
        @RequestBody ReservationServiceDTO reservationServiceDTO
    ) {
        return ResponseEntity.ok(
            reservationServiceService.addServico(
                reservationId,
                reservationServiceDTO
            )
        );
    }

    @PutMapping("/{reservationId}/{servicoId}")
    @Operation(
        summary = "Update a service for a reservation",
        description = "Updates the details of an existing service for a reservation"
    )
    public ResponseEntity<ReservationService> updateServico(
        @PathVariable Long reservationId,
        @PathVariable Long servicoId,
        @RequestBody ReservationServiceDTO updatedServiceDTO
    ) {
        return ResponseEntity.ok(
            reservationServiceService.updateServico(
                reservationId,
                servicoId,
                updatedServiceDTO
            )
        );
    }

    @DeleteMapping
    @Operation(
        summary = "Delete a service from a reservation",
        description = "Removes a service from the specified reservation"
    )
    public ResponseEntity<Void> deleteServico(
        @RequestParam Long reservationId,
        @RequestParam Long servicoId
    ) {
        reservationServiceService.deleteServico(reservationId, servicoId);
        return ResponseEntity.noContent().build();
    }
}
