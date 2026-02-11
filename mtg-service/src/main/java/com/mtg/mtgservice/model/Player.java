package com.mtg.mtgservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Player")
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PlayerID", columnDefinition = "int", nullable = false)
  private Integer playerID;

  @Column(name = "PlayerName", columnDefinition = "VARCHAR(64)", nullable = false)
  private String playerName;

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
