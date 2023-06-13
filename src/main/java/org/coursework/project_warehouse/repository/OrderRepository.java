package org.coursework.project_warehouse.repository;

import org.coursework.project_warehouse.dto.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
