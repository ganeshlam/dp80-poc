
package org.dp80.jaxrs.ex1.service;

import java.util.Date;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dp80.jaxrs.ex1.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("customerService")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger	logger	= LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	/**
	 * 
	 * 
	 * @see org.dp80.jaxrs.ex1.service.CustomerService#getCustomer(java.lang.Long)
	 */
	@Override
	public Customer getCustomer(Long id)
	{
	
		final Customer customer = new Customer();
		customer.setId(id);
		customer.setAddress("address-1");
		customer.setName("address-1");
		customer.setPermanent(false);
		customer.setStartDate(new Date());
		
		logger.debug("Get customer data. Cusomer id: {}, Customer: {}", id, customer);
		
		return customer;
	}
	
	/**
	 * 
	 * 
	 * @see org.dp80.jaxrs.ex1.service.CustomerService#saveCustomer(org.dp80.jaxrs.ex1.dto.Customer)
	 */
	@Override
	public Customer saveCustomer(Customer customer) {
	
		customer.setId(Math.abs(new Random().nextLong()));
		
		logger.debug("Save customer data. Customer: {}", customer);
		
		return customer;
	}
}
