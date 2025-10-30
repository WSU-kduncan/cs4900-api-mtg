package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.OrdersDto;
import com.mtg.mtgservice.mapper.OrdersDtoMapper;
import com.mtg.mtgservice.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "orders",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersDtoMapper ordersDtoMapper;
    private final OrdersService ordersService;

    /* ------- READ ------- */
    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        return ResponseEntity.ok(ordersDtoMapper.toDtoList(ordersService.getAllOrders()));
    }

    @GetMapping("{orderID}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable Integer orderID) {
        return ResponseEntity.ok(ordersDtoMapper.toDto(ordersService.getOrderById(orderID)));
    }

    /* ------- CREATE ------- */
    @PostMapping
    public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ordersDtoMapper.toDto(ordersService.createOrder(ordersDtoMapper.toEntity(dto))));
    }

    /* ------- UPDATE ------- */
    @PutMapping("{orderID}")
    public ResponseEntity<OrdersDto> updateOrder(@PathVariable Integer orderID,
                                                 @RequestBody OrdersDto dto) {
        return ResponseEntity.ok(ordersDtoMapper.toDto(ordersService.updateOrder(orderID, dto)));
    }
}