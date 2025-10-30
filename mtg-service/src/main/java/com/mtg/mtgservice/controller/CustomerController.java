package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.CustomerDto;
import com.mtg.mtgservice.mapper.CustomerDtoMapper;
import com.mtg.mtgservice.service.CustomerService;
import jakarta.validation.Valid;
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
@RequestMapping(path = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

  private final CustomerDtoMapper customerDtoMapper;

  private final CustomerService customerService;

  @GetMapping
  ResponseEntity<List<CustomerDto>> getAllCustomers() {
    System.out.println("In getAllCustomers()");

    return new ResponseEntity<>(
        customerDtoMapper.toDtoList(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping(path = "/{email}")
  ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String customerEmail) {
    return new ResponseEntity<>(
        customerDtoMapper.toDto(customerService.getCustomerByEmail(customerEmail)), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
    CustomerDto createdCustomer = customerService.createCustomer(customerDto);
    return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
  }

  @PutMapping(path = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDto> updateCustomer(
      @PathVariable("email") String customerEmail, @Valid @RequestBody CustomerDto customerDto) {

    CustomerDto updatedCustomer = customerService.updateCustomer(customerEmail, customerDto);
    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
  }
}
