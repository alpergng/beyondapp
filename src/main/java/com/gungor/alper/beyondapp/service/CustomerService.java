package com.gungor.alper.beyondapp.service;

import com.gungor.alper.beyondapp.dto.input.CreateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.input.DeleteCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.input.UpdateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.output.BaseOutputDTO;
import com.gungor.alper.beyondapp.dto.output.CreateCustomerOutputDTO;
import com.gungor.alper.beyondapp.dto.output.GetAllCustomerOutputDTO;
import com.gungor.alper.beyondapp.dto.output.GetCustomerOutputDTO;

public interface CustomerService {


    CreateCustomerOutputDTO createCustomer(CreateCustomerInputDTO createCustomerDTO);

    GetAllCustomerOutputDTO getAllCustomer();
    
    GetCustomerOutputDTO getByUsername(String username);

    GetCustomerOutputDTO getById(Long requestId);

    BaseOutputDTO update(UpdateCustomerInputDTO updateCustomerInputDTO);

    BaseOutputDTO delete(Long id);
}
