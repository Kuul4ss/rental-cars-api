package fr.esgi.cars.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundRentalCarException extends RuntimeException {

    public String message;
    public NotFoundRentalCarException(String message) {
        super(message);
        this.message = message;
    }
}
