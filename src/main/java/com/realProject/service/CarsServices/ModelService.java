package com.realProject.service.CarsServices;

import com.realProject.entity.Car.Model;
import com.realProject.repository.CarRelatedRepo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;


    public String addmodels(Model models) {
        Optional<Model> opModel = modelRepository.findByName(models.getName());
        if(opModel.isPresent()){
            throw new RuntimeException("Model is Already exist");
        }
        modelRepository.save(models);
        return "Model Saved";
    }

    public List<String> getall() {
        return modelRepository.findAllModelNames();
    }


}
