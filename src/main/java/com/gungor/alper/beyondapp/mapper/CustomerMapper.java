package com.gungor.alper.beyondapp.mapper;


import com.gungor.alper.beyondapp.anotation.EncodedMapping;
import com.gungor.alper.beyondapp.dto.input.CreateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.output.GetCustomerOutputDTO;
import com.gungor.alper.beyondapp.model.Customer;
import com.gungor.alper.beyondapp.util.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = PasswordEncoderMapper.class)
public interface CustomerMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    Customer toEntity(CreateCustomerInputDTO createCustomerInputDTO);

    GetCustomerOutputDTO toDTO(Customer customer);
}
