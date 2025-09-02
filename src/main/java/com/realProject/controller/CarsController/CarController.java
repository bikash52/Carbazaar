package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Car;
import com.realProject.service.CarsServices.CarService;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
@Tag(name = "Car Management", description = "CRUD operations for cars in the marketplace")
public class CarController {

    private CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(
            summary = "Add New Car",
            description = "Add a new car listing to the marketplace"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid car data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token required")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/add")
     public String addCarsdetails(
             @Parameter(description = "Car details to be added", required = true)
             @RequestBody Car car
    ){
        return carService.addCardetails(car);
    }

    @Operation(
            summary = "Get Car Details",
            description = "Retrieve detailed information about a specific car by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car details retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    @GetMapping("/getDetails/{id}")
    public Car getCardetails(
            @Parameter(description = "Car ID", required = true, example = "1")
            @PathVariable Long id
    ){
        return carService.getCarDetailsById(id);
    }

    @Operation(
            summary = "Search Cars",
            description = "Search for cars by brand name, model, or manufacturing year"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search results retrieved successfully")
    })
    @GetMapping("/searchCar")
    public  List<Car> searchCar (
            @Parameter(description = "Search parameter (brand/model name)", example = "honda")
            @RequestParam(required = false) String param,
            @Parameter(description = "Manufacturing year", example = "2020")
            @RequestParam(required = false)Integer year
    ){
        return carService.searchCar(param,year);
    }

    @Operation(
            summary = "Get All Cars (Paginated)",
            description = "Retrieve all cars with pagination and sorting options"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars retrieved successfully")
    })
    @GetMapping()
    public List<Car> getAllCars(
            @Parameter(description = "Page number (0-based)", example = "0")
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam(defaultValue = "2", required = false)int pageSize,
            @Parameter(description = "Field to sort by", example = "id")
            @RequestParam(defaultValue = "id",required = false) String sortBy,
            @Parameter(description = "Sort direction (asc/desc)", example = "asc")
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
