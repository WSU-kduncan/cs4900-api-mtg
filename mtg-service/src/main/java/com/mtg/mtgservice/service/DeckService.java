package com.mtg.mtgservice.service;

import com.mtg.mtgservice.dto.DeckDto;
import com.mtg.mtgservice.model.Deck;
import com.mtg.mtgservice.repository.DeckRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeckService {

  private final DeckRepository deckRepository;

  public List<Deck> getAll() {
    return deckRepository.findAll();
  }

  public Deck getByID(@NonNull String deckName) {
    Objects.requireNonNull(deckName, "deckName");
    return deckRepository
        .findById(deckName)
        .orElseThrow(() -> new EntityNotFoundException("Deck " + deckName + " not found"));
  }

  public Deck create(@NonNull Deck deck) {
    return deckRepository.save(Objects.requireNonNull(deck, "deck"));
  }

  @Transactional
  public Deck update(@NonNull String deckName, DeckDto dto) {
    Objects.requireNonNull(deckName, "deckName");
    Deck existing = getByID(deckName);
    existing.setWins(dto.getWins());
    existing.setLosses(dto.getLosses());
    existing.setTies(dto.getTies());
    existing.setCasualWins(dto.getCasualWins());
    existing.setCasualLosses(dto.getCasualLosses());
    return deckRepository.save(existing);
  }

  @Transactional
  public void delete(@NonNull String deckName) {
    Objects.requireNonNull(deckName, "deckName");
    if (!deckRepository.existsById(deckName)) {
      throw new EntityNotFoundException("Deck " + deckName + " not found");
    }
    deckRepository.deleteById(deckName);
  }
}
