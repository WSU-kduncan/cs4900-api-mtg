package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.OrdersDto;
import com.mtg.mtgservice.mapper.OrdersDtoMapper;
import com.mtg.mtgservice.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://127.0.0.1:4200", "http://localhost:4200"})
@RestController
@RequestMapping(path = "orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrdersController {

  private final OrdersDtoMapper ordersDtoMapper;
  private final OrdersService ordersService;

  /* ------- READ ------- */
  @GetMapping(consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<OrdersDto>> getAllOrders() {
    return ResponseEntity.ok(ordersDtoMapper.toDtoList(ordersService.getAllOrders()));
  }

  @GetMapping(path = "{orderID}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<OrdersDto> getOrderById(@PathVariable Integer orderID) {
    return ResponseEntity.ok(ordersDtoMapper.toDto(ordersService.getOrderById(orderID)));
  }

  /* ------- CREATE ------- */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ordersDtoMapper.toDto(ordersService.createOrder(ordersDtoMapper.toEntity(dto))));
  }

  /* ------- UPDATE ------- */
  @PutMapping(path = "{orderID}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrdersDto> updateOrder(
      @PathVariable Integer orderID, @RequestBody OrdersDto dto) {
    return ResponseEntity.ok(ordersDtoMapper.toDto(ordersService.updateOrder(orderID, dto)));
  }

  /* ------- DELETE ------- */
  @DeleteMapping(path = "{orderID}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderID) {
    ordersService.deleteOrder(orderID);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}