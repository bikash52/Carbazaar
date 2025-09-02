package com.realProject.repository.evaluationRepo;

import com.realProject.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area>findByPincode(String pincode);
}