package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<Deck, String> {}
