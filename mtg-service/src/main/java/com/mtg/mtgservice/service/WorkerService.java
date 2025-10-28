package com.mtg.mtgservice.service;


import org.springframework.stereotype.Service;

import com.mtg.mtgservice.dto.WorkerDto;
import com.mtg.mtgservice.mapper.WorkerDtoMapper;
import com.mtg.mtgservice.model.Worker;
import com.mtg.mtgservice.repository.WorkerRepository;

import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


@RequiredArgsConstructor
@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final WorkerDtoMapper workerDtoMapper;

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Integer employeeID) throws EntityNotFoundException {
        return workerRepository
            .findById(employeeID)
            .orElseThrow();
    }

    public Worker updateWorker(Worker worker) {
        if (!workerRepository.existsById(worker.getEmployeeID())) {
            throw new EntityNotFoundException("Worker not found");
        }
        return workerRepository.save(worker);
    }

    public Worker createWorker(WorkerDto workerDto) throws EntityNotFoundException{
        return workerRepository.saveAndFlush(workerDtoMapper.toEntity(workerDto));
    }
}