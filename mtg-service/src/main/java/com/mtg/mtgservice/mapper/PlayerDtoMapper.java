package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.PlayerDto;
import com.mtg.mtgservice.model.Player;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerDtoMapper {
  Player toEntity(PlayerDto playerDto);

  PlayerDto toDto(Player player);

  List<PlayerDto> toDtoList(List<Player> playerList);
}
