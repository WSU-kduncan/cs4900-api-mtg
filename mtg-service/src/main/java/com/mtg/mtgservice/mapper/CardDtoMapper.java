package com.mtg.mtgservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mtg.mtgservice.dto.CardDto;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.composite.CardId;

@Component
public class CardDtoMapper {

  public CardDto toDto(Card c) {
    CardDto dto = new CardDto();
    dto.setCardNumber(c.getId().getCardNumber());
    dto.setSetName(c.getId().getSetName());
    dto.setCardName(c.getCardName());
    dto.setCardType(c.getCardType());
    dto.setManaValue(c.getManaValue());
    dto.setCardCondition(c.getCardCondition());
    dto.setCardDescription(c.getCardDescription());
    dto.setPrice(c.getPrice());
    dto.setStock(c.getStock());
    return dto;
  }

  public List<CardDto> toDtoList(List<Card> cards) {
    return cards.stream().map(this::toDto).collect(Collectors.toList());
  }


  public Card toEntity(CardDto dto) {
    Card c = new Card();
    c.setId(new CardId(dto.getCardNumber(), dto.getSetName()));
    c.setCardName(dto.getCardName());
    c.setCardType(dto.getCardType());
    c.setManaValue(dto.getManaValue());
    c.setCardCondition(dto.getCardCondition());
    c.setCardDescription(dto.getCardDescription());
    c.setPrice(dto.getPrice());
    c.setStock(dto.getStock());
    return c;
  }
   public void applyUpdates(Card target, CardDto dto) {
    if (dto.getCardName() != null)        target.setCardName(dto.getCardName());
    if (dto.getCardType() != null)        target.setCardType(dto.getCardType());
    if (dto.getManaValue() != null)       target.setManaValue(dto.getManaValue());
    if (dto.getCardCondition() != null)   target.setCardCondition(dto.getCardCondition());
    if (dto.getCardDescription() != null) target.setCardDescription(dto.getCardDescription());
    if (dto.getPrice() != null)           target.setPrice(dto.getPrice());
    if (dto.getStock() != null)           target.setStock(dto.getStock());
  }
}
