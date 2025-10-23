package com.mtg.mtgservice.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.composite.CardId;
import com.mtg.mtgservice.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {
  private final CardRepository repo;

  public List<Card> getAll() { return repo.findAll(); }

  public Optional<Card> getById(Integer cardNumber, String setName) {
    return repo.findById(new CardId(cardNumber, setName));
  }

  public List<Card> searchByName(String q) {
    return repo.findByCardNameContainingIgnoreCase(q);
  }
}