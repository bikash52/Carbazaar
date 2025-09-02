package com.realProject.service.evaluationService;

import com.realProject.entity.evaluation.Area;
import com.realProject.repository.evaluationRepo.AreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    private AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public String addArea(Area area) {
        Area areas = areaRepository.save(area);
        return "saved";
    }

    public List<Area> allArea() {
        List<Area> areas = areaRepository.findAll();
        return areas;
    }
}
