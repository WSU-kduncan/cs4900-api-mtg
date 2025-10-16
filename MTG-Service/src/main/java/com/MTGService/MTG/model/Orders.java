package com.MTGService.MTG.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OrderID", columnDefinition = "INT", nullable = false)
  Integer OrderID;

  @Column(name = "OrderDate", columnDefinition = "DATETIME", nullable = false)
  LocalDateTime OrderDate;

  @ManyToOne
  @JoinColumn(name = "OrderStatusTypeID", nullable = false)
  OrderStatusType OrderStatusTypeID;

  @ManyToOne
  @JoinColumn(name = "CustomerEmail", nullable = false)
  Customer CustomerEmail;

  @ManyToOne
  @JoinColumn(name = "EmployeeID", nullable = false)
  Worker EmployeeID;
}
