package com.reservasapi.DTO;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {

  private String customerName;
  private LocalDate creationDate;
  private List<PassengerDTO> passengers;
  private List<ReservationServiceDTO> services;
}
