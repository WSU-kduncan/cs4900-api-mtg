package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.MatchHistory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Integer> {
  List<MatchHistory> findByPlayerID(Integer playerID);

  List<MatchHistory> findByDeckName(String deckName);

  List<MatchHistory> findByPlayerIDAndDeckName(Integer playerID, String deckName);

  Optional<MatchHistory> findFirstByPlayerIDAndDeckNameAndPlayedAt(
      Integer playerID, String deckName, LocalDateTime playedAt);

    Optional<MatchHistory>
      findFirstByPlayerIDAndDeckNameAndWinnerNameAndFormatAndPlayerWinAndOpponentOneAndOpponentOneDeckAndOpponentTwoAndOpponentTwoDeckAndOpponentThreeAndOpponentThreeDeckAndResultAndPlayedAt(
        Integer playerID,
        String deckName,
        String winnerName,
        String format,
        Boolean playerWin,
        String opponentOne,
        String opponentOneDeck,
        String opponentTwo,
        String opponentTwoDeck,
        String opponentThree,
        String opponentThreeDeck,
        String result,
        LocalDateTime playedAt);
}
