package com.mtg.mtgservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeckDto {
  String deckName;
  Integer wins;
  Integer losses;
  Integer ties;
  Integer casualWins;
  Integer casualLosses;
}
