package fr.esgi.cars.api;

import fr.esgi.cars.domain.RentalCarEntity;
import fr.esgi.cars.dto.request.RentalCarRequestDto;
import fr.esgi.cars.dto.response.RentalCarResponseDto;
import fr.esgi.cars.mapper.RentalCarDtoMapper;
import fr.esgi.cars.repository.RentalCarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static fr.esgi.cars.samples.RentalCarDtoSample.*;
import static fr.esgi.cars.samples.RentalCarEntitySample.oneRentalCarEntity;
import static fr.esgi.cars.samples.RentalCarEntitySample.rentalCarEntities;
import static fr.esgi.cars.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalCarResource.class)
class RentalCarResourceTest {

    @Value("classpath:/json/rentalCars.json")
    private Resource rentalCars;

    @Value("classpath:/json/rentalCar.json")
    private Resource rentalCar;

    @Value("classpath:/json/rentalCarRequest.json")
    private Resource rentalCarRequest;

    @Value("classpath:/json/invalidRentalCarRequest.json")
    private Resource invalidRentalCarRequest;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalCarRepository rentalCarRepository;

    @MockBean
    private RentalCarDtoMapper rentalCarDtoMapper;

    @Test
    void shouldGetRentalCars() throws Exception {
        List<RentalCarEntity> rentalCarEntities = rentalCarEntities();
        List<RentalCarResponseDto> rentalCarResponseList = rentalCarResponseList();

        when(rentalCarRepository.findAll()).thenReturn(rentalCarEntities);
        when(rentalCarDtoMapper.mapToDtoList(rentalCarEntities)).thenReturn(rentalCarResponseList);

        mockMvc.perform(get("/rent-cars-api/rental-cars"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalCars)));

        verify(rentalCarRepository).findAll();
        verify(rentalCarDtoMapper).mapToDtoList(rentalCarEntities);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void shouldGetRentalCarById() throws Exception {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();

        String id = "1";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(Optional.of(rentalCarEntity));
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);

        mockMvc.perform(get("/rent-cars-api/rental-cars/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalCar)));

        verify(rentalCarRepository).findById(Integer.valueOf(id));
        verify(rentalCarDtoMapper).mapToDto(rentalCarEntity);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarDtoMapper);
    }

    @Test
    void givenNoExistentRentalCarId_shouldThrowNotFoundRentalCarException() throws Exception {
        String id = "0";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(Optional.empty());

        System.out.println(mockMvc.perform(get("/rent-cars-api/rental-cars/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"message\":\"La voiture " + id + " est introuvable\"}"))
                .andReturn());

        verify(rentalCarRepository).findById(Integer.valueOf(id));
        verifyNoInteractions(rentalCarDtoMapper);
        verifyNoMoreInteractions(rentalCarRepository);
    }

    @Test
    void shouldCreateRentalCar() throws Exception {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.save(rentalCarEntity)).thenReturn(rentalCarEntity);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);

        mockMvc.perform(post("/rent-cars-api/rental-cars")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(readResource(rentalCar)));

        verify(rentalCarDtoMapper).mapToEntity(rentalCarRequestDto);
        verify(rentalCarRepository).save(rentalCarEntity);
        verify(rentalCarDtoMapper).mapToDto(rentalCarEntity);
        verifyNoMoreInteractions(rentalCarDtoMapper, rentalCarRepository);
    }

    @Test
    void shouldUpdateIfExistsRentalCar() throws Exception {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.save(rentalCarEntity)).thenReturn(rentalCarEntity);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);

        mockMvc.perform(put("/rent-cars-api/rental-cars/{id}", 1)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(readResource(rentalCar)));

        verify(rentalCarDtoMapper).mapToEntity(rentalCarRequestDto);
        verify(rentalCarRepository).save(rentalCarEntity);
        verify(rentalCarDtoMapper).mapToDto(rentalCarEntity);
    }

    @Test
    void shouldSaveIfNotExistsRentalCar() throws Exception {
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        when(rentalCarDtoMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.save(rentalCarEntity)).thenReturn(rentalCarEntity);
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);

        mockMvc.perform(put("/rent-cars-api/rental-cars/" + 2)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(readResource(rentalCar)));

        verify(rentalCarDtoMapper).mapToEntity(rentalCarRequestDto);
        verify(rentalCarRepository).save(rentalCarEntity);
        verify(rentalCarDtoMapper).mapToDto(rentalCarEntity);
    }

    @Test
    void shouldUpdateRentalCar() throws Exception {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();

        String id = "1";

        when(rentalCarRepository.findById(Integer.valueOf(id))).thenReturn(Optional.of(rentalCarEntity));
        when(rentalCarDtoMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponseDto);

        mockMvc.perform(patch("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void shouldThrowRentalCar() throws Exception {
        String id = "2";

        mockMvc.perform(patch("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"message\": \"La voiture 2 est introuvable\"}"));

    }

    @Test
    void shouldDeleteRentalCar() throws Exception {

        String id = "1";

        mockMvc.perform(delete("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isNoContent());

 }

    @Test
    void shouldDeleteRentalCarButNotFound() throws Exception {
        String id = "2";

        mockMvc.perform(delete("/rent-cars-api/rental-cars/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isNoContent());

    }

}
