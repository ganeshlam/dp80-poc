
package org.dp80.jpa.ex1.dao.impl;

import org.dp80.jpa.ex1.dao.EmployeeDao;
import org.dp80.jpa.ex1.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl  extends AbstractBaseDaoImpl<Employee> implements EmployeeDao {

	@Override
	public Class<Employee> getEntityClass() {
	
		return Employee.class;
	}
	
}
