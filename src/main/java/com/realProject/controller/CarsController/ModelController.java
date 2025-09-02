package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Brand;
import com.realProject.entity.Car.Model;
import com.realProject.service.CarsServices.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    //http://localhost:8080/api/v1/model/add
    @PostMapping("/add")
    public String addmodels(
            @RequestBody Model models
    ){
        return modelService.addmodels(models);
    }

    //http://localhost:8080/api/v1/model/get
    @GetMapping("/get")
    public List<String> getall(){
        return modelService.getall();
    }
}
