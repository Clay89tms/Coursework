package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.ChargerEntity;
import org.coursework.project_warehouse.repository.ChargerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ChargerService {

    private final ChargerRepository repository;


    public List<ChargerEntity> getAllCharger() {
        return repository.findAll();
    }


    public ChargerEntity save(ChargerEntity chargerEntity) {
        List<ChargerEntity> allCharger = repository.findAll();
        for (int i = 0; i < allCharger.size(); i++) {
            if (allCharger.get(i).equals(chargerEntity)){
                chargerEntity.setId(allCharger.get(i).getId());
                chargerEntity.setQuantity(chargerEntity.getQuantity() + allCharger.get(i).getQuantity());
                break;
            }
        }
        return repository.save(chargerEntity);
    }

    public ChargerEntity editCharger(ChargerEntity chargerEntity) {
        return repository.save(chargerEntity);
    }

    public void deleteCharger(Integer id) {
        ChargerEntity chargerEntity = repository.findById(id).get();
        repository.delete(chargerEntity);
    }

    public ChargerEntity getCharger(Integer id) {
        ChargerEntity chargerEntity = repository.findById(id).get();
        return chargerEntity;
    }

}
