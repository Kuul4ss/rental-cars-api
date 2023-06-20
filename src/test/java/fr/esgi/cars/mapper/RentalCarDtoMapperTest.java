package fr.esgi.cars.mapper;

import fr.esgi.cars.domain.RentalCarEntity;
import fr.esgi.cars.dto.request.RentalCarRequestDto;
import fr.esgi.cars.dto.response.RentalCarResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.esgi.cars.samples.RentalCarDtoSample.oneRentalCarResponse;
import static fr.esgi.cars.samples.RentalCarEntitySample.oneRentalCarEntity;
import static fr.esgi.cars.samples.RentalCarEntitySample.rentalCarEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RentalCarDtoMapperTest {

    @Test
    void shouldMapToDto() {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto expectedRentalCarResponseDto = oneRentalCarResponse();

        RentalCarDtoMapper rentalCarDtoMapper = new RentalCarDtoMapper();

        RentalCarResponseDto rentalCarResponseDto = rentalCarDtoMapper.mapToDto(rentalCarEntity);

        assertThat(rentalCarResponseDto).isEqualTo(expectedRentalCarResponseDto);
    }

    @Test
    void shouldMapToDtoList() {
        List<RentalCarEntity> rentalCarEntities = rentalCarEntities();

        RentalCarDtoMapper rentalCarDtoMapper = new RentalCarDtoMapper();

        List<RentalCarResponseDto> rentalCarResponseList = rentalCarDtoMapper.mapToDtoList(rentalCarEntities);

        assertThat(rentalCarResponseList).isNotNull()
                .hasSize(1)
                .extracting("brand", "model","rentAmount", "securityDepositAmount", "numberOfSeats","numberOfDoors","hasAirConditioning")
                .containsExactlyInAnyOrder(
                        tuple("BMW",
                                "Serie 1",
                                790.50,
                                1550.90,
                                5,
                                5,
                                true)
                );
    }

    @Test
    void shouldMapToEntity() {
        RentalCarRequestDto dto = new RentalCarRequestDto(
                "BMW",
                "Serie 1",
                790.50,
                1550.90,
                5,
                5,
                true
        );

        RentalCarDtoMapper rentalCarDtoMapper = new RentalCarDtoMapper();
        RentalCarEntity entity = rentalCarDtoMapper.mapToEntity(dto);
        assertThat(entity).hasFieldOrPropertyWithValue("securityDepositAmount", 1550.90d);
    }

}
