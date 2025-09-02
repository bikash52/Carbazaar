package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Brand;
import com.realProject.service.CarsServices.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brandcontroller")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //http://localhost:8080/api/v1/brandcontroller/add
    @PostMapping("/add")
    public String addbrands(
            @RequestBody Brand brands
            ){
      return brandService.addbrands(brands);
    }

    //http://localhost:8080/api/v1/brandcontroller/get
    @GetMapping("/get")
    public List<String> getall(){
      return brandService.getall();
    }
}
