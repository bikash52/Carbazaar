package com.realProject.service.CRMService;

import com.realProject.entity.evaluation.Agents;
import com.realProject.entity.evaluation.Area;
import com.realProject.entity.evaluation.CustomerVisit;
import com.realProject.repository.evaluationRepo.AgentsRepository;
import com.realProject.repository.evaluationRepo.AreaRepository;
import com.realProject.repository.evaluationRepo.CustomerVisitRepository;
import com.realProject.service.TwilioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CRMService {
    private AreaRepository areaRepository;
    private AgentsRepository agentsRepository;
    private CustomerVisitRepository customerVisitRepository;
    private TwilioService twilioService;

    public CRMService(AreaRepository areaRepository, AgentsRepository agentsRepository, CustomerVisitRepository customerVisitRepository, TwilioService twilioService) {
        this.areaRepository = areaRepository;
        this.agentsRepository = agentsRepository;
        this.customerVisitRepository = customerVisitRepository;
        this.twilioService = twilioService;
    }


    public List<Area> searchAgent(String pincode) {
        List<Area> byPinCode = areaRepository.findByPincode(pincode);
        return byPinCode;
    }


    public String allocateAgent(Long agentId, Long customerId) {
        Agents agent = null;
        CustomerVisit customerVisit = null;

        Optional<Agents> opAgent = agentsRepository.findById(agentId);
        if (opAgent.isPresent()){
            agent = opAgent.get();
        }else{
            throw new RuntimeException("Agent ID is not Valid");
        }

        Optional<CustomerVisit> byCustomer = customerVisitRepository.findById(customerId);
        if (byCustomer.isPresent()){
            customerVisit= byCustomer.get();
        }else {
            throw new RuntimeException("Customer Id is not Valid");
        }

        customerVisit.setAgents(agent);

        CustomerVisit allocation = customerVisitRepository.save(customerVisit);
        twilioService.sendSms("+916206296695","Your Agent is allocated Contact number:- +9162006296684");
        return "Agent allocation is done";
    }
}
