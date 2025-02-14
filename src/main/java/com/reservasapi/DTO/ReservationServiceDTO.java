package com.reservasapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationServiceDTO {

  private String name;
  private String description;
  private double price;
}
