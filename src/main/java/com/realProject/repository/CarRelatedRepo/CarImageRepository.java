package com.realProject.repository.CarRelatedRepo;

import com.realProject.entity.Car.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}