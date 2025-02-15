package com.reservasapi.controller;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.requests.PassengerRequest;
import com.reservasapi.dto.responses.PassengersResponse;
import com.reservasapi.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passengers")
public class PassengerController implements IPassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{reservationId}")
    public ResponseEntity<PassengersResponse> getPassengers(
        @PathVariable Long reservationId
    ) {
        return ResponseEntity.ok(passengerService.getPassengers(reservationId));
    }

    @PostMapping
    public ResponseEntity<PassengerDTO> addPassenger(
        @RequestBody PassengerRequest request
    ) {
        return ResponseEntity.ok(passengerService.addPassenger(request));
    }

    @PutMapping
    public ResponseEntity<PassengerDTO> updatePassenger(
        PassengerRequest request
    ) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePassenger(PassengerRequest request) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
