package com.reservasapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private String customerName;
    private LocalDate creationDate;
    private List<PassengerDTO> passengers;
    private List<ReservationServiceDTO> services;
}
