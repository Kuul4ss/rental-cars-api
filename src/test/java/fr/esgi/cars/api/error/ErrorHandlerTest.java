package fr.esgi.cars.api.error;

import fr.esgi.cars.exception.NotFoundRentalCarException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ErrorHandlerTest {

    @Test
    void shouldHandleNotFoundRentalCarException() {
        String message = "Test";

        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleNotFoundRentalCarException(new NotFoundRentalCarException(message));

        Assertions.assertEquals(errorDto.getMessage(), message);
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        String message = "La requête envoyée est invalide";

        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleMethodArgumentNotValidException();

        Assertions.assertEquals(errorDto.getMessage(), message);
    }

}
