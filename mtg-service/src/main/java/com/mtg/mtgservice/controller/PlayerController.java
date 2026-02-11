package com.mtg.mtgservice.controller;

import com.mtg.mtgservice.dto.PlayerDto;
import com.mtg.mtgservice.mapper.PlayerDtoMapper;
import com.mtg.mtgservice.model.Player;
import com.mtg.mtgservice.service.PlayerService;
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
@RequestMapping(path = "player", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

  private final PlayerDtoMapper mapper;
  private final PlayerService service;

  @GetMapping
  public ResponseEntity<List<PlayerDto>> getAll() {
    List<Player> players = service.getAll();
    return new ResponseEntity<>(mapper.toDtoList(players), HttpStatus.OK);
  }

  @GetMapping(path = "/{playerID}")
  public ResponseEntity<PlayerDto> getByID(@PathVariable @NonNull Integer playerID) {
    Player player = service.getByID(Objects.requireNonNull(playerID, "playerID"));
    return new ResponseEntity<>(mapper.toDto(player), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PlayerDto> create(@RequestBody @NonNull PlayerDto dto) {
    Player saved = service.create(Objects.requireNonNull(mapper.toEntity(dto), "player"));
    return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{playerID}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PlayerDto> update(
      @PathVariable @NonNull Integer playerID, @RequestBody @NonNull PlayerDto dto) {
    Player updated = service.update(Objects.requireNonNull(playerID, "playerID"), dto);
    return new ResponseEntity<>(mapper.toDto(updated), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{playerID}")
  public ResponseEntity<Void> delete(@PathVariable @NonNull Integer playerID) {
    service.delete(Objects.requireNonNull(playerID, "playerID"));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
