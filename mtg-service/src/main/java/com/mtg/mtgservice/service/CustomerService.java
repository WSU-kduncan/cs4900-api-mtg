package com.mtg.mtgservice.service;

import com.mtg.mtgservice.model.Customer;
import com.mtg.mtgservice.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mtg.mtgservice.dto.CustomerDto;
import com.mtg.mtgservice.mapper.CustomerDtoMapper;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerDtoMapper customerDtoMapper;

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerByEmail(String email) throws EntityNotFoundException {
    Optional<Customer> result = customerRepository.findByCustomerEmail(email);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("Customer (" + email + ") not found");
    }
    return result.get();
  }

  @Transactional
  public CustomerDto createCustomer(CustomerDto customerDto) {
    Optional<Customer> existing = customerRepository.findByCustomerEmail(customerDto.getCustomerEmail());
    if (existing.isPresent()) {
      throw new IllegalArgumentException(
          "Customer with email " + customerDto.getCustomerEmail() + " already exists.");
    }

    Customer newCustomer = customerDtoMapper.toEntity(customerDto);
    Customer savedCustomer = customerRepository.save(newCustomer);
    return customerDtoMapper.toDto(savedCustomer);
  }

  @Transactional
  public CustomerDto updateCustomer(String email, CustomerDto customerDto) {
    Customer existingCustomer = customerRepository.findByCustomerEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Customer (" + email + ") not found. Cannot update."));

    if (customerDto.getCustomerEmail() != null && !email.equals(customerDto.getCustomerEmail())) {
      throw new IllegalArgumentException("Cannot change customer email. Use POST to create a new customer.");
    }
    
    existingCustomer.setFirstName(customerDto.getFirstName());
    existingCustomer.setLastName(customerDto.getLastName());
    existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());

    Customer savedCustomer = customerRepository.save(existingCustomer);

    return customerDtoMapper.toDto(savedCustomer);
  }
}