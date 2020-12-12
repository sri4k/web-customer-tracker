package org.demo.spring.service;

import java.util.List;

import org.demo.spring.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomerList();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
}
