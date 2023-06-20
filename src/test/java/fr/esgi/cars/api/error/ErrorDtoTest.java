package fr.esgi.cars.api.error;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ErrorDtoTest {
    @Test
    public void testGetMessage() {
        String expectedMessage = "Test message";
        ErrorDto errorDto = new ErrorDto(expectedMessage);
        String actualMessage = errorDto.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
