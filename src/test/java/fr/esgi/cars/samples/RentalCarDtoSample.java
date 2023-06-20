package fr.esgi.cars.samples;

import fr.esgi.cars.dto.request.RentalCarRequestDto;
import fr.esgi.cars.dto.response.RentalCarResponseDto;

import java.util.List;

public class RentalCarDtoSample {

    public static List<RentalCarResponseDto> rentalCarResponseList() {
        RentalCarResponseDto rentalCar = oneRentalCarResponse();

        return List.of(rentalCar);
    }

    public static RentalCarResponseDto oneRentalCarResponse() {
        return RentalCarResponseDto.builder()
                .brand("BMW")
                .model("Serie 1")
                .rentAmount(790.50)
                .securityDepositAmount(1550.9)
                .numberOfSeats(5)
                .numberOfDoors(5)
                .hasAirConditioning(true)
                .build();
    }

    public static RentalCarRequestDto oneRentalCarRequest() {
        return RentalCarRequestDto.builder()
                .brand("BMW")
                .model("Serie 1")
                .rentAmount(790.50)
                .securityDepositAmount(1550.9)
                .numberOfSeats(5)
                .numberOfDoors(5)
                .hasAirConditioning(true)
                .build();
    }

}
