package com.realProject.service.evaluationService;

import com.realProject.entity.evaluation.Agents;
import com.realProject.repository.evaluationRepo.AgentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private AgentsRepository agentsRepository;

    public AgentService(AgentsRepository agentsRepository) {
        this.agentsRepository = agentsRepository;
    }


    public String addAgent(Agents agents) {
        Agents agent = agentsRepository.save(agents);
        return "Saved";
    }


    public List<Agents> getAllAgents() {
        List<Agents> agents = agentsRepository.findAll();
        return agents;
    }
}
