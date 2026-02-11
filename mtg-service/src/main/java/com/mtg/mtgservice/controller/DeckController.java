package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.DeckDto;
import com.mtg.mtgservice.mapper.DeckDtoMapper;
import com.mtg.mtgservice.model.Deck;
import com.mtg.mtgservice.service.DeckService;
import java.util.Objects;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "deck", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeckController {

  private final DeckDtoMapper mapper;
  private final DeckService service;

  @GetMapping
  public ResponseEntity<List<DeckDto>> getAll() {
    List<Deck> decks = service.getAll();
    return new ResponseEntity<>(mapper.toDtoList(decks), HttpStatus.OK);
  }

  @GetMapping(path = "/{deckName}")
  public ResponseEntity<DeckDto> getByID(@PathVariable @NonNull String deckName) {
    Deck deck = service.getByID(Objects.requireNonNull(deckName, "deckName"));
    return new ResponseEntity<>(mapper.toDto(deck), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DeckDto> create(@RequestBody @NonNull DeckDto dto) {
    Deck saved = service.create(Objects.requireNonNull(mapper.toEntity(dto), "deck"));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{deckName}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<DeckDto> update(
      @PathVariable @NonNull String deckName, @RequestBody @NonNull DeckDto dto) {
    Deck updated = service.update(Objects.requireNonNull(deckName, "deckName"), dto);
    return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{deckName}")
  public ResponseEntity<Void> delete(@PathVariable @NonNull String deckName) {
    service.delete(Objects.requireNonNull(deckName, "deckName"));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
