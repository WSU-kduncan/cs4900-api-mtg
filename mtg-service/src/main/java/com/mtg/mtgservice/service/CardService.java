package com.mtg.mtgservice.service;

import com.mtg.mtgservice.dto.CardDto;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.composite.CardID;
import com.mtg.mtgservice.repository.CardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CardService {
  private final CardRepository repo;
  private final com.mtg.mtgservice.mapper.CardDtoMapper mapper;

  public List<Card> getAll() {
    return repo.findAll();
  }

  public Optional<Card> getByID(Integer cardNumber, String setName) {
    return repo.findById(new CardID(cardNumber, setName));
  }

  public Card create(Card toSave) {
    CardID ID = toSave.getID();
    if (repo.existsById(ID)) {
      throw new IllegalStateException(
          "Card already exists: " + ID.getCardNumber() + "/" + ID.getSetName());
    }
    return repo.save(toSave);
  }

  public Card update(Integer cardNumber, String setName, CardDto dto) {
    CardID ID = new CardID(cardNumber, setName);
    Card existing = repo.findById(ID)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
    mapper.applyUpdates(existing, dto);
    return repo.save(existing);
  }

  public List<Card> searchByName(String q) {
    return repo.findByCardNameContainingIgnoreCase(q);
  }
}
