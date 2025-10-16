package com.MTGService.MTG.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Worker")
public class Worker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EmployeeID", columnDefinition = "INT", nullable = false)
  Integer EmployeeID;

  @Column(name = "FirstName", columnDefinition = "VARCHAR(35)", nullable = false)
  String FirstName;

  @Column(name = "LastName", columnDefinition = "VARCHAR(35)", nullable = false)
  String LastName;

  @Column(name = "Role", columnDefinition = "CHAR(3)", nullable = false)
  String Role;

  @Column(name = "Email", columnDefinition = "VARCHAR(128)", nullable = false)
  String Email;
}
