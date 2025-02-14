package com.reservasapi.controller;

import com.reservasapi.model.service.ReservationService;
import com.reservasapi.service.ReservationServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Tag(
  name = "Reservation Services",
  description = "Endpoints for managing services related to reservations"
)
public class ReservationServiceController {

  @Autowired
  private ReservationServiceService reservationServiceService;

  @GetMapping("/reservation/{reservationId}")
  @Operation(
    summary = "Get services by reservation",
    description = "Fetches all services associated with a specific reservation"
  )
  public List<ReservationService> getServicosByReservation(
    @PathVariable Long reservationId
  ) {
    return reservationServiceService.getServicosByReservation(reservationId);
  }

  @PostMapping("/{reservationId}")
  @Operation(
    summary = "Add a service to a reservation",
    description = "Adds a new service to the specified reservation"
  )
  public ResponseEntity<ReservationService> addServico(
    @PathVariable Long reservationId,
    @RequestBody ReservationService reservationService
  ) {
    return ResponseEntity.ok(
      reservationServiceService.addServico(reservationId, reservationService)
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
    @RequestBody ReservationService updatedService
  ) {
    return ResponseEntity.ok(
      reservationServiceService.updateServico(
        reservationId,
        servicoId,
        updatedService
      )
    );
  }

  @DeleteMapping("/{reservationId}/{serviceId}")
  @Operation(
    summary = "Delete a service from a reservation",
    description = "Removes a service from the specified reservation"
  )
  public ResponseEntity<Void> deleteServico(
    @PathVariable Long reservationId,
    @PathVariable Long servicoId
  ) {
    reservationServiceService.deleteServico(reservationId, servicoId);
    return ResponseEntity.noContent().build();
  }
}
