package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Brand;
import com.realProject.entity.Car.FuelType;
import com.realProject.service.CarsServices.BrandService;
import com.realProject.service.CarsServices.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fueltype")
public class FuelTypeController {

    @Autowired
    private FuelTypeService fuelTypeService;

    //http://localhost:8080/api/v1/fueltype/add
    @PostMapping("/add")
    public String addFuelType(
            @RequestBody FuelType fuelType
    ){
        return fuelTypeService.addFuelType(fuelType);
    }

       //http://localhost:8080/api/v1/fueltype/get
    @GetMapping("/get")
    public List<String> getall(){
        return fuelTypeService.getall();
    }

}
