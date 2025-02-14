package com.reservasapi.model.mapper;

import com.reservasapi.DTO.PassengerDTO;
import com.reservasapi.DTO.ReservationDTO;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.reservation.Reservation;
import java.util.ArrayList;
import java.util.List;

public class PassengerMapper {

  public static PassengerDTO toPassengerDTO(Passenger passenger) {
    List<ReservationDTO> reservationDTOS = new ArrayList<>();
    for (Reservation reservation : passenger.getReservations()) {
      reservationDTOS.add(ReservationMapper.toReservationDTO(reservation));
    }

    return PassengerDTO.builder()
      .name(passenger.getName())
      .age(passenger.getAge())
      .type(passenger.getType())
      .reservations(reservationDTOS)
      .build();
  }

  public static Passenger toPassenger(PassengerDTO passengerDTO) {
    List<Reservation> reservations = new ArrayList<>();
    for (ReservationDTO reservationDTO : passengerDTO.getReservations()) {
      reservations.add(ReservationMapper.toReservation(reservationDTO));
    }

    return Passenger.builder()
      .name(passengerDTO.getName())
      .age(passengerDTO.getAge())
      .type(passengerDTO.getType())
      .reservations(reservations)
      .build();
  }
}
