package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.OrderStatusTypeDto;
import com.mtg.mtgservice.mapper.OrderStatusTypeDtoMapper;
import com.mtg.mtgservice.model.OrderStatusType;
import com.mtg.mtgservice.service.OrderStatusTypeService;
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
    path = "orderstatustype",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderStatusTypeController {
  private final OrderStatusTypeDtoMapper orderStatusTypeDtoMapper;

  private final OrderStatusTypeService orderStatusTypeService;

  @GetMapping
  ResponseEntity<List<OrderStatusTypeDto>> getAllOrderStatusTypes() {
    return new ResponseEntity<>(
        orderStatusTypeDtoMapper.toDtoList(orderStatusTypeService.getAllOrderStatusTypes()),
        HttpStatus.OK);
  }

  @GetMapping(path = "{statusTypeID}")
  ResponseEntity<OrderStatusTypeDto> getOrderStatusTypeByID(@PathVariable Integer statusTypeID) {
    return new ResponseEntity<>(
        orderStatusTypeDtoMapper.toDto(orderStatusTypeService.getOrderStatusTypeByID(statusTypeID)),
        HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<OrderStatusTypeDto> newOrderStatusType(
      @RequestBody OrderStatusTypeDto orderStatusTypeDto) {
    OrderStatusType orderStatusType;
    try {

      orderStatusType = orderStatusTypeService.createOrderStatusType(orderStatusTypeDto);

    } catch (EntityNotFoundException e) {

      return new ResponseEntity<OrderStatusTypeDto>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(
        orderStatusTypeDtoMapper.toDto(orderStatusType), HttpStatus.CREATED);
  }

  @PutMapping(path = "{statusDescription}")
  ResponseEntity<OrderStatusTypeDto> updateOrderStatusType(
      @PathVariable String statusDescription,
      @RequestBody OrderStatusTypeDto orderStatusDescriptionDto) {
    OrderStatusType orderStatusDescription;
    try {
      orderStatusDescription = orderStatusTypeService.updateOrderStatusType(
          orderStatusTypeDtoMapper.toEntity(orderStatusDescriptionDto));
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(
        orderStatusTypeDtoMapper.toDto(orderStatusDescription), HttpStatus.OK);
  }
}
