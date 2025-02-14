package com.reservasapi.dto;

import com.reservasapi.model.passenger.PassengerType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerDTO {

    private Long id;
    private String name;
    private int age;
    private PassengerType type;
    private List<ReservationDTO> reservations;
}
