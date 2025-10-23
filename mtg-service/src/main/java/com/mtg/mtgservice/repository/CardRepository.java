package com.mtg.mtgservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.composite.CardId;

public interface CardRepository extends JpaRepository<Card, CardId> {
  List<Card> findByCardNameContainingIgnoreCase(String namePart);
}