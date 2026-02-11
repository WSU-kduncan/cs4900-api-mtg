package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.DeckDto;
import com.mtg.mtgservice.model.Deck;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeckDtoMapper {
  Deck toEntity(DeckDto deckDto);

  DeckDto toDto(Deck deck);

  List<DeckDto> toDtoList(List<Deck> deckList);
}
