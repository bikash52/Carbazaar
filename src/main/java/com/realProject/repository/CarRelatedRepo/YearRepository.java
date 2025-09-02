package com.realProject.repository.CarRelatedRepo;

import com.realProject.entity.Car.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface YearRepository extends JpaRepository<Year, Long> {
    Optional<Year> findByYear(Integer year);

    @Query("Select b.year from  Year b")
    List<Integer> findAllYear();
}