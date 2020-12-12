package org.demo.spring.dao;

import java.util.List;

import org.demo.spring.entity.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
//	@Transactional
//	this tag is moved to the service layer
//	the service layer is used to integrate multiple data sources
//	it takes care of the transaction of the session object
	public List<Customer> getCustomerList() {

		Session currentSession = sessionFactory.getCurrentSession();

		//sort by last_name
		List<Customer> customerList = currentSession
								.createQuery("from Customer order by lastName", Customer.class)
								.getResultList();
		
		return customerList;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer customer = currentSession.get(Customer.class, id); 

		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession
		.createQuery("delete from Customer where id=:id")
		.setParameter("id", id)
		.executeUpdate();
		
	}

}
