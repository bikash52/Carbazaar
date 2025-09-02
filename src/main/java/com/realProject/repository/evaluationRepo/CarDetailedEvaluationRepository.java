package com.realProject.repository.evaluationRepo;

import com.realProject.entity.evaluation.CarDetailedEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailedEvaluationRepository extends JpaRepository<CarDetailedEvaluation, Long> {
}