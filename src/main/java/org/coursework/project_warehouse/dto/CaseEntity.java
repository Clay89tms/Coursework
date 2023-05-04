package org.coursework.project_warehouse.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cables")
public class CaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String type;
        private String brand;
        private String vendor;
        private String model;
        private String color;
        private Integer quantity;
        private Double price;
}
