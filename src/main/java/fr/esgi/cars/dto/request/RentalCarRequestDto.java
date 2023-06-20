package fr.esgi.cars.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RentalCarRequestDto(
        @NotNull String brand,
        @NotNull String model,
        @NotNull double rentAmount,
        @NotNull double securityDepositAmount,
        @NotNull int numberOfSeats,
        @NotNull int numberOfDoors,
        @NotNull boolean hasAirConditioning) {

}
