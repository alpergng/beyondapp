package com.gungor.alper.beyondapp.controller;

import com.gungor.alper.beyondapp.dto.input.CreateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.input.DeleteCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.input.UpdateCustomerInputDTO;
import com.gungor.alper.beyondapp.dto.output.BaseOutputDTO;
import com.gungor.alper.beyondapp.dto.output.CreateCustomerOutputDTO;
import com.gungor.alper.beyondapp.dto.output.GetAllCustomerOutputDTO;
import com.gungor.alper.beyondapp.dto.output.GetCustomerOutputDTO;
import com.gungor.alper.beyondapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CreateCustomerOutputDTO> create(@Valid @RequestBody CreateCustomerInputDTO createCustomerInputDTO){
        return ResponseEntity.ok(customerService.createCustomer(createCustomerInputDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<GetAllCustomerOutputDTO> getAll(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/getById")
    public ResponseEntity<GetCustomerOutputDTO> getById(@RequestParam Long Id){
        return ResponseEntity.ok(customerService.getById(Id));
    }

    @GetMapping("/getByUsername")
    public ResponseEntity<GetCustomerOutputDTO> getByUserName(@RequestParam String username){
        return ResponseEntity.ok(customerService.getByUsername(username));
    }

    @PutMapping("/update")
    public ResponseEntity<BaseOutputDTO> update(@Valid @RequestBody UpdateCustomerInputDTO updateCustomerInputDTO){
        return ResponseEntity.ok(customerService.update(updateCustomerInputDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseOutputDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerService.delete(id));
    }




}
