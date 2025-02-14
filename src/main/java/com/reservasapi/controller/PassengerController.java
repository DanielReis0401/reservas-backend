package com.reservasapi.controller;

import com.reservasapi.DTO.PassengerDTO;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passengers")
@Tag(name = "Passengers", description = "Operations related to Passengers")
public class PassengerController {

  @Autowired
  private PassengerService passengerService;

  @GetMapping("/reservation/{reservationId}")
  @Operation(
    summary = "Get passengers by Reservation ID",
    description = "Returns a list of passengers associated with a specific reservation"
  )
  public List<Passenger> getPassengersByReservation(
    @PathVariable Long reservationId
  ) {
    return passengerService.getPassengersByReservation(reservationId);
  }

  @PostMapping("/{reservationId}")
  @Operation(
          summary = "Add a new passenger to a reservation",
          description = "Creates a new passenger and associates them with a reservation"
  )
  public ResponseEntity<Passenger> addPassenger(
    @PathVariable Long reservationId,
    @RequestBody PassengerDTO passengerDTO
  ) {
    return ResponseEntity.ok(
      passengerService.addPassenger(reservationId, passengerDTO)
    );
  }

  @PutMapping("/{id}")
  @Operation(
          summary = "Update a passenger",
          description = "Updates passenger details associated with a reservation"
  )
  public ResponseEntity<Passenger> updatePassenger(
    @PathVariable Long reservationId,
    @PathVariable Long idPassenger,
    @RequestBody PassengerDTO updatedPassengerDTO
  ) {
    return ResponseEntity.ok(
      passengerService.updatePassenger(
        reservationId,
        idPassenger,
        updatedPassengerDTO
      )
    );
  }

  @DeleteMapping("/{reservationId}/{id}")
  @Operation(
          summary = "Delete a passenger",
          description = "Removes a passenger from a reservation"
  )
  public ResponseEntity<Void> deletePassenger(
    @PathVariable Long reservationId,
    @PathVariable Long idPassenger
  ) {
    passengerService.deletePassenger(idPassenger, idPassenger);
    return ResponseEntity.noContent().build();
  }
}
