
package org.dp80.jpa.ex1.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long				id;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department			department;
	
	@OneToOne
	@JoinColumn(name = "PARKING_ID")
	private ParkingSpace		parkingSpace;
	
	@ManyToMany
	@JoinTable(name = "EMPLOYEE_PROJECT",
			joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
			inverseJoinColumns = @JoinColumn(name = "PROJECT_ID"))
	private Collection<Project>	projects	= new HashSet<Project>();
	
	@Column(name = "NAME")
	private String				name;
	
	@Column(name = "SALARY")
	private Long				salary;
	
	@Embedded 
	private Address address;
	
	public Employee() {
	
	}
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", address="+ address +"]";
	}
	
	public Long getId() {
	
		return id;
	}
	
	public void setId(Long id) {
	
		this.id = id;
	}
	
	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	public Long getSalary() {
	
		return salary;
	}
	
	public void setSalary(Long salary) {
	
		this.salary = salary;
	}
	
	/**
	 * @return the department
	 */
	public Department getDepartment() {
	
		return department;
	}
	
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
	
		this.department = department;
	}
	
	/**
	 * @return the parkingSpace
	 */
	public ParkingSpace getParkingSpace() {
	
		return parkingSpace;
	}
	
	/**
	 * @param parkingSpace the parkingSpace to set
	 */
	public void setParkingSpace(ParkingSpace parkingSpace) {
	
		this.parkingSpace = parkingSpace;
	}
	
	/**
	 * @return the projects
	 */
	public Collection<Project> getProjects() {
	
		return projects;
	}
	
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Collection<Project> projects) {
	
		this.projects = projects;
	}

	
	/**
	 * @return the address
	 */
	public Address getAddress() {
	
		return address;
	}

	
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
	
		this.address = address;
	}
}
