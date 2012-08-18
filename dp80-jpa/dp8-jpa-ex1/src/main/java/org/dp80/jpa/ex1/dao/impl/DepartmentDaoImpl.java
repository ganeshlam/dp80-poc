
package org.dp80.jpa.ex1.dao.impl;

import org.dp80.jpa.ex1.dao.DepartmentDao;
import org.dp80.jpa.ex1.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl extends AbstractBaseDaoImpl<Department> implements DepartmentDao {

	@Override
	public Class<Department> getEntityClass() {
	
		return Department.class;
	}
	
	
}
