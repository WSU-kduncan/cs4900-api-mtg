package com.mtg.mtgservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class WorkerDto {
    Integer employeeID;

    String firstName;

    String lastName;

    String role;

    String email;
}
