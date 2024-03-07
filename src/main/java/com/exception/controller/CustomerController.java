package com.exception.controller;

import com.exception.exception.ExceptionHandle;
import com.exception.model.Customer;
import com.exception.service.CustomerService;
import com.exception.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("")
    public ModelAndView home(){
       ModelAndView modelAndView = new ModelAndView("index");
       Iterable<Customer> customers = customerService.findAll();
       modelAndView.addObject("customers", customers);
       return modelAndView;
    }
    @GetMapping("/{id}")
    public ModelAndView showInformation(@PathVariable Long id) {
        try {
            ModelAndView modelAndView = new ModelAndView("/customer/info");
            Customer customer = (Customer) customerService.findOne(id);
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers");
        }
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("customers", new Customer());
        return "create";
    }
    @PostMapping("/save")
    public String save(Customer customer) throws ExceptionHandle {
            customerService.save(customer);
            return "redirect:/customers";
    }
    @ExceptionHandler(ExceptionHandle.class)
    public ModelAndView showInputNotAcceptable(){
        return new ModelAndView("inputs-not-acceptable");
    }

}