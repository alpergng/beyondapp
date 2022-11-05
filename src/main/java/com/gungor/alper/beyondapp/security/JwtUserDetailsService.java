package com.gungor.alper.beyondapp.security;

import com.gungor.alper.beyondapp.model.Customer;
import com.gungor.alper.beyondapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (Objects.isNull(customer))
            throw new UsernameNotFoundException("User not found with username : " + username);


        return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
    }
}
