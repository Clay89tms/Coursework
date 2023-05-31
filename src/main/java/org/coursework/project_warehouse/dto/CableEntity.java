package org.coursework.project_warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cables")
public class CableEntity {
    // 1 Cable Ugreen type-C_to_type-C 1m 10$
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type = "Cable";

    private String brand;
    @NotBlank(message = "not Blank")
    private String function;

    private Double length;

    private Integer quantity;

    private Double price;

    public String getDescription() {
        return type + " " + brand + " " + function + " " + length;
    }
}
