
package org.dp80.jpa.ex1;

import org.dp80.jpa.ex1.dao.DepartmentDao;
import org.dp80.jpa.ex1.dao.EmployeeDao;
import org.dp80.jpa.ex1.dao.ParkingDao;
import org.dp80.jpa.ex1.dao.ProjectDao;
import org.dp80.jpa.ex1.model.Address;
import org.dp80.jpa.ex1.model.Department;
import org.dp80.jpa.ex1.model.Employee;
import org.dp80.jpa.ex1.model.ParkingSpace;
import org.dp80.jpa.ex1.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 *
 */
public class JpaEx1Runner {
	
	/**
	 * logger
	 */
	private static final Logger		logger		= LoggerFactory.getLogger(JpaEx1Runner.class);
	
	/**
	 * Spring configuration files
	 */
	private static final String[]	springFiles	= new String[] {
												"src/main/resources/META-INF/spring/beans.xml"
												};
	
	/**
	 * Start performance test
	 */
	public static void main(String[] args) {
	
		FileSystemXmlApplicationContext context = null;
		
		try {

			
			// 
			// Load spring context
			context = new FileSystemXmlApplicationContext(springFiles);
			EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
			DepartmentDao departmentDao = (DepartmentDao) context.getBean("departmentDao");
			ParkingDao parkingDao = (ParkingDao) context.getBean("parkingDao");
			ProjectDao projectDao = (ProjectDao) context.getBean("projectDao");
			
			Department department = new Department();
			department.setName("software");
			departmentDao.save(department);
			
			ParkingSpace parkingSpace1 = new ParkingSpace();
			parkingSpace1.setLocation("L1");
			
			ParkingSpace parkingSpace2 = new ParkingSpace();
			parkingSpace2.setLocation("L2");
			
			parkingDao.save(parkingSpace1);
			parkingDao.save(parkingSpace2);
			
			Project project1 = new Project();
			project1.setName("P1");
			
			Project project2 = new Project();
			project2.setName("P2");
			
			projectDao.save(project1);
			projectDao.save(project2);
			
			// 
			// persist an employee
			for ( int i = 1; i <= 3; i++ ) {
				
				Employee employee1 = new Employee();
				employee1.setName("name-" + i);
				employee1.setSalary(10000L);
				employee1.setDepartment(department);
				if( i == 1){
					employee1.setParkingSpace(parkingSpace1);	
				}else if ( i == 2 ){
					employee1.setParkingSpace(parkingSpace2);
				}
				employee1.getProjects().add(project1);
				employee1.getProjects().add(project2);
				
				Address address = new Address();
				address.setCity("city-" + i);
				address.setState("state-" + i);
				address.setStreet("street-" + i);
				address.setZip("zip-" + i);
				employee1.setAddress(address);
				
				employeeDao.save(employee1);
				
			}
			
			//
			// employees
			System.err.println("=================================================================================");
			for ( Employee employee : employeeDao.getAll() ) {
				
				System.err.println(employee);
				System.err.println(employee.getDepartment());
				System.err.println(employee.getParkingSpace());
			}
			System.err.println("=================================================================================");

			//
			// projects
			System.err.println("=================================================================================");
			for ( Project project : projectDao.getAll() ) {
				
				System.err.println(project);
				//System.err.println(project.getEmployees());
			}
			System.err.println("=================================================================================");

		} catch ( final Exception ex ) {
			
			logger.error("Error executing JPA example 1", ex);
			
		} finally {
			
			logger.info("Stopping context...");
			
			if ( context != null ) {
				
				context.close();
			}
			
		}
	}
}
