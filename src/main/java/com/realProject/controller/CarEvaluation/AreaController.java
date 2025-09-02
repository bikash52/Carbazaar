package com.realProject.controller.CarEvaluation;

import com.realProject.entity.evaluation.Area;
import com.realProject.service.evaluationService.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/area")
public class AreaController {

    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping("/add")
    ResponseEntity<?> addArea(
            @RequestBody Area area
            ){
      String areas = areaService.addArea(area);
      return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @GetMapping("/get")
    ResponseEntity<List<Area>> allArea(

    ){
        List<Area> areas = areaService.allArea();
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }
}
