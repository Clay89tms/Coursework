package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

//    private Specification<CableEntity> createSpecification(CableEntity cable) {
//        return (root, query, builder) -> {
//
//            Predicate predicate = builder.conjunction();
////            List<Predicate> conditions = new ArrayList<>();
//            if (cable.getBrand() != null) {
//                predicate = builder.and(predicate, builder.between((root.get(CableEntity_.BRAND), cable.getBrand())))
//                Predicate brand = builder.equal(root.get(Room_.NUM_OF_TOURIST), cable.getBrand());
//                conditions.add(brand);
//            }
//            if (cable.getTypesByOccupancy() != null) {
//                Predicate equalTypeByOccupancy = builder.equal(root.get(Room_.TYPES_BY_OCCUPANCY), cable.getTypesByOccupancy());
//                conditions.add(equalTypeByOccupancy);
//            }
//
//            if (cable.getTypesByView() != null) {
//                Predicate equalTypeByView = builder.equal(root.get(Room_.TYPES_BY_VIEW), cable.getTypesByView());
//                conditions.add(equalTypeByView);
//            }
//            if (cable.getHotel() != null) {
//                Predicate equalHotel = builder.equal(root.get(Room_.HOTEL), cable.getHotel());
//                conditions.add(equalHotel);
//
//            }
//            if (cable.getBooked() != null) {
//                Predicate equalBooked = builder.equal(root.get(Room_.BOOKED), cable.getBooked());
//                conditions.add(equalBooked);
//
//            }
//            if (cable.getPreBooked() != null) {
//                Predicate equalPreBooked = builder.equal(root.get(Room_.PRE_BOOKED), cable.getPreBooked());
//                conditions.add(equalPreBooked);
//
//            }
//            if (cable.getBoardBases() != null) {
//                Join<Room, Hotel> hotelJoin = root.join(Room_.HOTEL);
//
//                Predicate equalBoardBasis = builder.isMember(cable.getBoardBases(), hotelJoin.get(Hotel_.BOARD_BASIS_SET));
//                conditions.add(equalBoardBasis);
//
//            }
//            return builder.and(conditions.toArray(new Predicate[]{}));
//
//        };
//    }
}
