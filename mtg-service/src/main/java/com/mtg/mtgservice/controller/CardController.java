package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.CardDto;
import com.mtg.mtgservice.mapper.CardDtoMapper;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "card",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

  private final CardDtoMapper mapper;
  private final CardService service;

  @GetMapping(consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<CardDto>> getAll() {
    List<Card> cards = service.getAll();
    return new ResponseEntity<>(mapper.toDtoList(cards), HttpStatus.OK);
  }

  @GetMapping(path = "/{cardNumber}/{setName}", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<CardDto> getById(
      @PathVariable("cardNumber") Integer cardNumber, @PathVariable("setName") String setName) {
    Card card = service
        .getById(cardNumber, setName)
        .orElseThrow(() -> new RuntimeException("Card not found"));
    return new ResponseEntity<>(mapper.toDto(card), HttpStatus.OK);
  }

  @GetMapping(path = "/search", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<CardDto>> search(@RequestParam String q) {
    return new ResponseEntity<>(mapper.toDtoList(service.searchByName(q)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CardDto> create(@RequestBody CardDto dto) {
    Card toSave = mapper.toEntity(dto);
    Card saved = service.create(toSave);
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{cardNumber}/{setName}")
  public ResponseEntity<CardDto> update(
      @PathVariable Integer cardNumber, @PathVariable String setName, @RequestBody CardDto dto) {
    Card updated = service.update(cardNumber, setName, dto);
    return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
  }
}
