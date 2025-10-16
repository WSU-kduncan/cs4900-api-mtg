package com.mtgservice.mtg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "Orders")
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OrderID", nullable = false)
  Integer orderId;

  @Column(name = "OrderDate", nullable = false)
  Instant orderDate;

  @Column(name = "CustomerEmail", length = 128, nullable = false)
  String customerEmail;

  @Column(name = "EmployeeID", nullable = false)
  Integer employeeId;

  @Column(name = "OrderStatusTypeID", nullable = false)
  Byte orderStatusTypeId;

  @Column(name = "date_added")
  @CreationTimestamp(source = SourceType.DB)
  Instant dateAdded;

  @Column(name = "date_last_updated")
  @UpdateTimestamp(source = SourceType.DB)
  Instant dateUpdated;
}

