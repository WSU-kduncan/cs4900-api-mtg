package com.mtgservice.mtg.model.composite;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class CardID implements Serializable{

    @Column(name = "CardNumber", columnDefinition = "smallint", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cardNumber;

    @Column(name = "SetName", columnDefinition = "CHAR(3)", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String setName;
}