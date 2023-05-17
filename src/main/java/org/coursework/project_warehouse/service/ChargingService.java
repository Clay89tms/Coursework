package org.coursework.project_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.repository.ChargingRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class ChargingService {

    private final ChargingRepository repository;


}
