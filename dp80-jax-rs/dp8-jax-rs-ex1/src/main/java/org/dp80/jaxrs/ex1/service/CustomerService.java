
package org.dp80.jaxrs.ex1.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dp80.jaxrs.ex1.dto.Customer;


@Path("/customer")
@Produces({ MediaType.TEXT_PLAIN })
public interface CustomerService {
	
	@GET
	@Path("{customerId}")
	Customer getCustomer(@PathParam("customerId") Long id);
	
	@POST
	Customer saveCustomer(Customer customer);
}
