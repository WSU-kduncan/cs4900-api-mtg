package com.MTGService.MTG.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderStatusType")
public class OrderStatusType {

  @Id
  @Column(name = "StatusTypeID", columnDefinition = "TINYINT", nullable = false)
  Integer StatusTypeID;

  @Column(name = "StatusDescription", columnDefinition = "VARCHAR(255)", nullable = false)
  String StatusDescription;
}
