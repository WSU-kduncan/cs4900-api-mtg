package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.model.composite.OrderItemID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemID> {
  List<OrderItem> findByIDOrderIDOrderID(Integer orderID);
}
