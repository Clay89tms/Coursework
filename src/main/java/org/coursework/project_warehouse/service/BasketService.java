package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.BasketEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Service
public class BasketService {
    private final BasketRepository basketRepository;


    public BasketEntity createOrFind(User person) {
        List<BasketEntity> allBaskets = basketRepository.findAll();
        for (BasketEntity basket : allBaskets) {
            if (basket.getDescription() == null || basket.getPrice() == null ||
                    basket.getQuantity() == null || basket.getTotal() == null) {
                return basket;
            }
        }
        BasketEntity basket = new BasketEntity();
        basket.setPersons(person);
        return basketRepository.save(basket);
    }

    public void setCable(BasketEntity basket, CableEntity cable) {
        List<CableEntity> listCables;
        if (basket.getCables() == null) {
            listCables = new ArrayList<>();
        } else {
            listCables = basket.getCables();
        }
        listCables.add(cable);

        basket.setCables(listCables);
        basket.setDescription(cable.getDescription());
        basket.setPrice(cable.getPrice());
        if (basket.getQuantity() == null || basket.getQuantity() == 0) {
            basket.setQuantity(1);
        } else {
            basket.setQuantity(basket.getQuantity() + 1);
        }
        basketRepository.save(basket);
    }
}
