package org.demo.spring.dao;

import java.util.List;

import org.demo.spring.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomerList();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
}
