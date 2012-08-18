
package org.dp80.mongodb.ex1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dp80.mongodb.ex1.domain.Customer;
import org.dp80.mongodb.ex1.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRepositoryTest extends AbstractIntegrationTest {
	
	@Autowired
	CustomerRepository	customerRepository;
	
	@Before
	public void purgeRepository() {
	
		customerRepository.deleteAll();
		super.setUp();
	}
	
	@Test
	public void save() throws Exception {
	
		Customer customer = this.createCustomer();
		
		this.customerRepository.save(customer);
		
//		Customer customerSaved = this.customerRepository.findOne(customer.getId());
//		
//		assertEquals(customer.getId(), customerSaved.getId());
	}
	
	@Test
	public void count() throws Exception {
	
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(this.createCustomer());
		customers.add(this.createCustomer());
		customers.add(this.createCustomer());
		
		this.customerRepository.save(customers);
		
		assertEquals(3, this.customerRepository.count());
	}
	
	private Customer createCustomer() {
	
		final Customer customer = new Customer();
		customer.setAddress("address");
		customer.setName("name");
		customer.setPermanent(true);
		customer.setStartDate(new Date());
		
		return customer;
	}
	
}
