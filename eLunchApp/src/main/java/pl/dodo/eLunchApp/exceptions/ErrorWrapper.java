package pl.dodo.eLunchApp.exceptions;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.http.HttpStatusCode;

@Builder
public record ErrorWrapper(
        @NotNull String errorMessage,
        @NotNull HttpStatusCode expectedStatus,
        @NotNull String uri,
        @NotNull HttpStatusCode occurredStatus) {}
