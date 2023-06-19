package org.coursework.project_warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.coursework.project_warehouse.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder

@Entity(name = "cart")
@Table
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToOne
    private User persons;

    private Boolean aStatus;

    @ToString.Exclude
    @OneToMany(mappedBy = "cart")
    private List<OrderEntity> orders;


    @ToString.Exclude
    @OneToMany(mappedBy = "cart")
    private List<CableToCartEntity> cablesToCarts;
}
