package org.coursework.project_warehouse.repository;

import org.coursework.project_warehouse.dto.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

}
