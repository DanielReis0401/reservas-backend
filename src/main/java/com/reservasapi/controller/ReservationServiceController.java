package com.reservasapi.controller;

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

import com.reservasapi.model.servico.ReservationService;
import com.reservasapi.service.ReservationServiceService;

@RestController
@RequestMapping
public class ReservationServiceController {

    @Autowired
    private ReservationServiceService reservationServiceService;

    @GetMapping("/reservation/{reservationId}")
    public List<ReservationService> getServicosByReservation(@PathVariable Long reservationId){
        return reservationServiceService.getServicosByReservation(reservationId);
    }

    @PostMapping("/{reservationId}")
    public ResponseEntity<ReservationService> addServico(@PathVariable Long reservationId, @RequestBody ReservationService reservationService){
        return ResponseEntity.ok(reservationServiceService.addServico(reservationId, reservationService));
    }

    @PutMapping("/{reservationId}/{servicoId}")
    public ResponseEntity<ReservationService> updateServico(@PathVariable Long reservationId, @PathVariable Long servicoId, @RequestBody ReservationService updatedService){
        return ResponseEntity.ok(reservationServiceService.updateServico(reservationId, servicoId, updatedService));
    }

    @DeleteMapping("/{reservationId}/{serviceId}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long reservationId, @PathVariable Long servicoId){
        reservationServiceService.deleteServico(reservationId, servicoId);
        return ResponseEntity.noContent().build();
    }
}
