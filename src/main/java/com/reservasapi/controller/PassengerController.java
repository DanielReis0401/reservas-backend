package com.reservasapi.controller;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.requests.PassengerRequest;
import com.reservasapi.dto.responses.PassengersResponse;
import com.reservasapi.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passengers")
public class PassengerController implements IPassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{reservationId}")
    public ResponseEntity<PassengersResponse> getPassengers(
        @PathVariable Long reservationId
    ) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PostMapping
    public ResponseEntity<PassengerDTO> addPassenger(PassengerRequest request) {
        throw new UnsupportedOperationException("Not implemented");
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
