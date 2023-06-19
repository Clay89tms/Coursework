package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableToCartEntity;
import org.coursework.project_warehouse.dto.CartEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartEntity createOrFind(User person) {

        List<CartEntity> carts = person.getCart();

        if (carts.get(carts.size() - 1).getAStatus()) {
            return carts.get(carts.size() - 1);
        }
            CartEntity newCart = CartEntity.builder()
                    .persons(person)
                    .build();
        CartEntity cart = cartRepository.save(newCart);

        return cart;

//        for (CartEntity cart : carts) {
//            if (cart.getAStatus() == true) {
//                return cart;
//            } else {
//                CartEntity newBasket = CartEntity.builder()
//                        .persons(person)
//                        .build();
//            }
//
//        }

    }

    public void setCable(CartEntity cart, CableEntity cable) {
        List<CableToCartEntity> listCables;
        if (cart.getCablesToCarts() == null) {
            listCables = new ArrayList<>();
        } else {
                listCables = cart.getCablesToCarts();
        }


        cart.setCables(listCables);
        cart.setDescription(cable.getDescription());
        cart.setPrice(cable.getPrice());
        if (cart.getQuantity() == null || cart.getQuantity() == 0) {
            cart.setQuantity(1);
        } else {
            cart.setQuantity(cart.getQuantity() + 1);
        }
        cartRepository.save(cart);
    }
}
