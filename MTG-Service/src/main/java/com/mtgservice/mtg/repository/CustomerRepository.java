package com.mtgservice.mtg.repository;
import com.mtgservice.mtg.model.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

Optional<Customer> findByCustomerEmail(String email);

}
