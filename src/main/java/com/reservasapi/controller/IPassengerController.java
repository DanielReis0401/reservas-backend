package com.reservasapi.controller;

import com.reservasapi.dto.PassengerDTO;
import com.reservasapi.dto.requests.AddPassengerRequest;
import com.reservasapi.dto.requests.DeletePassengerRequest;
import com.reservasapi.dto.requests.UpdatePassengerRequest;
import com.reservasapi.model.passenger.Passenger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

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
            schema = @Schema(implementation = ResponseEntity.class)
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
    ResponseEntity<List<PassengerDTO>> getPassengersByReservation(
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
            schema = @Schema(implementation = ResponseEntity.class)
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
    ResponseEntity<Passenger> addPassenger(
        @NotNull @Valid AddPassengerRequest request
    );

    @Operation(
        summary = "Update a passenger",
        description = "Updates passenger details associated with a reservation"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = @Content(
            schema = @Schema(implementation = ResponseEntity.class)
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
    ResponseEntity<Passenger> updatePassenger(
        @NotNull @Valid UpdatePassengerRequest request
    );

    @Operation(
        summary = "Delete a passenger",
        description = "Removes a passenger from a reservation"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = @Content(
            schema = @Schema(implementation = ResponseEntity.class)
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
    ResponseEntity<Void> deletePassenger(
        @NotNull @Valid DeletePassengerRequest request
    );
}
