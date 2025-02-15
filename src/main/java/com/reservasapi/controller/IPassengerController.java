package com.reservasapi.controller;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.requests.PassengerRequest;
import com.reservasapi.dto.responses.ErrorResponse;
import com.reservasapi.dto.responses.PassengersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Passengers", description = "Operations related to Passengers")
public interface IPassengerController {
    @Operation(
        summary = "Get passengers by Reservation ID",
        description = "Returns a list of passengers associated with a specific reservation"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = @Content(
            schema = @Schema(implementation = PassengersResponse.class)
        )
    )
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Bad request",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    ResponseEntity<PassengersResponse> getPassengers(
        @NotNull @Valid Long reservationId
    );

    @Operation(
        summary = "Add a new passenger to a reservation",
        description = "Creates a new passenger and associates them with a reservation"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = @Content(
            schema = @Schema(implementation = PassengerDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Bad request",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    ResponseEntity<PassengerDTO> addPassenger(
        @RequestBody PassengerDTO passengerDTO,
        @PathVariable Long reservationId
    );

    @Operation(
        summary = "Update a passenger",
        description = "Updates passenger details associated with a reservation"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = @Content(
            schema = @Schema(implementation = PassengerDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Bad request",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    ResponseEntity<PassengerDTO> updatePassenger(
        @RequestBody PassengerDTO passengerDTO
    );

    @Operation(
        summary = "Delete a passenger",
        description = "Removes a passenger from a reservation"
    )
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Bad request",
        content = @Content(
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    ResponseEntity<Void> deletePassenger(
        @RequestParam Long reservationId,
        @RequestParam Long passengerId
    );
}
