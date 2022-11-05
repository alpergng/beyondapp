package com.gungor.alper.beyondapp.controller;

import com.gungor.alper.beyondapp.dto.TokenOutputDTO;
import com.gungor.alper.beyondapp.dto.input.LoginInputDTO;
import com.gungor.alper.beyondapp.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenOutputDTO> login(@Valid @RequestBody LoginInputDTO loginInputDTO) {
        return ResponseEntity.ok(loginService.login(loginInputDTO));
    }
}
