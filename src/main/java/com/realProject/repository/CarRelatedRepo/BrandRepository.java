package com.realProject.repository.CarRelatedRepo;

import com.realProject.entity.Car.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

  Optional<Brand> findByName(String brandname);

  @Query("Select b.name from  Brand b")
  List<String> findAllBrandNames();
}