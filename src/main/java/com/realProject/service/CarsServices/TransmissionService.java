package com.realProject.service.CarsServices;

import com.realProject.entity.Car.Transmission;
import com.realProject.repository.CarRelatedRepo.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionService {

    @Autowired
    private TransmissionRepository transmissionRepository;


    public String addTransmissions(Transmission transmission) {

        Optional<Transmission> opTrans = transmissionRepository.findByType(transmission.getType());
        if(opTrans.isPresent()){
            throw new RuntimeException("This type of transmission is already Present");
        }
        transmissionRepository.save(transmission);
        return "Transmission Saved";
    }

    public List<String> getall() {
        return transmissionRepository.findAllTransmission();
    }
}
