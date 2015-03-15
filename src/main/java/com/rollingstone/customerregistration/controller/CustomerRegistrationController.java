package com.rollingstone.customerregistration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rollingstone.customerregistration.model.Customer;
import com.rollingstone.customerregistration.model.CustomerLogin;
import com.rollingstone.customerregistration.service.CustomerService;

@Controller
@SessionAttributes("customer")
public class CustomerRegistrationController
{

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model)
    {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return "signup";
        }
        else if (customerService.findByUserName(customer.getUserName()))
        {
            model.addAttribute("message", "User Name exists. Please Try another user name");
            return "signup";
        }
        else
        {
            customerService.save(customer);
            model.addAttribute("message", "Saved Customer details");
            return "redirect:login.html";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model)
    {
        CustomerLogin customerlogin = new CustomerLogin();
        model.addAttribute("customerLogin", customerlogin);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("customerLogin") CustomerLogin customerlogin, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "login";
        }
        else
        {
            boolean found = customerService.findByLogin(customerlogin.getUserName(), customerlogin.getPassword());
            if (found)
            {
                return "success";
            }
            else
            {
                return "failure";
            }
        }

    }
}
