package com.mtgservice.mtg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {

  @Id
  @Column(name = "OrderID", nullable = false)
  Integer orderId;

  @Id
  @Column(name = "CardNumber", nullable = false)
  Short cardNumber;

  @Id
  @Column(name = "SetName", length = 3, nullable = false)
  String setName;

  @Column(name = "Quantity", nullable = false)
  Short quantity;

  @Column(name = "Price", precision = 10, scale = 2, nullable = false)
  BigDecimal price;


}
