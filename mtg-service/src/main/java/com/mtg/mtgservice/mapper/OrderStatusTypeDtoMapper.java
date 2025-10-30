package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.OrderStatusTypeDto;
import com.mtg.mtgservice.model.OrderStatusType;
import com.mtg.mtgservice.service.OrderStatusTypeService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {OrderStatusTypeService.class})
public interface OrderStatusTypeDtoMapper {
  OrderStatusType toEntity(OrderStatusTypeDto orderStatusTypeDto) throws EntityNotFoundException;

  OrderStatusTypeDto toDto(OrderStatusType orderStatusType) throws EntityNotFoundException;

  List<OrderStatusTypeDto> toDtoList(List<OrderStatusType> orderStatusTypeList)
      throws EntityNotFoundException;
}
