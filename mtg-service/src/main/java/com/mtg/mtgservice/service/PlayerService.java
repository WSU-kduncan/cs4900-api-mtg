package com.mtg.mtgservice.service;

import com.mtg.mtgservice.dto.PlayerDto;
import com.mtg.mtgservice.model.Player;
import com.mtg.mtgservice.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerService {

  private final PlayerRepository playerRepository;

  public List<Player> getAll() {
    return playerRepository.findAll();
  }

  public Player getByID(@NonNull Integer playerID) {
    Objects.requireNonNull(playerID, "playerID");
    return playerRepository
        .findById(playerID)
        .orElseThrow(() -> new EntityNotFoundException("Player " + playerID + " not found"));
  }

  public Player create(@NonNull Player player) {
    return playerRepository.save(Objects.requireNonNull(player, "player"));
  }

  @Transactional
  public Player update(@NonNull Integer playerID, PlayerDto dto) {
    Objects.requireNonNull(playerID, "playerID");
    Player existing = getByID(playerID);
    existing.setPlayerName(dto.getPlayerName());
    existing.setWins(dto.getWins());
    existing.setLosses(dto.getLosses());
    existing.setTies(dto.getTies());
    existing.setCasualWins(dto.getCasualWins());
    existing.setCasualLosses(dto.getCasualLosses());
    return playerRepository.save(existing);
  }

  @Transactional
  public void delete(@NonNull Integer playerID) {
    Objects.requireNonNull(playerID, "playerID");
    if (!playerRepository.existsById(playerID)) {
      throw new EntityNotFoundException("Player " + playerID + " not found");
    }
    playerRepository.deleteById(playerID);
  }
}
