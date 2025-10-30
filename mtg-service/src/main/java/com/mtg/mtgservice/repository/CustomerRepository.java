package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

  Optional<Customer> findByCustomerEmail(String email);
}
