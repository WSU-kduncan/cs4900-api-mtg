package com.MTGService.MTG.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Customer")
public class Customer {

  @Id
  @Column(name = "CustomerEmail", columnDefinition = "VARCHAR(128)", nullable = false)
  String CustomerEmail;

  @Column(name = "FirstName", columnDefinition = "VARCHAR(35)", nullable = false)
  String FirstName;

  @Column(name = "LastName", columnDefinition = "VARCHAR(35)", nullable = false)
  String LastName;

  @Column(name = "PhoneNumber", columnDefinition = "CHAR(10)", nullable = false)
  String PhoneNumber;
}
