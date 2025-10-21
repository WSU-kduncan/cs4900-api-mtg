package com.mtgservice.mtg.service;

import com.mtgservice.mtg.model.Customer;
import com.mtgservice.mtg.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService  {

  private final CustomerRepository customerRepository;

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerByEmail(String email) throws EntityNotFoundException {
    Optional<Customer> result = customerRepository.findByEmail(email);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("Customer (" + email + ") not found");
    }
    return result.get();
  }
}