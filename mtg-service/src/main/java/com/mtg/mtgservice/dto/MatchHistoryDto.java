package com.mtg.mtgservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchHistoryDto {
  Integer matchID;
  Integer playerID;
  String deckName;
  String winnerName;
  String format;
  Boolean playerWin;
  String opponentOne;
  String opponentOneDeck;
  String opponentTwo;
  String opponentTwoDeck;
  String opponentThree;
  String opponentThreeDeck;
  String result;
  LocalDateTime playedAt;
}
