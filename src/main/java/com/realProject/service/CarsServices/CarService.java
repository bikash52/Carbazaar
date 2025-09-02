package com.realProject.service.CarsServices;

import com.realProject.entity.Car.Car;
import com.realProject.repository.CarRelatedRepo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Add Car Details
    public String addCardetails(Car car) {
        carRepository.save(car);
        return "Data is saved";
    }

    // Get Car Details by ID
    public Car getCarDetailsById(Long id) {
        Optional<Car> opCar = carRepository.findById(id);
        return opCar.orElse(null); // Return car or null if not found
    }

    // Search Cars by param (Brand, Transmission, or Year)
    public List<Car> searchCar(String param, Integer year) {
        List<Car> cars = carRepository.searchCar(param, year);
        if(cars.isEmpty()){
            throw new RuntimeException("No such cars are present " + param);
        }
        return cars;
    }

//    public List<Car> searchCarByYear(Integer year) {
//        List<Car> cars = carRepository.searchCarByYear(year);
//        return cars;
//    }


    // Get All Cars with Pagination and Sorting
    public List<Car> getAllCars(int pageNo, int pageSize, String sortBy, String sortDir) {

        // Default sorting by id if not specified
        if ("brand".equalsIgnoreCase(sortBy)) {
            sortBy = "brand.name";  // Sorting by brand name
        } else if ("year".equalsIgnoreCase(sortBy)) {
            sortBy = "year.year"; // Sorting by car's year
        } else {
            sortBy = "id"; // Default fallback to sort by ID
        }

        // Sort direction logic
        Sort sort = Sort.by(sortBy);
        if ("asc".equalsIgnoreCase(sortDir)) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }

        // Page request with sorting
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Car> record = carRepository.findAll(page);
        return record.getContent(); // Get the cars from the page
    }
}
