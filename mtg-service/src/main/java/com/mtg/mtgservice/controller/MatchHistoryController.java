package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.MatchHistoryDto;
import com.mtg.mtgservice.mapper.MatchHistoryDtoMapper;
import com.mtg.mtgservice.model.MatchHistory;
import com.mtg.mtgservice.service.MatchHistoryService;
import java.util.List;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "match", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchHistoryController {

  private final MatchHistoryDtoMapper mapper;
  private final MatchHistoryService service;

  @GetMapping
  public ResponseEntity<List<MatchHistoryDto>> getAll(
      @RequestParam(required = false) Integer playerID,
      @RequestParam(required = false) String deckName) {
    List<MatchHistory> matches;
    if (playerID != null && deckName != null) {
      matches = service.getByPlayerAndDeck(playerID, deckName);
    } else if (playerID != null) {
      matches = service.getByPlayerID(playerID);
    } else if (deckName != null) {
      matches = service.getByDeckName(deckName);
    } else {
      matches = service.getAll();
    }
    return new ResponseEntity<>(mapper.toDtoList(matches), HttpStatus.OK);
  }

  @GetMapping(path = "/{matchID}")
  public ResponseEntity<MatchHistoryDto> getByID(@PathVariable @NonNull Integer matchID) {
    MatchHistory match = service.getByID(Objects.requireNonNull(matchID, "matchID"));
    return new ResponseEntity<>(mapper.toDto(match), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MatchHistoryDto> create(@RequestBody @NonNull MatchHistoryDto dto) {
    MatchHistory saved = service.create(Objects.requireNonNull(mapper.toEntity(dto), "match"));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{matchID}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MatchHistoryDto> update(
      @PathVariable @NonNull Integer matchID, @RequestBody @NonNull MatchHistoryDto dto) {
    MatchHistory updated = service.update(Objects.requireNonNull(matchID, "matchID"), dto);
    return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{matchID}")
  public ResponseEntity<Void> delete(@PathVariable @NonNull Integer matchID) {
    service.delete(Objects.requireNonNull(matchID, "matchID"));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
