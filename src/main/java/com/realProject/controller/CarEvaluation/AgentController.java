package com.realProject.controller.CarEvaluation;


import com.realProject.entity.evaluation.Agents;
import com.realProject.repository.evaluationRepo.AgentsRepository;
import com.realProject.service.evaluationService.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {
             private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/add")
    ResponseEntity<?> addAgent(
            @RequestBody Agents agents
            ){
        String agent = agentService.addAgent(agents);
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Agents>> getAllAgents(

    ){
        List<Agents> agents = agentService.getAllAgents();
        return new ResponseEntity<>(agents,HttpStatus.OK);
    }
}
