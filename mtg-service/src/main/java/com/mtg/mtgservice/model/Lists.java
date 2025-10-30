package com.mtg.mtgservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "List")
public class Lists {
    @Id
    @Column(name = "ListID", columnDefinition = "int", nullable = false)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Integer listID;

    @Column(name = "ListName", columnDefinition = "VARCHAR(32)", nullable = false)
    String listName;

    @JoinColumn(name = "CustomerEmail", columnDefinition = "VARCHAR(128)", nullable = false)
    @ManyToOne
    Customer customerEmail;
}
