package com.gungor.alper.beyondapp.service;

import com.gungor.alper.beyondapp.dto.input.CreateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.output.CreateCustomerOutputDTO;
import com.gungor.alper.beyondapp.mapper.CustomerMapper;
import com.gungor.alper.beyondapp.repository.CustomerRepository;
import com.gungor.alper.beyondapp.security.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private CustomerServiceImpl customerService;


    @Test
    void createCustomerShouldReturnTokenWhenSuccess() {
        CreateCustomerInputDTO createCustomerInputDTO = new CreateCustomerInputDTO();
        createCustomerInputDTO.setUsername(USERNAME);
        createCustomerInputDTO.setPassword(PW);

        when(jwtTokenUtil.generateToken(USERNAME)).thenReturn(TOKEN);

        CreateCustomerOutputDTO customer = customerService.createCustomer(createCustomerInputDTO);

        Assertions.assertEquals(TOKEN, customer.getToken());


    }


    private static final String USERNAME = "username";

    private static final String PW = "password";

    private static final String TOKEN = "token";

}