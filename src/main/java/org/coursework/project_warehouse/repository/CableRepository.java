package org.coursework.project_warehouse.repository;

import org.coursework.project_warehouse.dto.CableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CableRepository extends JpaRepository<CableEntity, Integer> {
}
