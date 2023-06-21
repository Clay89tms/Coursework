package org.coursework.project_warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder

@Entity
@Table(name = "casePhone")
public class CaseEntity {
        // 1 case Spigen (for) iPhone, color
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String type = "Case";

        @NotBlank(message = "Производитель ?")
        private String brand;

        @NotBlank(message = "Устройство ?")
        private String device;

        @NotBlank(message = "Цвет ?")
        private String color;

        @NotNull(message = "Колличество ?")
        @Min(value = 0, message = "Минимум = 0")
        private Integer quantity = 0;

        @NotNull(message = "Цена ?")
        private Double price;

        public String getDescription() {
                return brand + " " + type + " for " + device + ", color " + color;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CaseEntity that = (CaseEntity) o;
                return Objects.equals(type, that.type) && Objects.equals(brand, that.brand) && Objects.equals(device, that.device) && Objects.equals(color, that.color) && Objects.equals(price, that.price);
        }

        @Override
        public int hashCode() {
                return Objects.hash(type, brand, device, color, price);
        }
}
