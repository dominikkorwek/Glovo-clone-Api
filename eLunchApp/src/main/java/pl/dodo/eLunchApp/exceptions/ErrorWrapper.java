package pl.dodo.eLunchApp.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorWrapper(
        String errorMessage,
        HttpStatus expectedStatus,
        String uri,
        HttpStatus occurredStatus) {}
