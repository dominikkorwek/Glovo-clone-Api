package pl.dodo.eLunchApp.exceptions;

import jakarta.validation.constraints.NotNull;
import pl.dodo.eLunchApp.model.OrderStatus;

import java.util.UUID;

public sealed class eLunchError extends RuntimeException permits
        eLunchError.ObjectNotFound, eLunchError.WrongOrderStatus, eLunchError.InvalidUuid,
        eLunchError.InvalidValidation {

    public eLunchError(String message) {
        super(message);
    }

    public static final class ObjectNotFound extends eLunchError {
        public ObjectNotFound(Class<?> objectName) {
            super("Object not found in repository: " + objectName.getSimpleName());
        }
    }

    public static final class WrongOrderStatus extends eLunchError {
        public WrongOrderStatus(@NotNull OrderStatus status) {
            super("Wrong order status: " + status);
        }
    }

    public static final class InvalidUuid extends eLunchError {
        public InvalidUuid(UUID expected, UUID got) {
            super("Invalid UUID. Expected: " + expected + ", got: " + got);
        }
    }

    public static final class InvalidValidation extends eLunchError {
        public InvalidValidation(Class<?> objectName) {
            super("Invalid validation for object: " + objectName.getSimpleName());
        }
    }
}
