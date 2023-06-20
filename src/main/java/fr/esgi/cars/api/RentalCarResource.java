package fr.esgi.cars.api;

import fr.esgi.cars.domain.RentalCarEntity;
import fr.esgi.cars.dto.request.RentalCarRequestDto;
import fr.esgi.cars.dto.response.RentalCarResponseDto;
import fr.esgi.cars.exception.NotFoundRentalCarException;
import fr.esgi.cars.mapper.RentalCarDtoMapper;
import fr.esgi.cars.repository.RentalCarRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/rent-cars-api")
public class RentalCarResource {

    private final RentalCarRepository rentalCarRepository;
    private final RentalCarDtoMapper rentalCarDtoMapper;

    public RentalCarResource(RentalCarRepository rentalCarRepository,
                             RentalCarDtoMapper rentalCarDtoMapper) {
        this.rentalCarRepository = rentalCarRepository;
        this.rentalCarDtoMapper = rentalCarDtoMapper;
    }

    @GetMapping("/rental-cars")
    public List<RentalCarResponseDto> getRentalCars() {
        List<RentalCarEntity> rentalCars = rentalCarRepository.findAll();

        return rentalCarDtoMapper.mapToDtoList(rentalCars);
    }

    @GetMapping("/rental-cars/{id}")
    public RentalCarResponseDto getRentalCarById(@PathVariable Integer id) {
        return rentalCarRepository.findById(id)
                .map(rentalCarDtoMapper::mapToDto)
                .orElseThrow(() -> new NotFoundRentalCarException("La voiture " + id + " est introuvable"));
    }

    @PostMapping("/rental-cars")
    @ResponseStatus(CREATED)
    public RentalCarResponseDto createRentalCar(@Valid @RequestBody RentalCarRequestDto rentalCarRequestDto) {
        RentalCarEntity rentalCarEntity = rentalCarDtoMapper.mapToEntity(rentalCarRequestDto);

        RentalCarEntity savedRentalCar = rentalCarRepository.save(rentalCarEntity);

        return  rentalCarDtoMapper.mapToDto(savedRentalCar);
    }

    @PutMapping("/rental-cars/{id}")
    @ResponseStatus(CREATED)
    public RentalCarResponseDto createOrUpdateRentalCar(@Valid @RequestBody RentalCarRequestDto rentalCarRequestDto, @Valid @PathVariable Integer id) {
        Optional<RentalCarEntity> opEntity = rentalCarRepository.findById(id);
        RentalCarEntity entity = null;
        if(opEntity.isPresent()) {
            entity = opEntity.get();
        } else {
            entity = rentalCarDtoMapper.mapToEntity(rentalCarRequestDto);
            entity.setId(id);
        }
        RentalCarEntity savedRentalCar = rentalCarRepository.save(entity);
        return  rentalCarDtoMapper.mapToDto(savedRentalCar);
    }

    @PatchMapping("/rental-cars/{id}")
    public RentalCarResponseDto updateRentalCar(@Valid @RequestBody RentalCarRequestDto rentalCarRequestDto, @Valid @PathVariable Integer id) {
        Optional<RentalCarEntity> opEntity = rentalCarRepository.findById(id);
        if(opEntity.isPresent()) {
            RentalCarEntity savedRentalCar = rentalCarRepository.save(opEntity.get());
            return  rentalCarDtoMapper.mapToDto(savedRentalCar);
        } else {
            throw new NotFoundRentalCarException("La voiture " + id + " est introuvable");
        }

    }

    @DeleteMapping("/rental-cars/{id}")
    @ResponseStatus(NO_CONTENT)
    public void createRentalCar(@Valid @RequestBody RentalCarRequestDto rentalCarRequestDto, @Valid @PathVariable Integer id) {

        Optional<RentalCarEntity> res = rentalCarRepository.findById(id);
        if(res.isPresent()) {
            rentalCarRepository.delete(res.get());
        } else {
            return;
        }
    }

}
