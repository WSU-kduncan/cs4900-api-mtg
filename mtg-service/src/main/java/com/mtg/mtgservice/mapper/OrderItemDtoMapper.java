package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.OrderItemDto;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.model.Orders;
import com.mtg.mtgservice.model.composite.CardID;
import com.mtg.mtgservice.model.composite.OrderItemID;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderItemDtoMapper {

  @Mappings({
    @Mapping(target = "ID", ignore = true),
    @Mapping(target = "quantity", source = "quantity"),
    @Mapping(target = "price", source = "price"),
  })
  OrderItem toEntity(OrderItemDto dto) throws EntityNotFoundException;

  @AfterMapping
  default void fillID(OrderItemDto dto, @MappingTarget OrderItem entity) {
    if (entity.getID() == null) entity.setID(new OrderItemID());

    Orders order = new Orders();
    order.setOrderID(dto.getOrderID());

    CardID cID = new CardID(dto.getCardNumber(), dto.getSetName());
    Card card = new Card();
    card.setID(cID);

    entity.getID().setOrderID(order);
    entity.getID().setCard(card);
  }

  @Mappings({
    @Mapping(target = "orderID", source = "ID.orderID.orderID"),
    @Mapping(target = "cardNumber", source = "ID.card.ID.cardNumber"),
    @Mapping(target = "setName", source = "ID.card.ID.setName"),
    @Mapping(target = "quantity", source = "quantity"),
    @Mapping(target = "price", source = "price")
  })
  OrderItemDto toDto(OrderItem entity);

  List<OrderItemDto> toDtoList(List<OrderItem> entities);
}
