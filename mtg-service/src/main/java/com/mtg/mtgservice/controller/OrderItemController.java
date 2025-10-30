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
@RequestMapping(path = "order-item",
  produces = MediaType.APPLICATION_JSON_VALUE,
  consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderItemController {

  private final OrderItemDtoMapper mapper;
  private final OrderItemService service;

  @GetMapping(consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<OrderItemDto>> getAll() {
    return new ResponseEntity<>(mapper.toDtoList(service.getAll()), HttpStatus.OK);
  }

  @GetMapping(path = "/{orderId}/{cardNumber}/{setName}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<OrderItemDto> getById(
      @PathVariable("orderId") Integer orderId,
      @PathVariable("cardNumber") Integer cardNumber,
      @PathVariable("setName") String setName) {
    OrderItem oi = service.getById(orderId, cardNumber, setName)
        .orElseThrow(() -> new RuntimeException("OrderItem not found"));
    return new ResponseEntity<>(mapper.toDto(oi), HttpStatus.OK);
  }

  @GetMapping(path = "/order/{orderId}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<OrderItemDto>> getByOrder(@PathVariable Integer orderId) {
    return new ResponseEntity<>(mapper.toDtoList(service.getByOrder(orderId)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderItemDto> create(@RequestBody OrderItemDto dto) {
    OrderItem saved = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{orderId}/{cardNumber}/{setName}")
  public ResponseEntity<OrderItemDto> upsert(
      @PathVariable("orderId") Integer orderId,
      @PathVariable("cardNumber") Integer cardNumber,
      @PathVariable("setName") String setName,
      @RequestBody OrderItemDto body) {

    body.setOrderId(orderId);
    body.setCardNumber(cardNumber);
    body.setSetName(setName);

    OrderItem saved = service.save(mapper.toEntity(body));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.OK);
  }
}
