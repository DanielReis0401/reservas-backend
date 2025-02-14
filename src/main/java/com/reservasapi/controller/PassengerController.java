package com.reservasapi.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.service.PassengerService;

@RestController
@RequestMapping("/passengers")
@Tag(name ="Passengers", description = "Operations related to Passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/reservation/{reservationId}")
    @Operation(summary = "Get a list of Passengers of that Reservation ID", description = " Return a list of Passengers of a Reservation")
    public List<Passenger> getPassengersByReservation(@PathVariable Long reservationId) {
        return passengerService.getPassengersByReservation(reservationId);
    }

    @PostMapping("/{reservationId}")
    public ResponseEntity<Passenger> addPassenger(@PathVariable Long reservationId, @RequestBody Passenger passenger){
        return ResponseEntity.ok(passengerService.addPassenger(reservationId, passenger));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long reservationId,@PathVariable Long idPassenger, @RequestBody Passenger updatedPassenger){
        return ResponseEntity.ok(passengerService.updatePassenger(reservationId, idPassenger, updatedPassenger));
    }

    @DeleteMapping("/{reservationId}/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long reservationId, @PathVariable Long idPassenger){
        passengerService.deletePassenger(idPassenger, idPassenger);
        return ResponseEntity.noContent().build();
    }
}
