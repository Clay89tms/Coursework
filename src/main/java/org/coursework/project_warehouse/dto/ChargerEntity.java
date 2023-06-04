package org.coursework.project_warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "chargers")
public class ChargerEntity {
    // 1 Huawei charger (power) 30(w)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type = "charger";

    @NotBlank(message = "Производитель ?")
    private String brand;

    @NotNull(message = "Мощность ?")
    @Min(value = 1, message = "Минимум = 1")
    private Double power;

    @NotBlank(message = "Цвет ?")
    private String color;

    @NotNull(message = "Колличество ?")
    @Min(value = 0, message = "Минимум = 0")
    private Integer quantity = 0;

    @NotNull(message = "Цена ?")
    private Double price;

    public String getDescription() {
        return brand + " " + type + " power " + power + ", color " + color;
    }
}
