package com.mtgservice.mtg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

@Data
@Entity
@Table(name = "Orders")
public class Orders {
  @Id
  @Column(name = "OrderID", columnDefinition = "int", nullable = false)
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  Integer orderId;

  @Column(name = "OrderDate", nullable = false)
  @CreationTimestamp(source = SourceType.DB)
  Instant orderDate;

  @JoinColumn(name = "OrderStatusTypeID", nullable = false)
  @ManyToOne
  OrderStatusType statusTypeId;

  @JoinColumn(name = "CustomerEmail", nullable = false)
  @ManyToOne
  Customer customerEmail;

  @JoinColumn(name = "EmployeeID", nullable = false)
  @ManyToOne
  Worker employeeId;
}
