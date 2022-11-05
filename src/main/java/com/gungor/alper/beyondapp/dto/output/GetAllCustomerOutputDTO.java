package com.gungor.alper.beyondapp.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllCustomerOutputDTO {

    List<GetCustomerOutputDTO> customerList;
}
