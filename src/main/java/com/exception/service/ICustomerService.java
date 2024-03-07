package com.exception.service;

import com.exception.exception.ExceptionHandle;

import java.util.List;
import java.util.Optional;

public interface ICustomerService<Customer> {
    Iterable<Customer> findAll();

    void save(Customer t) throws ExceptionHandle;

    Optional<Customer> findById(Long id);
    Customer findOne(Long id);

    void remove(Long id);
}
