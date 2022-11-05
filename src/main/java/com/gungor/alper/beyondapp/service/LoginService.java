package com.gungor.alper.beyondapp.service;

import com.gungor.alper.beyondapp.dto.TokenOutputDTO;
import com.gungor.alper.beyondapp.dto.input.LoginInputDTO;

public interface LoginService {

    TokenOutputDTO login(LoginInputDTO loginInputDTO);
}
