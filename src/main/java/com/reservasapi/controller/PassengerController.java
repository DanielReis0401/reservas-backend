package com.reservasapi.controller;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.responses.PassengersResponse;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{reservationId}")
    public ResponseEntity<PassengersResponse> getPassengers(
        @PathVariable Long reservationId
    ) {
        return ResponseEntity.ok(passengerService.getPassengers(reservationId));
    }

    @PostMapping("/{reservationId}")
    public ResponseEntity<PassengerDTO> addPassenger(
        @RequestBody PassengerDTO passengerDTO,
        @PathVariable Long reservationId
    ) {
        return ResponseEntity.ok(
            passengerService.addPassenger(reservationId, passengerDTO)
        );
    }

    @PutMapping
    public ResponseEntity<Passenger> updatePassenger(
        @RequestBody PassengerDTO passengerDTO,
        @RequestParam Long reservationId,
        @RequestParam Long passengerId
    ) {
        return ResponseEntity.ok(
            passengerService.updatePassenger(
                reservationId,
                passengerId,
                passengerDTO
            )
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePassenger(
        @RequestParam Long reservationId,
        @RequestParam Long passengerId
    ) {
        passengerService.deletePassenger(reservationId, passengerId);
        return ResponseEntity.noContent().build();
    }
}
