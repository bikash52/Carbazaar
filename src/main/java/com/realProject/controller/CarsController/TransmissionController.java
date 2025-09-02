package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Model;
import com.realProject.entity.Car.Transmission;
import com.realProject.service.CarsServices.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Transmission")
public class TransmissionController {

    @Autowired
    private TransmissionService transmissionService;
    //http://localhost:8080/api/v1/Transmission/add
    @PostMapping("/add")
    public String addTransmissions(
            @RequestBody Transmission transmission
    ){
        return transmissionService.addTransmissions(transmission);
    }

    //http://localhost:8080/api/v1/Transmission/get
    @GetMapping("/get")
    public List<String> getall(){
        return transmissionService.getall();
    }
}
