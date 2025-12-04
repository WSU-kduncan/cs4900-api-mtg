package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.OrderItemDto;
import com.mtg.mtgservice.dto.OrdersDto;
import com.mtg.mtgservice.mapper.OrderItemDtoMapper;
import com.mtg.mtgservice.mapper.OrdersDtoMapper;
import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.service.OrderItemService;
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
  private final OrderItemService orderItemService;
  private final OrderItemDtoMapper orderItemDtoMapper;

  /* ------- READ ------- */
  @GetMapping(consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<OrdersDto>> getAllOrders() {
    List<OrdersDto> orderDtos = ordersDtoMapper.toDtoList(ordersService.getAllOrders());

    // Fetch and attach items for each order
    orderDtos.forEach(orderDto -> {
      List<OrderItem> items = orderItemService.findByOrderID(orderDto.getOrderID());
      orderDto.setOrderItems(orderItemDtoMapper.toDtoList(items));
    });

    return ResponseEntity.ok(orderDtos);
  }

  @GetMapping(path = "{orderID}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<OrdersDto> getOrderById(@PathVariable Integer orderID) {
    return ResponseEntity.ok(ordersDtoMapper.toDto(ordersService.getOrderByID(orderID)));
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

  /* ------- ADD ITEM TO ORDER ------- */
  @PostMapping(path = "{orderID}/items", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<OrderItemDto> addItemToOrder(
      @PathVariable Integer orderID, @RequestBody OrderItemDto itemDto) {

    itemDto.setOrderID(orderID);
    OrderItem saved = orderItemService.save(orderItemDtoMapper.toEntity(itemDto));

    return ResponseEntity.status(HttpStatus.CREATED).body(orderItemDtoMapper.toDto(saved));
  }

  /* ------- DELETE ITEM FROM ORDER ------- */
  @DeleteMapping(path = "{orderID}/items/{cardNumber}/{setName}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<Void> deleteItemFromOrder(
      @PathVariable Integer orderID,
      @PathVariable Integer cardNumber,
      @PathVariable String setName) {

    orderItemService.delete(orderID, cardNumber, setName);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}