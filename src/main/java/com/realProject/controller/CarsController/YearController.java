package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Year;
import com.realProject.service.CarsServices.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/year")
public class YearController {

    @Autowired
    private YearService yearService;
    //http://localhost:8080/api/v1/year/add
    @PostMapping("/add")
    public String addYears(
            @RequestBody Year year
            ){
        return yearService.addYears(year);
    }

    //http://localhost:8080/api/v1/year/get
    @GetMapping("/get")
    public List<Integer> getall(){
        return yearService.getall();
    }
}
