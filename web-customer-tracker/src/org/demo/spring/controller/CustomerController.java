package org.demo.spring.controller;

import java.util.List;

import org.demo.spring.dao.CustomerDAO;
import org.demo.spring.entity.Customer;
import org.demo.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//inject the customer dao
//	@Autowired
//	private CustomerDAO customerDAO;
	
	//inject the customer service
	@Autowired
	private CustomerService customerService;
	
//	@RequestMapping("/list")
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		//get customers from dao
		List<Customer> customerList = customerService.getCustomerList();
		
		//add the customers to the spring mvc model
		model.addAttribute("customerList", customerList);
		
		return "list-customers";
	}
	
//	handles the GET requests
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,
									Model model) {
		
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
}
