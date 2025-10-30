package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.OrderItemDto;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.model.Orders;
import com.mtg.mtgservice.model.composite.CardId;
import com.mtg.mtgservice.model.composite.OrderItemID;

import jakarta.persistence.EntityNotFoundException;

import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemDtoMapper {

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "quantity", source = "quantity"),
      @Mapping(target = "price",    source = "price"),

  })
  OrderItem toEntity(OrderItemDto dto) throws EntityNotFoundException;

  @AfterMapping
  default void fillId(OrderItemDto dto, @MappingTarget OrderItem entity) {
    if (entity.getId() == null) entity.setId(new OrderItemID());

    Orders order = new Orders();
    order.setOrderID(dto.getOrderId());

    CardId cid = new CardId(dto.getCardNumber(), dto.getSetName());
    Card card = new Card();
    card.setId(cid);

    entity.getId().setOrderID(order);
    entity.getId().setCard(card);
  }

  @Mappings({
      @Mapping(target = "orderId",    source = "id.orderID.orderID"),
      @Mapping(target = "cardNumber", source = "id.card.id.cardNumber"),
      @Mapping(target = "setName",    source = "id.card.id.setName"),
      @Mapping(target = "quantity",   source = "quantity"),
      @Mapping(target = "price",      source = "price")
  })
  OrderItemDto toDto(OrderItem entity);

  List<OrderItemDto> toDtoList(List<OrderItem> entities);
}
