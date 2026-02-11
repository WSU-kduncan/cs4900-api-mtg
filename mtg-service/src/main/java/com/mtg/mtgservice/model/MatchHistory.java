package com.mtg.mtgservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "MatchHistory")
public class MatchHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MatchID", columnDefinition = "int", nullable = false)
  private Integer matchID;

  @Column(name = "PlayerID", columnDefinition = "int", nullable = false)
  private Integer playerID;

  @Column(name = "DeckName", columnDefinition = "VARCHAR(64)", nullable = false)
  private String deckName;

  @Column(name = "WinnerName", columnDefinition = "VARCHAR(64)", nullable = false)
  private String winnerName;

  @Column(name = "Format", columnDefinition = "VARCHAR(8)", nullable = false)
  private String format;

  @Column(name = "PlayerWin", columnDefinition = "BOOLEAN", nullable = false)
  private Boolean playerWin;

  @Column(name = "OpponentOne", columnDefinition = "VARCHAR(64)", nullable = false)
  private String opponentOne;

  @Column(name = "OpponentOneDeck", columnDefinition = "VARCHAR(64)", nullable = false)
  private String opponentOneDeck;

  @Column(name = "OpponentTwo", columnDefinition = "VARCHAR(64)", nullable = false)
  private String opponentTwo;

  @Column(name = "OpponentTwoDeck", columnDefinition = "VARCHAR(64)", nullable = false)
  private String opponentTwoDeck;

  @Column(name = "OpponentThree", columnDefinition = "VARCHAR(64)", nullable = false)
  private String opponentThree;

  @Column(name = "OpponentThreeDeck", columnDefinition = "VARCHAR(64)", nullable = false)
  private String opponentThreeDeck;

  @Column(name = "Result", columnDefinition = "VARCHAR(8)", nullable = false)
  private String result;

  @Column(name = "PlayedAt", columnDefinition = "DATETIME", nullable = false)
  private LocalDateTime playedAt;
}
