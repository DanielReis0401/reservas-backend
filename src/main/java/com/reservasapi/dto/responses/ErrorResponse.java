package com.reservasapi.dto.responses;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Builder
public class ErrorResponse {

    @NotNull
    private String error;
}
