package com.realProject.repository.CarRelatedRepo;

import com.realProject.entity.Car.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    Optional<FuelType> findByFuelType(String fuelType);

    @Query("Select f.fuelType from FuelType f")
    List<String> findAllFuelTypes();
}