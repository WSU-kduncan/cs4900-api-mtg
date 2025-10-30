package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.OrderStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; 

public interface OrderStatusTypeRepository extends JpaRepository<OrderStatusType, Integer> {
    List<OrderStatusType> findByStatusDescriptionContainingIgnoreCase(String q);
}
