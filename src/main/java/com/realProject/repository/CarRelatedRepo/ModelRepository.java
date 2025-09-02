package com.realProject.repository.CarRelatedRepo;

import com.realProject.entity.Car.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findByName(String modelnames);

    @Query("Select b.name from  Model b")
    List<String> findAllModelNames();
}