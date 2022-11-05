package com.gungor.alper.beyondapp.dto.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginInputDTO {

    @NotNull
    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 3)
    private String password;
}
