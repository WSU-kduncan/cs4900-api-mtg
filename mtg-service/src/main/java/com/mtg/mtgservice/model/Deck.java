package com.mtg.mtgservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Deck")
public class Deck {

  @Id
  @Column(name = "DeckName", columnDefinition = "VARCHAR(64)", nullable = false)
  private String deckName;

  @Column(name = "Wins", columnDefinition = "int", nullable = false)
  private Integer wins;

  @Column(name = "Losses", columnDefinition = "int", nullable = false)
  private Integer losses;

  @Column(name = "Ties", columnDefinition = "int", nullable = false)
  private Integer ties;

  @Column(name = "CasualWins", columnDefinition = "int", nullable = false)
  private Integer casualWins;

  @Column(name = "CasualLosses", columnDefinition = "int", nullable = false)
  private Integer casualLosses;
}
