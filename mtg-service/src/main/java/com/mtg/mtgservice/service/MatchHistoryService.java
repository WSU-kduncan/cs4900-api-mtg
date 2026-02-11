package com.mtg.mtgservice.service;

import com.mtg.mtgservice.dto.MatchHistoryDto;
import com.mtg.mtgservice.model.Deck;
import com.mtg.mtgservice.model.MatchHistory;
import com.mtg.mtgservice.repository.DeckRepository;
import com.mtg.mtgservice.repository.MatchHistoryRepository;
import com.mtg.mtgservice.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchHistoryService {

  private final MatchHistoryRepository matchHistoryRepository;
  private final DeckRepository deckRepository;
  private final PlayerRepository playerRepository;

  public List<MatchHistory> getAll() {
    return matchHistoryRepository.findAll();
  }

  public MatchHistory getByID(@NonNull Integer matchID) {
    Objects.requireNonNull(matchID, "matchID");
    return matchHistoryRepository
        .findById(matchID)
        .orElseThrow(() -> new EntityNotFoundException("Match " + matchID + " not found"));
  }

  public List<MatchHistory> getByPlayerID(@NonNull Integer playerID) {
    Objects.requireNonNull(playerID, "playerID");
    return matchHistoryRepository.findByPlayerID(playerID);
  }

  public List<MatchHistory> getByDeckName(@NonNull String deckName) {
    Objects.requireNonNull(deckName, "deckName");
    return matchHistoryRepository.findByDeckName(deckName);
  }

  public List<MatchHistory> getByPlayerAndDeck(@NonNull Integer playerID, @NonNull String deckName) {
    Objects.requireNonNull(playerID, "playerID");
    Objects.requireNonNull(deckName, "deckName");
    return matchHistoryRepository.findByPlayerIDAndDeckName(playerID, deckName);
  }

  @Transactional
  public MatchHistory create(@NonNull MatchHistory matchHistory) {
    Objects.requireNonNull(matchHistory, "matchHistory");
    Integer matchId = matchHistory.getMatchID();
    if (matchId != null && matchHistoryRepository.existsById(matchId)) {
      throw new IllegalArgumentException("Match " + matchId + " already exists");
    }
    Integer playerId = matchHistory.getPlayerID();
    String deckName = matchHistory.getDeckName();
    if (playerId != null && deckName != null) {
      if (matchHistory.getPlayedAt() == null) {
        throw new IllegalArgumentException("playedAt is required");
      }
      matchHistoryRepository
          .findFirstByPlayerIDAndDeckNameAndPlayedAt(playerId, deckName, matchHistory.getPlayedAt())
          .ifPresent(
              existing -> {
                throw new IllegalArgumentException(
                    "Duplicate match for player/deck/time: " + existing.getMatchID());
              });
      matchHistoryRepository
          .findFirstByPlayerIDAndDeckNameAndWinnerNameAndFormatAndPlayerWinAndOpponentOneAndOpponentOneDeckAndOpponentTwoAndOpponentTwoDeckAndOpponentThreeAndOpponentThreeDeckAndResultAndPlayedAt(
              playerId,
              deckName,
              matchHistory.getWinnerName(),
              matchHistory.getFormat(),
              matchHistory.getPlayerWin(),
              matchHistory.getOpponentOne(),
              matchHistory.getOpponentOneDeck(),
              matchHistory.getOpponentTwo(),
              matchHistory.getOpponentTwoDeck(),
              matchHistory.getOpponentThree(),
              matchHistory.getOpponentThreeDeck(),
              matchHistory.getResult(),
              matchHistory.getPlayedAt())
          .ifPresent(
              existing -> {
                throw new IllegalArgumentException(
                    "Duplicate match payload: " + existing.getMatchID());
              });
    }
    validateOpponent(matchHistory.getWinnerName());
    validateOpponent(matchHistory.getOpponentOne());
    validateOpponent(matchHistory.getOpponentTwo());
    validateOpponent(matchHistory.getOpponentThree());
    validateResult(matchHistory.getResult());
    validateDeckName(matchHistory.getOpponentOneDeck());
    validateDeckName(matchHistory.getOpponentTwoDeck());
    validateDeckName(matchHistory.getOpponentThreeDeck());
    MatchHistory saved = matchHistoryRepository.save(matchHistory);
    Deck deck = getDeckOrThrow(matchHistory.getDeckName());
    applyDeckResult(deck, matchHistory.getResult(), matchHistory.getFormat(), 1);
    deckRepository.save(deck);
    return saved;
  }

  @Transactional
  public MatchHistory update(@NonNull Integer matchID, MatchHistoryDto dto) {
    Objects.requireNonNull(matchID, "matchID");
    MatchHistory existing = getByID(matchID);
    Deck oldDeck = getDeckOrThrow(existing.getDeckName());
    applyDeckResult(oldDeck, existing.getResult(), existing.getFormat(), -1);
    existing.setPlayerID(dto.getPlayerID());
    existing.setDeckName(dto.getDeckName());
    validateOpponent(dto.getWinnerName());
    existing.setWinnerName(dto.getWinnerName());
    existing.setFormat(dto.getFormat());
    existing.setPlayerWin(dto.getPlayerWin());
    validateOpponent(dto.getOpponentOne());
    validateOpponent(dto.getOpponentTwo());
    validateOpponent(dto.getOpponentThree());
    validateResult(dto.getResult());
    validateDeckName(dto.getOpponentOneDeck());
    validateDeckName(dto.getOpponentTwoDeck());
    validateDeckName(dto.getOpponentThreeDeck());
    existing.setOpponentOne(dto.getOpponentOne());
    existing.setOpponentOneDeck(dto.getOpponentOneDeck());
    existing.setOpponentTwo(dto.getOpponentTwo());
    existing.setOpponentTwoDeck(dto.getOpponentTwoDeck());
    existing.setOpponentThree(dto.getOpponentThree());
    existing.setOpponentThreeDeck(dto.getOpponentThreeDeck());
    existing.setResult(dto.getResult());
    existing.setPlayedAt(dto.getPlayedAt());
    Deck newDeck = getDeckOrThrow(dto.getDeckName());
    applyDeckResult(newDeck, dto.getResult(), dto.getFormat(), 1);
    deckRepository.save(oldDeck);
    if (!Objects.equals(oldDeck.getDeckName(), newDeck.getDeckName())) {
      deckRepository.save(newDeck);
    }
    return matchHistoryRepository.save(existing);
  }

  @Transactional
  public void delete(@NonNull Integer matchID) {
    Objects.requireNonNull(matchID, "matchID");
    MatchHistory existing = getByID(matchID);
    Deck deck = getDeckOrThrow(existing.getDeckName());
    applyDeckResult(deck, existing.getResult(), existing.getFormat(), -1);
    deckRepository.save(deck);
    matchHistoryRepository.deleteById(matchID);
  }

  private void validateOpponent(String opponentName) {
    Objects.requireNonNull(opponentName, "opponentName");
    if (!playerRepository.existsByPlayerName(opponentName)) {
      throw new EntityNotFoundException("Player " + opponentName + " not found");
    }
  }

  private void validateDeckName(String deckName) {
    Objects.requireNonNull(deckName, "deckName");
    if (!deckRepository.existsById(deckName)) {
      throw new EntityNotFoundException("Deck " + deckName + " not found");
    }
  }

  private void validateResult(String result) {
    Objects.requireNonNull(result, "result");
    String normalizedResult = result.trim().toUpperCase();
    if (!"WIN".equals(normalizedResult) && !"TIE".equals(normalizedResult)) {
      throw new IllegalArgumentException("Result must be WIN or TIE");
    }
  }

  @NonNull
  private Deck getDeckOrThrow(String deckName) {
    Objects.requireNonNull(deckName, "deckName");
    return Objects.requireNonNull(
      deckRepository
        .findById(deckName)
        .orElseThrow(() -> new EntityNotFoundException("Deck " + deckName + " not found")),
      "deck");
  }

  private void applyDeckResult(Deck deck, String result, String format, int delta) {
    if (deck == null || result == null) {
      return;
    }
    boolean isCasual = format != null && "CASUAL".equalsIgnoreCase(format);
    String normalizedResult = result.trim().toUpperCase();
    switch (normalizedResult) {
      case "WIN" -> {
        if (isCasual) {
          deck.setCasualWins(deck.getCasualWins() + delta);
        } else {
          deck.setWins(deck.getWins() + delta);
        }
      }
      case "LOSS" -> {
        if (isCasual) {
          deck.setCasualLosses(deck.getCasualLosses() + delta);
        } else {
          deck.setLosses(deck.getLosses() + delta);
        }
      }
      case "TIE" -> {
        if (!isCasual) {
          deck.setTies(deck.getTies() + delta);
        }
      }
      default -> {}
    }
  }
}
