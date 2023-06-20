package fr.esgi.cars.repository;

import fr.esgi.cars.domain.RentalCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalCarRepository extends JpaRepository<RentalCarEntity, Integer> {

}
