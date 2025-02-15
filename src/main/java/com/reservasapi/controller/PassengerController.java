package com.reservasapi.controller;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.requests.AddPassengerRequest;
import com.reservasapi.dto.requests.DeletePassengerRequest;
import com.reservasapi.dto.requests.UpdatePassengerRequest;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.service.PassengerService;
import java.util.List;
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

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<PassengerDTO>> getPassengersByReservation(
        @PathVariable Long reservationId
    ) {
        return ResponseEntity.ok(
            passengerService.getPassengersByReservation(reservationId)
        );
    }

    @PostMapping
    public ResponseEntity<Passenger> addPassenger(AddPassengerRequest request) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PutMapping
    public ResponseEntity<Passenger> updatePassenger(
        UpdatePassengerRequest request
    ) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePassenger(
        DeletePassengerRequest request
    ) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
