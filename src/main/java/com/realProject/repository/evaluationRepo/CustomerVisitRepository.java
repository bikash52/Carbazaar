package com.realProject.repository.evaluationRepo;

import com.realProject.entity.evaluation.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Long> {
}