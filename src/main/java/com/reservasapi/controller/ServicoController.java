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
import com.reservasapi.service.ServicoService;

@RestController
@RequestMapping
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/reservation/{reservationId}")
    public List<ReservationService> getServicosByReservation(@PathVariable Long reservationId){
        return servicoService.getServicosByReservation(reservationId);
    }

    @PostMapping("/{reservationId}")
    public ResponseEntity<ReservationService> addServico(@PathVariable Long reservationId, @RequestBody ReservationService reservationService){
        return ResponseEntity.ok(servicoService.addServico(reservationId, reservationService));
    }

    @PutMapping("/{reservationId}/{servicoId}")
    public ResponseEntity<ReservationService> updateServico(@PathVariable Long reservationId, @PathVariable Long servicoId, @RequestBody ReservationService updatedService){
        return ResponseEntity.ok(servicoService.updateServico(reservationId, servicoId, updatedService));
    }

    @DeleteMapping("/{reservationId}/{serviceId}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long reservationId, @PathVariable Long servicoId){
        servicoService.deleteServico(reservationId, servicoId);
        return ResponseEntity.noContent().build();
    }
}
