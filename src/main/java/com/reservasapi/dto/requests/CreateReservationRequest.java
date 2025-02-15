package com.reservasapi.dto.requests;

import com.reservasapi.dto.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationRequest {

    private ReservationDTO reservationDTO;
}
