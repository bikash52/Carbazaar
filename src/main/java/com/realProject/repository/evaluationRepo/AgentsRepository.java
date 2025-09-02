package com.realProject.repository.evaluationRepo;

import com.realProject.entity.evaluation.Agents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentsRepository extends JpaRepository<Agents, Long> {

}