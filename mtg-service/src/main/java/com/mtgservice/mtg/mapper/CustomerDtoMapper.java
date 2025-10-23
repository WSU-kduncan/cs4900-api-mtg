package com.mtgservice.mtg.mapper;

import com.mtgservice.mtg.dto.CustomerDto;
import com.mtgservice.mtg.model.Customer;
import com.mtgservice.mtg.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {CustomerService.class})
public interface CustomerDtoMapper {

  Customer toEntity(CustomerDto customerDto) throws EntityNotFoundException;

  // No exception needed for a simple DTO mapping
  CustomerDto toDto(Customer customer);

  // Replaced the static implementation with an abstract method.
  // MapStruct will generate the code to loop the list and call toDto().
  List<CustomerDto> toDtoList(List<Customer> customerList);
}
