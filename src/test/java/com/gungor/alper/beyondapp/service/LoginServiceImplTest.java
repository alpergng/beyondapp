package com.gungor.alper.beyondapp.service;

import com.gungor.alper.beyondapp.dto.TokenOutputDTO;
import com.gungor.alper.beyondapp.dto.input.LoginInputDTO;
import com.gungor.alper.beyondapp.exception.JwtTokenException;
import com.gungor.alper.beyondapp.exception.UserAlreadyExists;
import com.gungor.alper.beyondapp.model.Customer;
import com.gungor.alper.beyondapp.repository.CustomerRepository;
import com.gungor.alper.beyondapp.security.JwtTokenUtil;
import com.gungor.alper.beyondapp.security.JwtUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private JwtUserDetailsService userDetailsService;


    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginServiceImpl loginService;


    @Test
    void loginShouldThrowExceptionWhenUserNotExists() {
        Executable executable = () -> loginService.login(loginInputDTO);

        Assertions.assertThrows(UserAlreadyExists.class, executable);
    }

    @Test
    void loginShouldThrowExceptionWheUserNotAuthenticate() {
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(USERNAME, PW))).
                thenThrow(new DisabledException("DISABLED"));
        when(customerRepository.findByUsername(USERNAME)).thenReturn(new Customer());
        Executable executable = () -> loginService.login(loginInputDTO);

        Assertions.assertThrows(JwtTokenException.class, executable);
    }

    @Test
    void loginShouldReturnTokenWhenSuccess() {
        when(customerRepository.findByUsername(USERNAME)).thenReturn(new Customer());
        when(jwtTokenUtil.generateToken(USERNAME)).thenReturn(TOKEN);

        TokenOutputDTO login = loginService.login(loginInputDTO);

        Assertions.assertEquals(TOKEN, login.getToken());
    }

    @BeforeEach
    void setUp() {
        loginInputDTO = new LoginInputDTO();
        loginInputDTO.setUsername(USERNAME);
        loginInputDTO.setPassword(PW);
    }

    private static final String USERNAME = "username";

    private static final String PW = "password";

    private static final String TOKEN = "token";

    private static LoginInputDTO loginInputDTO;
}