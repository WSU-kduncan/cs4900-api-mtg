package com.mtg.mtgservice.service;


import org.springframework.stereotype.Service;

import com.mtg.mtgservice.model.Worker;
import com.mtg.mtgservice.repository.WorkerRepository;

import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


@RequiredArgsConstructor
@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Integer employeeID) throws EntityNotFoundException {
        return workerRepository
            .findById(employeeID)
            .orElseThrow();
    }
}