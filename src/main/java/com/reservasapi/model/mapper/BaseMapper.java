package com.reservasapi.model.mapper;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.ReservationDTO;
import com.reservasapi.dto.ReservationServiceDTO;
import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.reservation.Reservation;
import com.reservasapi.model.service.ReservationService;
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
            .map(passenger ->
                PassengerDTO.builder()
                    .id(passenger.getId())
                    .name(passenger.getName())
                    .age(passenger.getAge())
                    .type(passenger.getType())
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
                    .id(passengerDto.getId())
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
            .map(resService ->
                ReservationServiceDTO.builder()
                    .name(resService.getName())
                    .description(resService.getDescription())
                    .price(resService.getPrice())
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
            .map(reservation ->
                ReservationDTO.builder()
                    .customerName(reservation.getCustomerName())
                    .creationDate(reservation.getCreationDate())
                    .services(
                        toReservationServiceDTO(reservation.getServices())
                    )
                    .passengers(toPassengerDTO(reservation.getPassengers()))
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
                    .services(
                        toReservationService(reservationDto.getServices())
                    )
                    .passengers(toPassenger(reservationDto.getPassengers()))
                    .build()
            )
            .toList();
    }
}
