package fr.esgi.cars.mapper;

import fr.esgi.cars.domain.RentalCarEntity;
import fr.esgi.cars.dto.request.RentalCarRequestDto;
import fr.esgi.cars.dto.response.RentalCarResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalCarDtoMapper {

    public List<RentalCarResponseDto> mapToDtoList(List<RentalCarEntity> rentalCars) {
        return rentalCars.stream()
                .map(this::mapToDto)
                .toList();
    }

    public RentalCarResponseDto mapToDto(RentalCarEntity rentalCar) {
        return new RentalCarResponseDto(
                rentalCar.getBrand(),
                rentalCar.getModel(),
                rentalCar.getRentAmount(),
                rentalCar.getSecurityDepositAmount(),
                rentalCar.getNumberOfSeats(),
                rentalCar.getNumberOfDoors(),
                rentalCar.getHasAirConditioning());
    }

    public RentalCarEntity mapToEntity(RentalCarRequestDto rentalCarRequestDto) {
        return new RentalCarEntity(
                rentalCarRequestDto.brand(),
                rentalCarRequestDto.model(),
                rentalCarRequestDto.rentAmount(),
                rentalCarRequestDto.securityDepositAmount(),
                rentalCarRequestDto.numberOfSeats(),
                rentalCarRequestDto.numberOfDoors(),
                rentalCarRequestDto.hasAirConditioning());
    }

}
