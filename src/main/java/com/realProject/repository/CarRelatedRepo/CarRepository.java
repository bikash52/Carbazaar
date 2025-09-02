package com.realProject.repository.CarRelatedRepo;

import com.realProject.entity.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            "SELECT c FROM Car c " +
                    "JOIN c.brand b " +
                    "JOIN c.transmission t " +
                    "JOIN c.fuelType f " +
                    "JOIN c.year y " +
                    "JOIN c.model m " +
                    "WHERE (:details IS NULL OR b.name = :details OR t.type = :details OR f.fuelType = :details OR m.name = :details) " +
                    "AND (:year IS NULL OR y.year >= :year)"
    )
    List<Car> searchCar(
            @Param("details") String details,
            @Param("year") Integer year
    );
}
