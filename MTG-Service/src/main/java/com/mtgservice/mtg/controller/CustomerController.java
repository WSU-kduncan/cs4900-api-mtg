package com.mtgservice.mtg.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtgservice.mtg.dto.CustomerDto;
import com.mtgservice.mtg.mapper.CustomerDtoMapper;
import com.mtgservice.mtg.service.CustomerService;

import jakarta.validation.constraints.Email;

@RequiredArgsConstructor
@RestController
@RequestMapping(
    path = "customer",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

  private final CustomerDtoMapper customerDtoMapper;

  private final CustomerService customerService;

 // @GetMapping
 // ResponseEntity<List<CustomerDto>> getAllCustomers() {
 //   return new ResponseEntity<>(
 //       customerDtoMapper.toDtoList(customerService.getAllCustomers()), HttpStatus.OK);
 // }

  @GetMapping(path = "{email}")
  ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
    return new ResponseEntity<>(
        customerDtoMapper.toDto(customerService.getCustomerByEmail(email)), HttpStatus.OK);
  }
}