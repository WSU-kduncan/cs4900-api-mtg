package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.WorkerDto;
import com.mtg.mtgservice.mapper.WorkerDtoMapper;
import com.mtg.mtgservice.model.Worker;
import com.mtg.mtgservice.service.WorkerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(
    path = "workers",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class WorkerController {
  private final WorkerDtoMapper workerDtoMapper;

  private final WorkerService workerService;

  @GetMapping
  ResponseEntity<List<WorkerDto>> getAllWorkers() {
    return new ResponseEntity<>(
        workerDtoMapper.toDtoList(workerService.getAllWorkers()), HttpStatus.OK);
  }

  @GetMapping(path = "{employeeID}")
  ResponseEntity<WorkerDto> getWorkerByID(@PathVariable Integer employeeID) {
    return new ResponseEntity<>(
        workerDtoMapper.toDto(workerService.getWorkerByID(employeeID)), HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<WorkerDto> newWorker(@RequestBody WorkerDto workerDto) {
    Worker worker;
    try {
      worker = workerService.createWorker(workerDto);
      // return new ResponseEntity<>(
      //     workerDtoMapper.toDto(worker),
      //     HttpStatus.CREATED);
    } catch (EntityNotFoundException e) {

      return new ResponseEntity<WorkerDto>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(workerDtoMapper.toDto(worker), HttpStatus.CREATED);
  }

  @PutMapping(path = "{employeeID}")
  ResponseEntity<WorkerDto> updateWorker(
      @PathVariable Integer employeeID, @RequestBody WorkerDto workerDto) {
    Worker worker;
    try {
      worker = workerService.updateWorker(workerDtoMapper.toEntity(workerDto));
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(workerDtoMapper.toDto(worker), HttpStatus.OK);
  }
}
