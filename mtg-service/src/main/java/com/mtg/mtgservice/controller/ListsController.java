package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.ListsDto;
import com.mtg.mtgservice.mapper.ListsDtoMapper;
import com.mtg.mtgservice.service.ListsService;
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
    path = "list",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class ListsController {
  private final ListsDtoMapper listDtoMapper;

  private final ListsService listService;

  @GetMapping
  ResponseEntity<List<ListsDto>> getAllLists() {
    return new ResponseEntity<>(listDtoMapper.toDtoList(listService.getAllLists()), HttpStatus.OK);
  }

  @GetMapping(path = "{customerEmail}")
  ResponseEntity<ListsDto> getListByEmail(@PathVariable String customerEmail) {
    return new ResponseEntity<>(
        listDtoMapper.toDto(listService.getCustomerByEmail(customerEmail)), HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<ListsDto> newList(@RequestBody ListsDto listDto) {
    com.mtg.mtgservice.model.Lists list;
    try {
      list = listService.createList(listDto);
    } catch (EntityNotFoundException e) {

      return new ResponseEntity<ListsDto>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(listDtoMapper.toDto(list), HttpStatus.CREATED);
  }

  @PutMapping(path = "{listName}")
  ResponseEntity<ListsDto> updateList(
      @PathVariable String listName, @RequestBody ListsDto listDto) {
    com.mtg.mtgservice.model.Lists list;
    try {
      list = listService.updateList(listDtoMapper.toEntity(listDto));
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(listDtoMapper.toDto(list), HttpStatus.OK);
  }
}
