
package org.dp80.jpa.ex1.dao.impl;

import org.dp80.jpa.ex1.dao.ProjectDao;
import org.dp80.jpa.ex1.model.Project;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl  extends AbstractBaseDaoImpl<Project> implements ProjectDao {

	@Override
	public Class<Project> getEntityClass() {
	
		return Project.class;
	}
	
}
