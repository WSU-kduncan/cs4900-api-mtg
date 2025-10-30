package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.ListItemDto;
import com.mtg.mtgservice.mapper.ListItemDtoMapper;
import com.mtg.mtgservice.model.ListItem;
import com.mtg.mtgservice.service.ListItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "list-item",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class ListItemController {

  private final ListItemDtoMapper mapper;
  private final ListItemService service;

  @GetMapping(consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<ListItemDto>> getAll() {
    return new ResponseEntity<>(mapper.toDtoList(service.getAll()), HttpStatus.OK);
  }

  @GetMapping(path = "/{listId}/{cardNumber}/{setName}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<ListItemDto> getById(
      @PathVariable Integer listId,
      @PathVariable Integer cardNumber,
      @PathVariable String setName) {
    ListItem li = service
        .getById(listId, cardNumber, setName)
        .orElseThrow(() -> new RuntimeException("ListItem not found"));
    return new ResponseEntity<>(mapper.toDto(li), HttpStatus.OK);
  }

  @GetMapping(path = "/list/{listId}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<ListItemDto>> getByList(@PathVariable Integer listId) {
    return new ResponseEntity<>(mapper.toDtoList(service.getByList(listId)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ListItemDto> create(@RequestBody ListItemDto dto) {
    ListItem saved = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{listId}/{cardNumber}/{setName}")
  public ResponseEntity<ListItemDto> upsert(
      @PathVariable Integer listId,
      @PathVariable Integer cardNumber,
      @PathVariable String setName,
      @RequestBody ListItemDto body) {

    // trust path for id parts
    body.setListId(listId);
    body.setCardNumber(cardNumber);
    body.setSetName(setName);

    ListItem saved = service.save(mapper.toEntity(body));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.OK);
  }
}
