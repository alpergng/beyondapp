package com.gungor.alper.beyondapp.service;

import com.gungor.alper.beyondapp.dto.TokenOutputDTO;
import com.gungor.alper.beyondapp.dto.input.LoginInputDTO;
import com.gungor.alper.beyondapp.exception.JwtTokenException;
import com.gungor.alper.beyondapp.exception.UserAlreadyExists;
import com.gungor.alper.beyondapp.model.Customer;
import com.gungor.alper.beyondapp.repository.CustomerRepository;
import com.gungor.alper.beyondapp.security.JwtTokenUtil;
import com.gungor.alper.beyondapp.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final CustomerRepository customerRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    @Override
    public TokenOutputDTO login(LoginInputDTO loginInputDTO) {
        checkUserNotExists(loginInputDTO.getUsername());
        authenticate(loginInputDTO.getUsername(), loginInputDTO.getPassword());


        String token = jwtTokenUtil.generateToken(loginInputDTO.getUsername());
        return new TokenOutputDTO(token);
    }

    private void checkUserNotExists(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (Objects.isNull(customer))
            throw new UserAlreadyExists("User not exists with username : " + username);
    }

    private void authenticate(String username, String password) throws JwtTokenException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new JwtTokenException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new JwtTokenException("INVALID_CREDENTIALS", e);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
