package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.WorkerDto;
import com.example.demo.service.WorkerService;
import org.springframework.http.HttpStatus;
import com.example.demo.mapper.WorkerDtoMapper;


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
