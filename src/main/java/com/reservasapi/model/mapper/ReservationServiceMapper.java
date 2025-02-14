package com.reservasapi.model.mapper;

import com.reservasapi.DTO.ReservationServiceDTO;
import com.reservasapi.model.servico.ReservationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservationServiceMapper extends BaseMapper {
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  ReservationServiceDTO toReservationServiceDTO(
    ReservationService reservationService
  );

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  ReservationService toReservationService(
    ReservationServiceDTO reservationService
  );
}
