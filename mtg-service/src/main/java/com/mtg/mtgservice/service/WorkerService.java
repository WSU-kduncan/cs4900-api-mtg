package com.mtg.mtgservice.service;

import com.mtg.mtgservice.dto.WorkerDto;
import com.mtg.mtgservice.mapper.WorkerDtoMapper;
import com.mtg.mtgservice.model.Worker;
import com.mtg.mtgservice.repository.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkerService {
  private final WorkerRepository workerRepository;
  private final WorkerDtoMapper workerDtoMapper;

  public List<Worker> getAllWorkers() {
    return workerRepository.findAll();
  }

  public Worker getWorkerByID(Integer employeeID) throws EntityNotFoundException {
    return workerRepository.findById(employeeID).orElseThrow();
  }

  public Worker updateWorker(Worker worker) {
    if (!workerRepository.existsById(worker.getEmployeeID())) {
      throw new EntityNotFoundException("Worker not found");
    }
    return workerRepository.save(worker);
  }

  public Worker createWorker(WorkerDto workerDto) throws EntityNotFoundException {
    return workerRepository.saveAndFlush(workerDtoMapper.toEntity(workerDto));
  }
}
