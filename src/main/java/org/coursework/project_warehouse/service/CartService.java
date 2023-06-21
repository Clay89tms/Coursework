package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableToCartEntity;
import org.coursework.project_warehouse.dto.CartEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.dto.CaseEntity;
import org.coursework.project_warehouse.dto.CaseToCartEntity;
import org.coursework.project_warehouse.dto.ChargerEntity;
import org.coursework.project_warehouse.dto.ChargerToCartEntity;
import org.coursework.project_warehouse.dto.OrderEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.CableToCartRepository;
import org.coursework.project_warehouse.repository.CartRepository;
import org.coursework.project_warehouse.repository.CaseToCartRepository;
import org.coursework.project_warehouse.repository.ChargerToCartRepository;
import org.coursework.project_warehouse.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CaseToCartRepository caseToCartRepository;
    private final ChargerToCartRepository chargerToCartRepository;
    private final CableToCartRepository cableToCartRepository;
    private final OrderRepository orderRepository;


    public CartEntity createOrFindWorkCart(User person) {

        List<CartEntity> carts = person.getCart();
        if (carts.size() > 0) {
            int lastIndex = (carts.size() - 1);

            if (carts.get(lastIndex).getAStatus()) {
                return carts.get(lastIndex);
            }
        }
        CartEntity newCart = CartEntity.builder()
                .persons(person)
                .aStatus(true)
                .total(0.00)
                .cablesToCarts(new ArrayList<>())
                .caseToCarts(new ArrayList<>())
                .chargerToCarts(new ArrayList<>())
                .build();
        CartEntity cart = cartRepository.save(newCart);

        return cart;

    }

    public CableToCartEntity setCable(CartEntity cart, CableEntity cable) {

        if (cart.getCablesToCarts() != null) {
            cart = calculateTotalAdd(cart, cable.getPrice());
            for (CableToCartEntity cableToCartEntity : cart.getCablesToCarts()) {
                if (cableToCartEntity.getCable().getId().equals(cable.getId())) {
                    cableToCartEntity.setQuantity(cableToCartEntity.getQuantity() + 1);
                    return cableToCartRepository.save(cableToCartEntity);
                }
            }
        }
        return cableToCartRepository.save(new CableToCartEntity(null, 1, cable, cart));
    }

    public CaseToCartEntity setCase(CartEntity cart, CaseEntity aCase) {

        if (cart.getCaseToCarts() != null) {
            cart = calculateTotalAdd(cart, aCase.getPrice());

            for (CaseToCartEntity caseToCartEntity : cart.getCaseToCarts()) {
                if (caseToCartEntity.getACase().getId().equals(aCase.getId())) {
                    caseToCartEntity.setQuantity(caseToCartEntity.getQuantity() + 1);
                    return caseToCartRepository.save(caseToCartEntity);
                }
            }

        }
        return caseToCartRepository.save(new CaseToCartEntity(null, 1, aCase, cart));
    }

    public ChargerToCartEntity setCharger(CartEntity cart, ChargerEntity charger) {

        if (cart.getChargerToCarts() != null) {
            cart = calculateTotalAdd(cart, charger.getPrice());
            for (ChargerToCartEntity chargerToCartEntity : cart.getChargerToCarts()) {
                if (chargerToCartEntity.getCharger().getId().equals(charger.getId())) {
                    chargerToCartEntity.setQuantity(chargerToCartEntity.getQuantity() + 1);
                    return chargerToCartRepository.save(chargerToCartEntity);
                }
            }

        }
        return chargerToCartRepository.save(new ChargerToCartEntity(null, 1, charger, cart));
    }

    public ModelAndView createListCart(CartEntity cart, ModelAndView modelAndView) {
        List<Object> allProduct = new ArrayList<>();

        if (cart.getCablesToCarts() != null && cart.getCablesToCarts().size() > 0) {
            allProduct = getAllCablesToCart(cart, allProduct);

        }
        if (cart.getCaseToCarts() != null && cart.getCaseToCarts().size() > 0) {
            allProduct = getAllCasesToCart(cart, allProduct);
        }
        if (cart.getChargerToCarts() != null && cart.getChargerToCarts().size() > 0) {
            allProduct = getAllChargerToCart(cart, allProduct);
        }

        if (cart.getId() != null) {
            modelAndView.addObject("cartId", cart.getId());
        }


        modelAndView.addObject("allProduct", allProduct);


        modelAndView.addObject("total", cart.getTotal());
        return modelAndView;
    }

    private List<Object> getAllChargerToCart(CartEntity cart, List<Object> allProduct) {
        for (ChargerToCartEntity chargerToCartEntity : cart.getChargerToCarts()) {
            ChargerEntity charger = chargerToCartEntity.getCharger();
            charger.setQuantity(chargerToCartEntity.getQuantity());
            allProduct.add(charger);
        }
        return allProduct;
    }


    private List<Object> getAllCasesToCart(CartEntity cart, List<Object> allProduct) {
        for (CaseToCartEntity caseToCartEntity : cart.getCaseToCarts()) {
            CaseEntity aCase = caseToCartEntity.getACase();
            aCase.setQuantity(caseToCartEntity.getQuantity());
            allProduct.add(aCase);
        }
        return allProduct;
    }

    private List<Object> getAllCablesToCart(CartEntity cart, List<Object> allProduct) {
        for (CableToCartEntity cableToCartEntity : cart.getCablesToCarts()) {
            CableEntity cable = cableToCartEntity.getCable();
            cable.setQuantity(cableToCartEntity.getQuantity());
            allProduct.add((cable));
        }
        return allProduct;
    }

    private CartEntity calculateTotalAdd(CartEntity cart, Double price) {
        cart.setTotal(cart.getTotal() + price);
        return cart;
    }

    private CartEntity calculateTotalRemove(CartEntity cart, Double price) {
        cart.setTotal(cart.getTotal() - price);
        return cart;
    }

    public void removeProduct(CartEntity cart, Integer id, String type) {

        if ("cable".equalsIgnoreCase(type)) {
            if (cart.getCablesToCarts() != null) {
                removeCable(cart, id);
            }
        }

        if ("case".equalsIgnoreCase(type)) {
            if (cart.getCaseToCarts() != null) {
                removeCase(cart, id);
            }
        }

        if ("charger".equalsIgnoreCase(type)) {
            if (cart.getChargerToCarts() != null) {
                removeCharger(cart, id);
            }
        }

    }

    private void removeCable(CartEntity cart, Integer id) {

        for (CableToCartEntity cablesToCart : cart.getCablesToCarts()) {
            if (cablesToCart.getCable().getId().equals(id)) {
                cart = calculateTotalRemove(cart, cablesToCart.getCable().getPrice());

                if (cablesToCart.getQuantity() > 1) {
                    cablesToCart.setQuantity(cablesToCart.getQuantity() - 1);
                    cableToCartRepository.save(cablesToCart);
                } else {
                    cableToCartRepository.delete(cablesToCart);
                }
            }
        }
    }

    private void removeCase(CartEntity cart, Integer id) {

        for (CaseToCartEntity caseToCart : cart.getCaseToCarts()) {
            if (caseToCart.getACase().getId().equals(id)) {
                cart = calculateTotalRemove(cart, caseToCart.getACase().getPrice());

                if (caseToCart.getQuantity() > 1) {
                    caseToCart.setQuantity(caseToCart.getQuantity() - 1);
                    caseToCartRepository.save(caseToCart);
                } else {
                    caseToCartRepository.delete(caseToCart);
                }
            }
        }
    }

    private void removeCharger(CartEntity cart, Integer id) {

        for (ChargerToCartEntity chargerToCart : cart.getChargerToCarts()) {
            if (chargerToCart.getCharger().getId().equals(id)) {
                cart = calculateTotalRemove(cart, chargerToCart.getCharger().getPrice());

                if (chargerToCart.getQuantity() > 1) {
                    chargerToCart.setQuantity(chargerToCart.getQuantity() - 1);
                    chargerToCartRepository.save(chargerToCart);
                } else {
                    chargerToCartRepository.delete(chargerToCart);
                }
            }
        }
    }

    public void doOrder(CartEntity cart) {

        if (cart.getCaseToCarts().isEmpty() && cart.getCablesToCarts().isEmpty() && cart.getChargerToCarts().isEmpty()) {
        } else {
            pickUpToWarehouse(cart);
            cart.setAStatus(false);

            OrderEntity order = OrderEntity.builder()
                    .date(new Date().toString())
                    .cart(cart)
                    .build();

            orderRepository.save(order);
        }
    }

    private CartEntity pickUpToWarehouse(CartEntity cart) {
        if (cart.getCablesToCarts() != null && cart.getCablesToCarts().size() > 0) {
            for (CableToCartEntity cableToCart : cart.getCablesToCarts()) {
                cableToCart.getCable().setQuantity(cableToCart.getCable().getQuantity() - cableToCart.getQuantity());
            }
        }
        if (cart.getCaseToCarts() != null && cart.getCaseToCarts().size() > 0) {
            for (CaseToCartEntity caseToCart : cart.getCaseToCarts()) {
                caseToCart.getACase().setQuantity(caseToCart.getACase().getQuantity() - caseToCart.getQuantity());
            }
        }
        if (cart.getChargerToCarts() != null && cart.getChargerToCarts().size() > 0) {
            for (ChargerToCartEntity chargerToCart : cart.getChargerToCarts()) {
                chargerToCart.getCharger().setQuantity(chargerToCart.getCharger().getQuantity() - chargerToCart.getQuantity());
            }
        }
        return cart;
    }

    public ModelAndView getAllOrders(User persona, ModelAndView modelAndView) {
        Map<String, Object> allOrders = new HashMap<>();

        List<CartEntity> carts = persona.getCart();
        for (int i = 0; i < carts.size(); i++) {
            List<Object> allProduct = new ArrayList<>();
            CartEntity cart = carts.get(i);
            if (!cart.getAStatus()) {
//                String date = cart.getOrder().getDate();
//                allOrders.add(cart.getOrder());
                allProduct = getAllCablesToCart(cart, allProduct);
                allProduct = getAllCasesToCart(cart, allProduct);
                allProduct = getAllChargerToCart(cart, allProduct);
//                allProduct.add(cart.getOrder());
//                allOrders.put("date" + i, cart.getOrder());
                allOrders.put("allProduct" + i, allProduct);

            }
        }
        modelAndView.addObject("allOrders", allOrders);
//        for (CartEntity cart : carts) {
//            OrderEntity order = cart.getOrder();
//            Date date = order.getDate();
//            allOrders.put("date_" + order.getId(), date);
//
//        }
//        persona.getCart().get(1).getOrder();
        return modelAndView;
    }
}
