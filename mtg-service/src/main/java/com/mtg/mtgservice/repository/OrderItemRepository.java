package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.model.composite.OrderItemID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemID> {
  @Query("SELECT oi FROM OrderItem oi WHERE oi.ID.orderID.orderID = :orderID")
  List<OrderItem> findByIDOrderIDOrderID(@Param("orderID") Integer orderID);
}
