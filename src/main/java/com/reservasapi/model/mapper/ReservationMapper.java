package com.reservasapi.model.mapper;

import com.reservasapi.DTO.PassengerDTO;
import com.reservasapi.DTO.ReservationDTO;
import com.reservasapi.DTO.ReservationServiceDTO;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.model.servico.ReservationService;
import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

  public static ReservationDTO toReservationDTO(Reservation reservation) {
    List<PassengerDTO> passengerDTOs = new ArrayList<>();
    for (Passenger passenger : reservation.getPassengers()) {
      passengerDTOs.add(PassengerMapper.toPassengerDTO(passenger));
    }

    List<ReservationServiceDTO> reservationServiceDTOs = new ArrayList<>();
    for (ReservationService reservationService : reservation.getServices()) {
      reservationServiceDTOs.add(
        ReservationServiceMapper.toReservationServiceDTO(reservationService)
      );
    }
    return ReservationDTO.builder()
      .customerName(reservation.getCustomerName())
      .creationDate(reservation.getCreationDate())
      .passengers(passengerDTOs)
      .services(reservationServiceDTOs)
      .build();
  }

  public static Reservation toReservation(ReservationDTO reservationDTO) {
    List<Passenger> passengers = new ArrayList<>();

    for (PassengerDTO passengerDTO : reservationDTO.getPassengers()) {
      passengers.add(PassengerMapper.toPassenger(passengerDTO));
    }

    List<ReservationService> reservationServices = new ArrayList<>();
    for (ReservationServiceDTO reservationServiceDTO : reservationDTO.getServices()) {
      reservationServices.add(
        ReservationServiceMapper.toReservationService(reservationServiceDTO)
      );
    }

    return Reservation.builder()
      .customerName(reservationDTO.getCustomerName())
      .creationDate(reservationDTO.getCreationDate())
      .passengers(passengers)
      .services(reservationServices)
      .build();
  }
}
