package com.reservasapi.DTO;

import com.reservasapi.model.passenger.PassengerType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {

  private String name;
  private int age;
  private PassengerType type;
  private List<ReservationDTO> reservations;
}
