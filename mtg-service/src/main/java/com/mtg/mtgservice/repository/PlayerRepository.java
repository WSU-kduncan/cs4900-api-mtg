package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	boolean existsByPlayerName(String playerName);
}
