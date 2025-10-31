package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.ListItemDto;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.ListItem;
import com.mtg.mtgservice.model.composite.CardID;
import com.mtg.mtgservice.model.composite.ListItemID;
import com.mtg.mtgservice.service.CardService;
import com.mtg.mtgservice.service.ListItemService;
import java.util.List;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {ListItemService.class, CardService.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ListItemDtoMapper {

  @Mappings({
    @Mapping(target = "ID", ignore = true),
    @Mapping(target = "quantityWanted", source = "quantityWanted")
  })
  ListItem toEntity(ListItemDto dto);

  @AfterMapping
  default void fillID(ListItemDto dto, @MappingTarget ListItem entity) {
    if (entity.getID() == null) entity.setID(new ListItemID());

    com.mtg.mtgservice.model.Lists listEntity = new com.mtg.mtgservice.model.Lists();
    listEntity.setListID(dto.getListID());

    CardID cID = new CardID(dto.getCardNumber(), dto.getSetName());
    Card card = new Card();
    card.setID(cID);

    entity.getID().setListID(listEntity);
    entity.getID().setCard(card);
  }

  @Mappings({
    @Mapping(target = "listID", source = "ID.listID.listID"),
    @Mapping(target = "cardNumber", source = "ID.card.ID.cardNumber"),
    @Mapping(target = "setName", source = "ID.card.ID.setName"),
    @Mapping(target = "quantityWanted", source = "quantityWanted")
  })
  ListItemDto toDto(ListItem entity);

  List<ListItemDto> toDtoList(List<ListItem> entities);
}
