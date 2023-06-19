package org.coursework.project_warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder

@Entity
@Table(name = "cables")
public class CableEntity {
    // 1 Xiaomi cable type-C 1m
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type = "Cable";

    @NotBlank(message = "Производитель ?")
    private String brand;

    @NotBlank(message = "Тип разъема ?")
    private String function;

    @NotNull(message = "Длинна ?")
    private Double length;

    @NotNull(message = "Колличество ?")
    @Min(value = 0, message = "Минимум = 0")
    private Integer quantity = 0;

    @NotNull(message = "Цена ?")
    private Double price;

    @ToString.Exclude
    @OneToMany(mappedBy = "cable")
    private List<CableToCartEntity> cablesToCarts;

    public String getDescription() {
        return brand + " " + type + " use " + function + ", " + length + " m";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CableEntity that = (CableEntity) o;
        return Objects.equals(type, that.type) && Objects.equals(brand, that.brand) && Objects.equals(function, that.function) && Objects.equals(length, that.length) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand, function, length, price);
    }
}
