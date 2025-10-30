package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.Lists;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListsRepository extends JpaRepository<Lists, Integer> {

  Optional<Lists> findByCustomerEmail_CustomerEmail(String customerEmail);
}
