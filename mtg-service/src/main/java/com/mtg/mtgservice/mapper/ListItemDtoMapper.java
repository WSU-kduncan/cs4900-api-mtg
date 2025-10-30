package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.ListItemDto;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.ListItem;
import com.mtg.mtgservice.model.composite.CardId;
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
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "quantityWanted", source = "quantityWanted")
  })
  ListItem toEntity(ListItemDto dto);

  @AfterMapping
  default void fillId(ListItemDto dto, @MappingTarget ListItem entity) {
    if (entity.getId() == null) entity.setId(new ListItemID());

    // com.mtg.mtgservice.model.List (fully qualified to avoid clash)
    com.mtg.mtgservice.model.Lists listEntity = new com.mtg.mtgservice.model.Lists();
    listEntity.setListID(dto.getListId());

    CardId cid = new CardId(dto.getCardNumber(), dto.getSetName());
    Card card = new Card();
    card.setId(cid);

    entity.getId().setListID(listEntity);
    entity.getId().setCard(card);
  }

  @Mappings({
    @Mapping(target = "listId", source = "id.listID.listID"),
    @Mapping(target = "cardNumber", source = "id.card.id.cardNumber"),
    @Mapping(target = "setName", source = "id.card.id.setName"),
    @Mapping(target = "quantityWanted", source = "quantityWanted")
  })
  ListItemDto toDto(ListItem entity);

  List<ListItemDto> toDtoList(List<ListItem> entities);
}
