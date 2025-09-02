package com.realProject.service.CarsServices;

import com.realProject.entity.Car.Year;
import com.realProject.repository.CarRelatedRepo.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YearService {

    @Autowired
    private YearRepository yearRepository;


    public String addYears(Year year) {
        Optional<Year> opYear = yearRepository.findByYear(year.getYear());
        if(opYear.isPresent()){
            throw new RuntimeException("Year is already Present");
        }
        yearRepository.save(year);
        return "year Saved";
    }

    public List<Integer> getall() {
        return yearRepository.findAllYear();
    }
}
