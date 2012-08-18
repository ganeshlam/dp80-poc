
package org.dp80.mongodb.ex1.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.dp80.mongodb.ex1.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, ObjectId> {
	
	List<Customer> findByName(String name);
}
