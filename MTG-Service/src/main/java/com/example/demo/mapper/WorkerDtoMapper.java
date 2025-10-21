package com.example.demo.mapper;


import org.mapstruct.Mapper;

import com.example.demo.model.Worker;
import com.example.demo.dto.WorkerDto;
import com.example.demo.service.WorkerService;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;


@Mapper(
    componentModel = "spring",
    uses = {WorkerService.class}
)
public interface WorkerDtoMapper {
    Worker toEntity(WorkerDto workerDto) throws EntityNotFoundException;

    WorkerDto toDto(Worker worker) throws EntityNotFoundException;

    List<WorkerDto> toDtoList(List<Worker> workerList) throws EntityNotFoundException;
}
