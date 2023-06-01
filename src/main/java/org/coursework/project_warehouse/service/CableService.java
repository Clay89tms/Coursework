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


    public List<CableEntity> getAllCable(){
     return repository.findAll();
    }


    public CableEntity save(CableEntity cable) {
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

    public void updateCable(CableEntity cable) {
    }
}
