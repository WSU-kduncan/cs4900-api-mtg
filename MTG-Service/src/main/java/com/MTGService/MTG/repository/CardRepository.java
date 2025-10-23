package com.MTGService.MTG.repository;

import com.MTGService.MTG.model.Card;
import com.MTGService.MTG.model.Composite.CardId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, CardId> {
  List<Card> findByCardNameContainingIgnoreCase(String namePart);
}