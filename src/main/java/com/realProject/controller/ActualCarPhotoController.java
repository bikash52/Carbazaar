package com.realProject.controller;


import com.realProject.entity.evaluation.CarEvaluationphotos;
import com.realProject.service.evaluationService.ActualCarPhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/car-photos")
public class ActualCarPhotoController {

    private  ActualCarPhotoService actualCarPhotoService;

    public ActualCarPhotoController (ActualCarPhotoService actualCarPhotoService) {
        this.actualCarPhotoService = actualCarPhotoService;
    }


    @PostMapping(value = "/upload/{evaluationId}", consumes = "multipart/form-data")
    public ResponseEntity<List<CarEvaluationphotos>> uploadCarPhotos(
            @PathVariable Long evaluationId,
            @RequestParam("files") List<MultipartFile> files) {

        if (files == null || files.isEmpty()) {
            throw new RuntimeException("No files uploaded. Please select files.");
        }

        List<CarEvaluationphotos> uploadedPhotos = actualCarPhotoService.uploadCarPhotos(files, evaluationId);
        return ResponseEntity.ok(uploadedPhotos);
    }

}