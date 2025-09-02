package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Car;
import com.realProject.service.CarsServices.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    private CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //http://localhost:8080/api/v1/car/add
    @PostMapping("/add")
     public String addCarsdetails(
             @RequestBody Car car
    ){
        return carService.addCardetails(car);
    }

    //http://localhost:8080/api/v1/car/{id}
    @GetMapping("/getDetails/{id}")
    public Car getCardetails(
            @PathVariable Long id
    ){
        return carService.getCarDetailsById(id);
    }


    //http://localhost:8080/api/v1/car/searchCar?param=honda
    @GetMapping("/searchCar")

    public  List<Car> searchCar (
            @RequestParam(required = false) String param,
            @RequestParam(required = false)Integer year
    ){
        return carService.searchCar(param,year);
    }

    //http://localhost:8080/api/v1/car?pageNo=0&pageSize=2
    @GetMapping()
    public List<Car> getAllCars(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "2", required = false)int pageSize,
            @RequestParam(defaultValue = "id",required = false) String sortBy,
            @RequestParam(defaultValue = "asc",required = false)String sortDir
    ){
        List<Car> cars = carService.getAllCars(pageNo,pageSize,sortBy,sortDir);
        return cars;
    }

    // http://localhost:8080/api/v1/car/searchByYear?year=2015

//    @GetMapping("/SearchByYear")
//    public List<Car> searchCarByYear(
//            @RequestParam Integer year
//    ){
//        return carService.searchCarByYear(year);
//    }
}
