package com.reservasapi.controller;

import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.service.ReservationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping
  public List<Reservation> getAllReservations() {
    return reservationService.getAllReservations();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Reservation>> getReservationById(
    @PathVariable Long id
  ) {
    return ResponseEntity.ok(reservationService.getReservationById(id));
  }

  @PostMapping
  public ResponseEntity<Reservation> createReservation(
    @RequestBody Reservation reservation
  ) {
    return ResponseEntity.ok(reservationService.createReservation(reservation));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Reservation> updateReservation(
    @PathVariable Long id,
    @RequestBody Reservation updatedReservation
  ) {
    return ResponseEntity.ok(
      reservationService.updateReservation(id, updatedReservation)
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
    reservationService.deleteReservation(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public List<Reservation> searchByClientName(@RequestParam String clientName) {
    return reservationService.getReservationsByClientName(clientName);
  }
}
