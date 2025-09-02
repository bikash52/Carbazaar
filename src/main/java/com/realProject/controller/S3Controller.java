package com.realProject.controller;

import com.realProject.entity.Car.Car;
import com.realProject.entity.Car.CarImage;
import com.realProject.repository.CarRelatedRepo.CarImageRepository;
import com.realProject.repository.CarRelatedRepo.CarRepository;
import com.realProject.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;
    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;

    public S3Controller(S3Service s3Service, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.s3Service = s3Service;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    //http://localhost:8080/s3/upload/car/{carId}
    @PostMapping("/upload/car/{carId}")
    public ResponseEntity<CarImage> uploadFile(
            @RequestParam("file") MultipartFile file,
           @PathVariable long carId
      ) {
        try {
            String fileUrl = s3Service.uploadFile(file);

            Optional<Car> byId = carRepository.findById(carId);
            if(byId.isEmpty()){
              throw new RuntimeException("Car with ID "+ carId +"Not Found");
            }
            Car car = byId.get();

            CarImage image = new CarImage();
            image.setFileUrl(fileUrl);
            image.setCar(car);
            CarImage saveImage = carImageRepository.save(image);
            return new ResponseEntity<>(saveImage,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
