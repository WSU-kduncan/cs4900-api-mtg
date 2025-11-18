package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.OrderItemDto;
import com.mtg.mtgservice.mapper.OrderItemDtoMapper;
import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.service.OrderItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "order-item",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderItemController {

  private final OrderItemDtoMapper mapper;
  private final OrderItemService service;

  @GetMapping(consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<OrderItemDto>> getAll() {
    return new ResponseEntity<>(mapper.toDtoList(service.getAll()), HttpStatus.OK);
  }

  @GetMapping(path = "/{orderID}/{cardNumber}/{setName}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<OrderItemDto> getByID(
      @PathVariable Integer orderID,
      @PathVariable Integer cardNumber,
      @PathVariable String setName) {
    OrderItem oi = service
        .getByID(orderID, cardNumber, setName)
        .orElseThrow(() -> new RuntimeException("OrderItem not found"));
    return new ResponseEntity<>(mapper.toDto(oi), HttpStatus.OK);
  }

  @GetMapping(path = "/order/{orderID}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<OrderItemDto>> getByOrder(@PathVariable Integer orderID) {
    return new ResponseEntity<>(mapper.toDtoList(service.getByOrder(orderID)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderItemDto> create(@RequestBody OrderItemDto dto) {
    OrderItem saved = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{orderID}/{cardNumber}/{setName}")
  public ResponseEntity<OrderItemDto> upsert(
      @PathVariable Integer orderID,
      @PathVariable Integer cardNumber,
      @PathVariable String setName,
      @RequestBody OrderItemDto body) {

    body.setOrderID(orderID);
    body.setCardNumber(cardNumber);
    body.setSetName(setName);

    OrderItem saved = service.save(mapper.toEntity(body));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.OK);
  }
}
