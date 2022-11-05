package com.gungor.alper.beyondapp.service;


import com.gungor.alper.beyondapp.dto.input.CreateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.input.UpdateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.output.BaseOutputDTO;
import com.gungor.alper.beyondapp.dto.output.CreateCustomerOutputDTO;
import com.gungor.alper.beyondapp.dto.output.GetAllCustomerOutputDTO;
import com.gungor.alper.beyondapp.dto.output.GetCustomerOutputDTO;
import com.gungor.alper.beyondapp.exception.UserAlreadyExists;
import com.gungor.alper.beyondapp.exception.UserNotExistsException;
import com.gungor.alper.beyondapp.mapper.CustomerMapper;
import com.gungor.alper.beyondapp.model.Customer;
import com.gungor.alper.beyondapp.repository.CustomerRepository;
import com.gungor.alper.beyondapp.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final JwtTokenUtil jwtTokenUtil;


    @Override
    public CreateCustomerOutputDTO createCustomer(CreateCustomerInputDTO createCustomerDTO) {
        checkUserExists(createCustomerDTO.getUsername());
        Customer customer = customerMapper.toEntity(createCustomerDTO);
        customerRepository.save(customer);

        String token = jwtTokenUtil.generateToken(createCustomerDTO.getUsername());

        return new CreateCustomerOutputDTO(token);
    }

    @Override
    public GetAllCustomerOutputDTO getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<GetCustomerOutputDTO> returnCustomerList = new ArrayList<>();
        customerList.forEach(customer -> returnCustomerList.add(customerMapper.toDTO(customer)));

        return new GetAllCustomerOutputDTO(returnCustomerList);
    }

    @Override
    public GetCustomerOutputDTO getByUsername(String username) {
        Customer customer = getCustomerIfExistsByUsername(username);
        return customerMapper.toDTO(customer);
    }

    @Override
    public GetCustomerOutputDTO getById(Long requestId) {
        Customer customer = getCustomerIfExistsById(requestId);
        return customerMapper.toDTO(customer);
    }

    @Override
    public BaseOutputDTO update(UpdateCustomerInputDTO updateCustomerInputDTO) {
        Customer customer = getCustomerIfExistsByUsername(updateCustomerInputDTO.getUsername());

        customer.setAge(updateCustomerInputDTO.getAge());
        customer.setGender(updateCustomerInputDTO.getGender());
        customer.setName(updateCustomerInputDTO.getName());
        customer.setName(updateCustomerInputDTO.getSurname());
        customerRepository.save(customer);
        return new BaseOutputDTO(true);
    }

    @Override
    public BaseOutputDTO delete(Long id) {
        getCustomerIfExistsById(id);
        customerRepository.deleteById(id);
        return new BaseOutputDTO(true);
    }

    private void checkUserExists(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (Objects.nonNull(customer))
            throw new UserAlreadyExists("Already another user exists with username : " + username);
    }

    private Customer getCustomerIfExistsById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent())
            throw new UserNotExistsException("User not exists with id : " + id);
        return customer.get();
    }


    private Customer getCustomerIfExistsByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (Objects.isNull(customer))
            throw new UserNotExistsException("User not exists with username : " + username);
        return customer;
    }

}
