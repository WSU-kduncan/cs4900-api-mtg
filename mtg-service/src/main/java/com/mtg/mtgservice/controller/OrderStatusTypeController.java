package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.OrderStatusTypeDto;
import com.mtg.mtgservice.mapper.OrderStatusTypeDtoMapper;
import com.mtg.mtgservice.model.OrderStatusType;
import com.mtg.mtgservice.service.OrderStatusTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "order-status-type",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class OrderStatusTypeController {

    private final OrderStatusTypeService service;
    private final OrderStatusTypeDtoMapper mapper;

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<OrderStatusTypeDto>> getAll() {
        return new ResponseEntity<>(mapper.toDtoList(service.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<OrderStatusTypeDto> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(mapper.toDto(service.getById(id)), HttpStatus.OK);
    }

    @GetMapping(path = "/search", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<OrderStatusTypeDto>> search(@RequestParam("q") String q) {
        return new ResponseEntity<>(mapper.toDtoList(service.search(q)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderStatusTypeDto> create(@RequestBody OrderStatusTypeDto dto) {
        var saved = service.create(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStatusTypeDto> update(
            @PathVariable Integer id,
            @RequestBody OrderStatusTypeDto dto) {
        var updated = service.update(id, mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
    }
}
