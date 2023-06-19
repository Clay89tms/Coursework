package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableToCartEntity;
import org.coursework.project_warehouse.dto.CartEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.CableToCartRepository;
import org.coursework.project_warehouse.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CableToCartRepository cableToCartRepository;

    public CartEntity createOrFindWorkCart(User person) {

        List<CartEntity> carts = person.getCart();

        if (carts.get(carts.size() - 1).getAStatus()) {
            return carts.get(carts.size() - 1);
        }
        CartEntity newCart = CartEntity.builder()
                .persons(person)
                .aStatus(true)
                .build();
        CartEntity cart = cartRepository.save(newCart);

        return cart;

    }

    public CableToCartEntity setCable(CartEntity cart, CableEntity cable) {

        if (cart.getCablesToCarts() != null) {
            for (CableToCartEntity cableToCartEntity : cart.getCablesToCarts()) {
                if (cableToCartEntity.getCable().getId().equals(cable.getId())) {
                    cableToCartEntity.setQuantity(cableToCartEntity.getQuantity() + 1);
                    return cableToCartRepository.save(cableToCartEntity);
                }
            }
        }
        CableToCartEntity cableToCart = new CableToCartEntity(null, 1, cable, cart);

        return cableToCartRepository.save(cableToCart);

    }

}
