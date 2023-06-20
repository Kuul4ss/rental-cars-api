package fr.esgi.cars.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rental_car")
public class RentalCarEntity {

    public RentalCarEntity() {

    }

    public RentalCarEntity(String brand, String model, double rentAmount, double securityDepositAmount, int numberOfSeats, int numberOfDoors, boolean hasAirConditioning) {
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }

    public RentalCarEntity(Integer id, String brand, String model, double rentAmount, double securityDepositAmount, int numberOfSeats, int numberOfDoors, boolean hasAirConditioning) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Integer id;


    @Column(name = "brand")
    private String brand;


    @Column(name = "model")
    private String model;

    @Column(name = "rent_amount")
    private Double rentAmount;


    @Column(name = "security_deposit_amount")
    private Double securityDepositAmount;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "number_of_doors")
    private Integer numberOfDoors;

    @Column(name = "has_air_conditioning")
    private Boolean hasAirConditioning;
}
