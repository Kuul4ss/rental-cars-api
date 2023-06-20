package fr.esgi.cars.dto.response;

import lombok.Builder;

@Builder
public record RentalCarResponseDto(String brand,
                                   String model,
                                   double rentAmount,
                                   double securityDepositAmount,
                                   int numberOfSeats,
                                   int numberOfDoors,
                                   boolean hasAirConditioning) {
}
