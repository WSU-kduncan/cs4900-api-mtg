package com.mtg.mtgservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtg.mtgservice.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  Optional<Customer> findByCustomerEmail(String email);
}
