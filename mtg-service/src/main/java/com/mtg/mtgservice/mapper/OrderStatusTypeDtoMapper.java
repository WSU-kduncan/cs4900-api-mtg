package com.mtg.mtgservice.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.mtg.mtgservice.dto.OrderStatusTypeDto;
import com.mtg.mtgservice.model.OrderStatusType;

@Component
public class OrderStatusTypeDtoMapper {

  public OrderStatusTypeDto toDto(OrderStatusType e) {
    OrderStatusTypeDto dto = new OrderStatusTypeDto();
    dto.setStatusTypeId(e.getStatusTypeId());        
    dto.setStatusDescription(e.getStatusDescription());
    return dto;
  }

  public List<OrderStatusTypeDto> toDtoList(List<OrderStatusType> list) {
    return list.stream().map(this::toDto).collect(Collectors.toList());
  }
    public OrderStatusType toEntity(OrderStatusTypeDto dto) {
    OrderStatusType e = new OrderStatusType();
    e.setStatusTypeId(dto.getStatusTypeId());           
    e.setStatusDescription(dto.getStatusDescription());
    return e;
  }

  public void copyToEntity(OrderStatusTypeDto dto, OrderStatusType e) {
    if (dto.getStatusDescription() != null) {
      e.setStatusDescription(dto.getStatusDescription());
    }
  }
}
