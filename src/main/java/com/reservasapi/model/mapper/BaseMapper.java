package com.reservasapi.model.mapper;

import com.reservasapi.DTO.PassengerDTO;
import com.reservasapi.DTO.ReservationDTO;
import com.reservasapi.DTO.ReservationServiceDTO;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.model.servico.ReservationService;
import java.util.List;
import org.mapstruct.Named;

public interface BaseMapper {
  String RESERVATION_DTO = "reservationDto";
  String RESERVATION = "reservation";
  String PASSENGER_DTO = "passengerDto";
  String PASSENGER = "passenger";
  String RES_SERVICE_DTO = "resServiceDto";
  String RES_SERVICE = "resService";

  @Named(PASSENGER_DTO)
  default List<PassengerDTO> toPassengerDTO(List<Passenger> passengers) {
    return passengers
      .stream()
      .map(passengerDto ->
        PassengerDTO.builder()
          .name(passengerDto.getName())
          .age(passengerDto.getAge())
          .type(passengerDto.getType())
          .build()
      )
      .toList();
  }

  @Named(PASSENGER)
  default List<Passenger> toPassenger(List<PassengerDTO> passengerDTOs) {
    return passengerDTOs
      .stream()
      .map(passengerDto ->
        Passenger.builder()
          .name(passengerDto.getName())
          .age(passengerDto.getAge())
          .type(passengerDto.getType())
          .build()
      )
      .toList();
  }

  @Named(RES_SERVICE_DTO)
  default List<ReservationServiceDTO> toReservationServiceDTO(
    List<ReservationService> reservationServices
  ) {
    return reservationServices
      .stream()
      .map(resServiceDto ->
        ReservationServiceDTO.builder()
          .name(resServiceDto.getName())
          .description(resServiceDto.getDescription())
          .price(resServiceDto.getPrice())
          .build()
      )
      .toList();
  }

  @Named(RES_SERVICE)
  default List<ReservationService> toReservationService(
    List<ReservationServiceDTO> reservationServiceDTOs
  ) {
    return reservationServiceDTOs
      .stream()
      .map(resServiceDto ->
        ReservationService.builder()
          .name(resServiceDto.getName())
          .description(resServiceDto.getDescription())
          .price(resServiceDto.getPrice())
          .build()
      )
      .toList();
  }

  @Named(RESERVATION_DTO)
  default List<ReservationDTO> toReservationDTO(
    List<Reservation> reservations
  ) {
    return reservations
      .stream()
      .map(reservationDto ->
        ReservationDTO.builder()
          .customerName(reservationDto.getCustomerName())
          .creationDate(reservationDto.getCreationDate())
          .services(toReservationServiceDTO(reservationDto.getServices()))
          .passengers(toPassengerDTO(reservationDto.getPassengers()))
          .build()
      )
      .toList();
  }

  @Named(RESERVATION)
  default List<Reservation> toReservation(
    List<ReservationDTO> reservationDTOs
  ) {
    return reservationDTOs
      .stream()
      .map(reservationDto ->
        Reservation.builder()
          .customerName(reservationDto.getCustomerName())
          .creationDate(reservationDto.getCreationDate())
          .services(toReservationService(reservationDto.getServices()))
          .passengers(toPassenger(reservationDto.getPassengers()))
          .build()
      )
      .toList();
  }
}
