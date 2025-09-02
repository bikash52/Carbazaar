package com.realProject.service.evaluationService;


import com.realProject.entity.evaluation.CarDetailedEvaluation;
import com.realProject.entity.evaluation.CarEvaluationphotos;
import com.realProject.repository.evaluationRepo.CarDetailedEvaluationRepository;
import com.realProject.repository.evaluationRepo.CarEvaluationphotosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
    public class ActualCarPhotoService {


        private CarEvaluationphotosRepository carEvaluationphotosRepository;
        private CarDetailedEvaluationRepository carDetailedEvaluationRepository;
        private ListS3Services listS3Services;

        public ActualCarPhotoService(CarEvaluationphotosRepository carEvaluationphotosRepository, CarDetailedEvaluationRepository carDetailedEvaluationRepository, ListS3Services listS3Services) {
            this.carEvaluationphotosRepository = carEvaluationphotosRepository;
            this.carDetailedEvaluationRepository = carDetailedEvaluationRepository;
            this.listS3Services = listS3Services;
        }


        public List<CarEvaluationphotos> uploadCarPhotos(List<MultipartFile> files, Long evaluationId) {
            Optional<CarDetailedEvaluation> evaluationOpt = carDetailedEvaluationRepository.findById(evaluationId);
            if (evaluationOpt.isEmpty()) {
                throw new RuntimeException("CarDetailedEvaluation with ID " + evaluationId + " not found.");
            }
            CarDetailedEvaluation evaluation = evaluationOpt.get();

            List<String> uploadedUrls = listS3Services.uploadFiles(files);

            List<CarEvaluationphotos> savedPhotos = new ArrayList<>();

            for (String url : uploadedUrls) {
                CarEvaluationphotos photo = new CarEvaluationphotos();
                photo.setPhotoUrl(url);
                photo.setCarDetailedEvaluation(evaluation);
                savedPhotos.add(carEvaluationphotosRepository.save(photo));
            }

            return savedPhotos;
        }
    }


