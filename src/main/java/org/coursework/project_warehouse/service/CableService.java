package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.repository.CableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CableService {

    private final CableRepository repository;


    public List<CableEntity> getAllCable() {
        return repository.findAll();
    }


    public CableEntity save(CableEntity cable) {
        List<CableEntity> allCable = repository.findAll();
        for (int i = 0; i < allCable.size(); i++) {
            if (allCable.get(i).equals(cable)){
                cable.setId(allCable.get(i).getId());
                cable.setQuantity(cable.getQuantity() + allCable.get(i).getQuantity());
                break;
            }
        }
        return repository.save(cable);
    }

    public CableEntity editCable(CableEntity cable) {
        return repository.save(cable);
    }

    public void deleteCable(Integer id) {
        CableEntity cableEntity = repository.findById(id).get();
        repository.delete(cableEntity);
    }

    public CableEntity getCable(Integer id) {
        CableEntity cableEntity = repository.findById(id).get();
        return cableEntity;
    }

}
