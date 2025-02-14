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
  public List<Passenger> getPassengersByReservation(
    @PathVariable Long reservationId
  ) {
    return passengerService.getPassengersByReservation(reservationId);
  }

  @PostMapping("/{reservationId}")
  public ResponseEntity<Passenger> addPassenger(
    @PathVariable Long reservationId,
    @RequestBody PassengerDTO passengerDTO
  ) {
    return ResponseEntity.ok(
      passengerService.addPassenger(reservationId, passengerDTO)
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<Passenger> updatePassenger(
    @PathVariable Long reservationId,
    @PathVariable Long idPassenger,
    @RequestBody Passenger updatedPassenger
  ) {
    return ResponseEntity.ok(
      passengerService.updatePassenger(
        reservationId,
        idPassenger,
        updatedPassenger
      )
    );
  }

  @DeleteMapping("/{reservationId}/{id}")
  public ResponseEntity<Void> deletePassenger(
    @PathVariable Long reservationId,
    @PathVariable Long idPassenger
  ) {
    passengerService.deletePassenger(idPassenger, idPassenger);
    return ResponseEntity.noContent().build();
  }
}
