package com.realProject.service.CarsServices;

import com.realProject.entity.Car.Brand;
import com.realProject.repository.CarRelatedRepo.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;


    public String addbrands(Brand brands) {
        Optional<Brand> opBrand = brandRepository.findByName(brands.getName());
        if(opBrand.isPresent()){
           throw new RuntimeException("Brand is alredy exist");
        }
        brandRepository.save(brands);
        return "Brand Saved";
    }

    public List<String> getall() {
        return brandRepository.findAllBrandNames();
    }
}
