package org.coursework.project_warehouse.repository;

import org.coursework.project_warehouse.dto.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    //

}
