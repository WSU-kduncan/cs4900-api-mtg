package com.mtg.mtgservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtg.mtgservice.dto.WorkerDto;
import com.mtg.mtgservice.mapper.WorkerDtoMapper;
import com.mtg.mtgservice.service.WorkerService;

import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

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
    ResponseEntity<WorkerDto> getWorkerById(@PathVariable Integer employeeID) {
        return new ResponseEntity<>(
            workerDtoMapper.toDto(workerService.getWorkerById(employeeID)), HttpStatus.OK);
            
    }
}
