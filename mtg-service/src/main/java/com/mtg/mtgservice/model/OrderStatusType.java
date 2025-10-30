package com.mtg.mtgservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderStatusType")
public class OrderStatusType {
  @Id
  @Column(name = "StatusTypeID", columnDefinition = "tinyint", nullable = false)
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  Integer statusTypeID;

  @Column(name = "StatusDescription", columnDefinition = "VARCHAR(255)", nullable = false)
  String statusDescription;
}
