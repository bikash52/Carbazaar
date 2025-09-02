package com.realProject.controller.CarEvaluation;

import com.realProject.entity.evaluation.CustomerVisit;
import com.realProject.service.evaluationService.CustomerVisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/CarVisit")
public class CustomerVisitController {
    private CustomerVisitService customerVisitService;

    public CustomerVisitController(CustomerVisitService customerVisitService) {
        this.customerVisitService = customerVisitService;
    }

       @PostMapping("/add")
    ResponseEntity<?> addDetails(
               @RequestBody CustomerVisit customerVisit
               ){
          String customer = customerVisitService.addDetails(customerVisit);
          return new ResponseEntity<>(customer, HttpStatus.OK);
       }
}
