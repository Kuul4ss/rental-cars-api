CREATE TABLE property_type(
                              id INT PRIMARY KEY NOT NULL,
                              designation VARCHAR(5) NOT NULL
);

CREATE TABLE energy_classification(
                                      id INT PRIMARY KEY NOT NULL,
                                      designation CHAR(1) NOT NULL
);

CREATE TABLE rental_property(
                                id INT primary key NOT NULL ,
                                description VARCHAR(200) NOT NULL,
                                town VARCHAR(100) NOT NULL,
                                address VARCHAR(200) NOT NULL,
                                property_type_id INT NOT NULL,
                                rent_amount DOUBLE NOT NULL,
                                security_deposit_amount DOUBLE NOT NULL,
                                area DOUBLE NOT NULL,
                                number_of_bedrooms INT,
                                floors_number INT,
                                construction_year CHAR(4),
                                energy_classification_id INT,
                                has_elevator BIT(1),
                                has_intercom BIT(1),
                                has_balcony BIT(1),
                                has_parking_space BIT(1)
);

CREATE TABLE rental_car(
                           id INT PRIMARY KEY NOT NULL,
                           brand VARCHAR(100) NOT NULL,
                           model VARCHAR(100) NOT NULL,
                           rent_amount DOUBLE NOT NULL,
                           security_deposit_amount DOUBLE NOT NULL,
                           number_of_seats INT,
                           number_of_doors INT,
                           has_air_conditionin