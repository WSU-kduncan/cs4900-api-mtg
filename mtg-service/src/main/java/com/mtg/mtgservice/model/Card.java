package com.mtg.mtgservice.model;

import com.mtg.mtgservice.model.composite.CardID;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Card")

public class Card {
    @EmbeddedId
    CardID id;
    
    @Column(name = "CardCondition", columnDefinition = "VARCHAR(14)", nullable = false)
    String cardCondition;

    @Column(name = "CardName", columnDefinition = "VARCHAR(128)", nullable = false)
    String cardName;

    @Column(name = "CardDescription", columnDefinition = "text", nullable = false)
    String cardDescription;

    @Column(name = "ManaValue", columnDefinition = "varchar(10)", nullable = false)
    String manaValue;

    @Column(name = "CardType", columnDefinition = "VARCHAR(64)", nullable = false)
    String cardType;

    @Column(name = "Price", columnDefinition = "decimal(10,2)", nullable = false)
    double price;

    @Column(name = "Stock", columnDefinition = "int", nullable = false)
    Integer stock;
}

