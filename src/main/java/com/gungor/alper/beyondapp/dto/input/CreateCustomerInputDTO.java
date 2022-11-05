package com.gungor.alper.beyondapp.dto.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateCustomerInputDTO extends LoginInputDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    private int age;

    @Size(max = 1)
    private String gender;

}
