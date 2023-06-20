package fr.esgi.cars.samples;

import fr.esgi.cars.domain.RentalCarEntity;

import java.util.List;

public class RentalCarEntitySample {

    public static List<RentalCarEntity> rentalCarEntities() {
        RentalCarEntity rentalCar = oneRentalCarEntity();

        return List.of(rentalCar);
    }

    public static RentalCarEntity oneRentalCarEntity() {
        return new RentalCarEntity(
                1,
                "BMW",
                "Serie 1",
                790.50,
                1550.90,
                5,
                5,
                true);
    }

}
