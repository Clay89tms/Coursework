package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CaseEntity;
import org.coursework.project_warehouse.repository.CaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class CaseService {

    private final CaseRepository repository;


    public List<CaseEntity> getAllCase() {
        return repository.findAll();
    }


    public CaseEntity save(CaseEntity caseEntity) {
        List<CaseEntity> allCase = repository.findAll();
        for (int i = 0; i < allCase.size(); i++) {
            if (allCase.get(i).equals(caseEntity)){
                caseEntity.setId(allCase.get(i).getId());
                caseEntity.setQuantity(caseEntity.getQuantity() + allCase.get(i).getQuantity());
                break;
            }
        }
        return repository.save(caseEntity);
    }

    public CaseEntity editCase(CaseEntity caseEntity) {
        return repository.save(caseEntity);
    }

    public void deleteCase(Integer id) {
        CaseEntity caseEntity = repository.findById(id).get();
        repository.delete(caseEntity);
    }

    public CaseEntity getCase(Integer id) {
        CaseEntity caseEntity = repository.findById(id).get();
        return caseEntity;
    }

}
