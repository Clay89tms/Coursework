package org.coursework.project_warehouse.repository;

import org.coursework.project_warehouse.dto.CableToCartEntity;
import org.coursework.project_warehouse.dto.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CableToCartRepository extends JpaRepository<CableToCartEntity, Integer> {
    CartEntity findByCableId(Integer id);
}
