package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.OrderStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusTypeRepository extends JpaRepository<OrderStatusType, Integer> {}
