

package com.MTGService.MTG.controller;

import com.MTGService.MTG.dto.CardDto;
import com.MTGService.MTG.mapper.CardDtoMapper;
import com.MTGService.MTG.model.Card;
import com.MTGService.MTG.service.CardService;
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
  consumes = MediaType.APPLICATION_JSON_VALUE
)
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
      @PathVariable Integer cardNumber,
      @PathVariable String setName) {
    Card card = service.getById(cardNumber, setName)
        .orElseThrow(() -> new RuntimeException("Card not found"));
    return new ResponseEntity<>(mapper.toDto(card), HttpStatus.OK);
  }

  @GetMapping(path = "/search", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<List<CardDto>> search(@RequestParam String q) {
    return new ResponseEntity<>(mapper.toDtoList(service.searchByName(q)), HttpStatus.OK);
  }
}
