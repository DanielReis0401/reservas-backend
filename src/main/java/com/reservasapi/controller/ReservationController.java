package com.reservasapi.controller;

import com.reservasapi.dto.ReservationDTO;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservations", description = "Endpoints for managing reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    @Operation(
        summary = "Get all reservations",
        description = "Fetches all the reservations from the system"
    )
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a reservation by ID",
        description = "Fetches a reservation by its unique identifier"
    )
    public ResponseEntity<Optional<Reservation>> getReservationById(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping
    @Operation(
        summary = "Create a new reservation",
        description = "Creates a new reservation based on the provided details"
    )
    public ResponseEntity<Reservation> createReservation(
        @RequestBody ReservationDTO reservationDTO
    ) {
        return ResponseEntity.ok(
            reservationService.createReservation(reservationDTO)
        );
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing reservation",
        description = "Updates an existing reservation identified by the ID"
    )
    public ResponseEntity<Reservation> updateReservation(
        @PathVariable Long id,
        @RequestBody ReservationDTO updatedReservationDTO
    ) {
        return ResponseEntity.ok(
            reservationService.updateReservation(id, updatedReservationDTO)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a reservation",
        description = "Deletes the reservation identified by the provided ID"
    )
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Search reservations by client name",
        description = "Fetches all reservations for a specific client based on the client name"
    )
    @GetMapping("/search")
    public List<Reservation> searchByClientName(
        @RequestParam String clientName
    ) {
        return reservationService.getReservationsByClientName(clientName);
    }
}
