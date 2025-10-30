package com.mtg.mtgservice.model;

import com.mtg.mtgservice.model.composite.OrderItemID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {

  @EmbeddedId
  OrderItemID id;

  @Column(name = "Quantity", columnDefinition = "smallint", nullable = false)
  int quantity;

  @Column(name = "Price", columnDefinition = "decimal(10,2)", nullable = false)
  double price;
}
