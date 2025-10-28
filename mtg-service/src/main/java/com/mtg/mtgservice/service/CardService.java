package com.mtg.mtgservice.service;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.mtg.mtgservice.model.composite.CardId;
import com.mtg.mtgservice.dto.CardDto;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.composite.CardId;
import com.mtg.mtgservice.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {
  private final CardRepository repo;
  private final com.mtg.mtgservice.mapper.CardDtoMapper mapper;

  public List<Card> getAll() { return repo.findAll(); }

  public Optional<Card> getById(Integer cardNumber, String setName) {
    return repo.findById(new CardId(cardNumber, setName));
  }

  public Card create(Card toSave) {
    CardId id = toSave.getId();
    if (repo.existsById(id)) {
      throw new IllegalStateException(
          "Card already exists: " + id.getCardNumber() + "/" + id.getSetName());
    }
    return repo.save(toSave);
  }

  public Card update(Integer cardNumber, String setName, CardDto dto) {
    CardId id = new CardId(cardNumber, setName);
    Card existing = repo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
    mapper.applyUpdates(existing, dto);
    return repo.save(existing);
  }

  public List<Card> searchByName(String q) {
    return repo.findByCardNameContainingIgnoreCase(q);
  }
}