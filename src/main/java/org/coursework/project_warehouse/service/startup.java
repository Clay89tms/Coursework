package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.dto.CartEntity;
import org.coursework.project_warehouse.dto.CaseEntity;
import org.coursework.project_warehouse.dto.ChargerEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.CartRepository;
import org.coursework.project_warehouse.repository.CableRepository;
import org.coursework.project_warehouse.repository.CaseRepository;
import org.coursework.project_warehouse.repository.ChargerRepository;
import org.coursework.project_warehouse.repository.OrderRepository;
import org.coursework.project_warehouse.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor

@Service
public class startup {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CableRepository cableRepository;
    private final CaseRepository caseRepository;
    private final ChargerRepository chargerRepository;
    private final OrderRepository orderRepository;

//    @PostConstruct
//    public void init() {
//        User admin = User.builder()
//                .auth("admin")
//                .password("admin")
//                .username("admin")
//                .build();
//        User adminRs = userRepository.save(admin);
//
//        CartEntity cartAdmin = CartEntity.builder()
//                .persons(adminRs)
//                .aStatus(true)
//                .total(0.00)
//                .build();
//        CartEntity basketRs = cartRepository.save(cartAdmin);
//
//        CableEntity cable1 = CableEntity.builder()
//                .type("Cable")
//                .brand("Ugreen")
//                .function("TypeC")
//                .length(1.00)
//                .price(15.30)
//                .quantity(100)
//                .build();
//        CableEntity cable2 = CableEntity.builder()
//                .type("Cable")
//                .brand("Ugreen")
//                .function("Lightning")
//                .length(1.50)
//                .price(25.50)
//                .quantity(100)
//                .build();
//        CableEntity cable3 = CableEntity.builder()
//                .type("Cable")
//                .brand("Baseus")
//                .function("MicroUSB")
//                .length(0.70)
//                .price(8.10)
//                .quantity(100)
//                .build();
//        cableRepository.save(cable1);
//        cableRepository.save(cable2);
//        cableRepository.save(cable3);
//
//        CaseEntity aCase1 = CaseEntity.builder()
//                .type("Case")
//                .brand("Spigen")
//                .device("iPhone")
//                .color("red")
//                .quantity(100)
//                .price(23.50)
//                .build();
//        caseRepository.save(aCase1);
//
//        ChargerEntity charger = ChargerEntity.builder()
//                .type("Charger")
//                .brand("Huawei")
//                .power(10.00)
//                .color("white")
//                .quantity(100)
//                .price(44.00)
//                .build();
//        chargerRepository.save(charger);
//
//    }
}
