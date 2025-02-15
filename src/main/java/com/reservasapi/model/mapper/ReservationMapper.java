package com.reservasapi.model.mapper;

import com.reservasapi.dto.ReservationDTO;
import com.reservasapi.model.reservation.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservationMapper extends BaseMapper {
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "creationDate", source = "creationDate")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(
        target = "services",
        source = "services",
        qualifiedByName = RES_SERVICE_DTO
    )
    @Mapping(
        target = "passengers",
        source = "passengers",
        qualifiedByName = PASSENGER_DTO
    )
    ReservationDTO toReservationDTO(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "creationDate", source = "creationDate")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(
        target = "services",
        source = "services",
        qualifiedByName = RES_SERVICE
    )
    @Mapping(
        target = "passengers",
        source = "passengers",
        qualifiedByName = PASSENGER
    )
    Reservation toReservation(ReservationDTO reservationDTO);
}
