package org.coursework.project_warehouse.repository;

import org.coursework.project_warehouse.dto.ChargerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChargingRepository extends JpaRepository<ChargerEntity, Integer> {
}
