package com.realProject.controller.CRMController;

import com.realProject.entity.evaluation.Area;
import com.realProject.service.CRMService.CRMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crm")
public class CRM {
    private CRMService crmService;

    public CRM(CRMService crmService) {
        this.crmService = crmService;
    }

    @GetMapping("/get")
    ResponseEntity<List<Area>> searchAgent(
            @RequestParam String pincode
    ){
        List<Area> areas = crmService.searchAgent(pincode);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PutMapping("/allocating")
    ResponseEntity<?> allocateAgent(
            @RequestParam Long agentId,
            @RequestParam Long customerId
    ){
        String crm = crmService.allocateAgent(agentId,customerId);
        return new ResponseEntity<>(crm,HttpStatus.OK);
    }
}
