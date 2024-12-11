package pl.dodo.eLunchApp.exceptions;

import jakarta.validation.constraints.NotNull;
import pl.dodo.eLunchApp.model.OrderStatus;

import java.util.UUID;

public sealed interface eLunchError extends Error {
    // remember to add newly error to connected to its controller
    record ObjectNotFound(Class<?> objectName) implements eLunchError {}
    record WrongOrderStatus(@NotNull OrderStatus status) implements eLunchError {}
    record InvalidUuid(UUID expected, UUID got) implements eLunchError {}
}
