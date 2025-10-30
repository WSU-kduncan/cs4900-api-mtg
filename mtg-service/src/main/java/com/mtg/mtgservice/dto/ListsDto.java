package com.mtg.mtgservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class ListsDto {
  Integer listID;
  String customerEmail;
  String listName;
}
