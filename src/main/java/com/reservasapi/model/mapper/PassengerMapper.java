package com.reservasapi.model.mapper;

import com.reservasapi.DTO.PassengerDTO;
import com.reservasapi.model.passenger.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PassengerMapper extends BaseMapper {
  @Mapping(target = "name", source = "name")
  @Mapping(target = "age", source = "age")
  @Mapping(target = "type", source = "type")
  @Mapping(
    target = "reservations",
    source = "reservations",
    qualifiedByName = RESERVATION_DTO
  )
  PassengerDTO toPassengerDTO(Passenger passenger);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "name")
  @Mapping(target = "age", source = "age")
  @Mapping(target = "type", source = "type")
  @Mapping(
    target = "reservations",
    source = "reservations",
    qualifiedByName = RESERVATION
  )
  Passenger toPassenger(PassengerDTO passengerDTO);
}
