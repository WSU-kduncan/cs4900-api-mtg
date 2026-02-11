package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.MatchHistoryDto;
import com.mtg.mtgservice.model.MatchHistory;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchHistoryDtoMapper {
  MatchHistory toEntity(MatchHistoryDto dto);

  MatchHistoryDto toDto(MatchHistory entity);

  List<MatchHistoryDto> toDtoList(List<MatchHistory> entities);
}
