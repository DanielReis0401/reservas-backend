package com.reservasapi.model.mapper;

import com.reservasapi.DTO.ReservationServiceDTO;
import com.reservasapi.model.servico.ReservationService;

public class ReservationServiceMapper {

  public static ReservationServiceDTO toReservationServiceDTO(
    ReservationService reservationService
  ) {
    return ReservationServiceDTO.builder()
      .name(reservationService.getName())
      .description(reservationService.getDescription())
      .price(reservationService.getPrice())
      .build();
  }

  public static ReservationService toReservationService(
    ReservationServiceDTO reservationServiceDTO
  ) {
    return ReservationService.builder()
      .name(reservationServiceDTO.getName())
      .description(reservationServiceDTO.getDescription())
      .price(reservationServiceDTO.getPrice())
      .build();
  }
}
