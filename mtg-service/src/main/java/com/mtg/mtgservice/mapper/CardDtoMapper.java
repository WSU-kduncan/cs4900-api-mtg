package com.mtg.mtgservice.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.mtg.mtgservice.dto.CardDto;
import com.mtg.mtgservice.model.Card;

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
}