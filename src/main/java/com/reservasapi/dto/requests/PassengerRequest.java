package com.reservasapi.dto.requests;

import com.reservasapi.dto.PassengerDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor
@SuperBuilder
public class PassengerRequest {

    private Long reservationId;
    private PassengerDTO passenger;
}
