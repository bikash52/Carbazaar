package com.realProject.service.evaluationService;

import com.realProject.entity.evaluation.CarDetailedEvaluation;
import com.realProject.repository.evaluationRepo.CarDetailedEvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarDetailedEvaluationService {
    private CarDetailedEvaluationRepository evaluationRepository;

    public CarDetailedEvaluationService(CarDetailedEvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }
    public CarDetailedEvaluation findById(Long id) {
        Optional<CarDetailedEvaluation> evaluation = evaluationRepository.findById(id);
        return evaluation.orElse(null); // Return null if not found
    }

    public CarDetailedEvaluation save(CarDetailedEvaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }
}
