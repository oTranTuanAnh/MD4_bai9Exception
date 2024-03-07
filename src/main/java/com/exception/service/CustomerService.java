package com.exception.service;

import com.exception.exception.ExceptionHandle;
import com.exception.model.Customer;
import com.exception.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService<Customer>{

    @Autowired
    private ICustomerRepo customerRepo;


    @Override
    public Iterable<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public void save(Customer customer) throws ExceptionHandle {

        try {
            customerRepo.save(customer);
        } catch (DataIntegrityViolationException e){
            throw new ExceptionHandle();
        }

    }
    @Override
    public Customer findOne(Long id) {
        Customer customer = new Customer();
        return customer;
    }
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public void remove(Long id) {
        customerRepo.deleteById(id);
    }
}
