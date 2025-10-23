package com.mtg.mtgservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ListID", columnDefinition = "INT", nullable = false)
  Integer listId;

  @ManyToOne
  @JoinColumn(name = "CustomerEmail", nullable = false)
  Customer customerEmail;

  @Column(name = "ListName", columnDefinition = "VARCHAR(32)", nullable = false)
  String listName;
}
