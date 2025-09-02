package com.realProject.service.evaluationService;

import com.realProject.entity.evaluation.CustomerVisit;
import com.realProject.repository.evaluationRepo.CustomerVisitRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerVisitService {
          private CustomerVisitRepository customerVisitRepository;

    public CustomerVisitService(CustomerVisitRepository customerVisitRepository) {
        this.customerVisitRepository = customerVisitRepository;
    }

    public String addDetails(CustomerVisit customerVisit) {
        CustomerVisit customer = customerVisitRepository.save(customerVisit);
        return "Saved";
    }
}
