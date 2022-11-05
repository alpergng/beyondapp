package com.gungor.alper.beyondapp.repository;

import com.gungor.alper.beyondapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByUsername(String username);
}
