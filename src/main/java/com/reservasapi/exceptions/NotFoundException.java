package com.reservasapi.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    private final String message;
}
