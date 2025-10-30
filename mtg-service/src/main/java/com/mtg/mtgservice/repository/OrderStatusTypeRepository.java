package com.mtg.mtgservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtg.mtgservice.model.OrderStatusType;

@Repository
public interface OrderStatusTypeRepository extends JpaRepository<OrderStatusType, Integer> {
    
}
