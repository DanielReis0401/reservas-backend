package com.reservasapi.dto.responses;

import com.reservasapi.dto.PassengerDTO;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Builder
public class PassengersResponse {

    private List<PassengerDTO> passengers;
}
