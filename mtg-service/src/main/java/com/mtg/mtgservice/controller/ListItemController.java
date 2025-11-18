package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.ListItemDto;
import com.mtg.mtgservice.mapper.ListItemDtoMapper;
import com.mtg.mtgservice.model.ListItem;
import com.mtg.mtgservice.service.ListItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "/list-item",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class ListItemController {

  private final ListItemDtoMapper mapper;
  private final ListItemService service;

  // GET /list-item
  @GetMapping
  public ResponseEntity<List<ListItemDto>> getAll() {
    return ResponseEntity.ok(mapper.toDtoList(service.getAll()));
  }

  // GET /list-item/{listID}/{cardNumber}/{setName}
  @GetMapping("/{listID}/{cardNumber}/{setName}")
  public ResponseEntity<ListItemDto> getById(
      @PathVariable Integer listID,
      @PathVariable Integer cardNumber,
      @PathVariable String setName) {
    ListItem li = service
        .getById(listID, cardNumber, setName)
        .orElseThrow(() -> new RuntimeException("ListItem not found"));
    return ResponseEntity.ok(mapper.toDto(li));
  }

  // GET /list-item/list/{listID}
  @GetMapping("/list/{listID}")
  public ResponseEntity<List<ListItemDto>> getByList(@PathVariable Integer listID) {
    return ResponseEntity.ok(mapper.toDtoList(service.getByList(listID)));
  }

  // POST /list-item
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListItemDto> create(@RequestBody ListItemDto dto) {
    ListItem saved = service.saveFromDto(
        dto.getListID(),
        dto.getCardNumber(),
        dto.getSetName(),
        dto.getQuantityWanted()
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
  }

  // PUT /list-item/{listID}/{cardNumber}/{setName}
  @PutMapping(path = "/{listID}/{cardNumber}/{setName}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListItemDto> upsert(
      @PathVariable Integer listID,
      @PathVariable Integer cardNumber,
      @PathVariable String setName,
      @RequestBody ListItemDto body) {

    body.setListID(listID);
    body.setCardNumber(cardNumber);
    body.setSetName(setName);

    ListItem saved = service.save(mapper.toEntity(body));
    return ResponseEntity.ok(mapper.toDto(saved));
  }
}
