package com.reservasapi.dto.requests;

import com.reservasapi.dto.PassengerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PassengerRequest {

    private Long reservationId;
    private PassengerDTO passenger;
}
