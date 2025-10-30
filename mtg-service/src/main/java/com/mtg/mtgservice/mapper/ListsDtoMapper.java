// package com.mtg.mtgservice.mapper;

// import org.mapstruct.Mapper;
// import java.util.List;

// import com.mtg.mtgservice.dto.ListDto;
// import com.mtg.mtgservice.service.ListService;
// import jakarta.persistence.EntityNotFoundException;

// @Mapper(
//     componentModel = "spring",
//     uses = {ListService.class}
// )
// public interface ListDtoMapper {
//     com.mtg.mtgservice.model.List toEntity(ListDto listDto) throws EntityNotFoundException;

//     ListDto toDto(com.mtg.mtgservice.model.List list) throws EntityNotFoundException;

//     List<ListDto> toDtoList(List<com.mtg.mtgservice.model.List> listList) throws
// EntityNotFoundException;
// }

// package com.mtg.mtgservice.mapper;

// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import java.util.List;

// import com.mtg.mtgservice.dto.ListDto;
// import com.mtg.mtgservice.service.ListService;
// // You likely need to import your Customer model here if you use the "helper" method approach
// below
// // import com.mtg.mtgservice.model.Customer;
// import jakarta.persistence.EntityNotFoundException;

// @Mapper(
//     componentModel = "spring",
//     uses = {ListService.class}
// )
// public interface ListDtoMapper {

//     // DTO to Entity Mapping
//     @Mapping(source = "listID", target = "listId") // Fixes the case sensitivity issue
//     @Mapping(target = "customerEmail", ignore = true) // IGNORE this for now. See note below why.
//     com.mtg.mtgservice.model.List toEntity(ListDto listDto) throws EntityNotFoundException;

//     // Entity to DTO Mapping
//     @Mapping(source = "listId", target = "listID") // Fixes the case sensitivity issue
//     // assuming your Customer object has a getEmail() method:
//     @Mapping(source = "customerEmail", target = "customerEmail")
//     ListDto toDto(com.mtg.mtgservice.model.List list) throws EntityNotFoundException;

//     List<ListDto> toDtoList(List<com.mtg.mtgservice.model.List> listList) throws
// EntityNotFoundException;
// }

package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.ListsDto;
import com.mtg.mtgservice.model.Lists;
import com.mtg.mtgservice.service.ListsService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    uses = {ListsService.class, CustomerDtoMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ListsDtoMapper {
  @Mapping(source = "customerEmail", target = "customerEmail.customerEmail")
  Lists toEntity(ListsDto listDto) throws EntityNotFoundException;

  @Mapping(source = "customerEmail.customerEmail", target = "customerEmail")
  ListsDto toDto(Lists list) throws EntityNotFoundException;

  List<ListsDto> toDtoList(List<Lists> listList) throws EntityNotFoundException;
}
