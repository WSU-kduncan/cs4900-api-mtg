package com.MTGService.MTG.service;

import com.MTGService.MTG.model.Card;
import com.MTGService.MTG.model.Composite.CardId;
import com.MTGService.MTG.repository.CardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
