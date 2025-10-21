package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.model.Worker;

import lombok.RequiredArgsConstructor;
import com.example.demo.repository.WorkerRepository;

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