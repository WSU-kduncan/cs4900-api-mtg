package com.mtgservice.mtg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "List")
public class List {
  @Id
  @Column(name = "ListID", columnDefinition = "int", nullable = false)
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  Integer listId;

  @Column(name = "ListName", columnDefinition = "VARCHAR(32)", nullable = false)
  String listName;

  @JoinColumn(name = "CustomerEmail", columnDefinition = "VARCHAR(128)", nullable = false)
  @ManyToOne
  Customer customerEmail;
}
