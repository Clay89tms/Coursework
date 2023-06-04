package org.coursework.project_warehouse.repository;

import org.coursework.project_warehouse.dto.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CaseRepository extends JpaRepository<CaseEntity, Integer> {
}
