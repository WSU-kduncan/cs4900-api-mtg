package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.WorkerDto;
import com.mtg.mtgservice.model.Worker;
import com.mtg.mtgservice.service.WorkerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {WorkerService.class})
public interface WorkerDtoMapper {
  Worker toEntity(WorkerDto workerDto) throws EntityNotFoundException;

  WorkerDto toDto(Worker worker) throws EntityNotFoundException;

  List<WorkerDto> toDtoList(List<Worker> workerList) throws EntityNotFoundException;
}
