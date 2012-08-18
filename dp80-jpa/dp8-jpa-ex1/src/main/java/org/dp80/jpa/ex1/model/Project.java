
package org.dp80.jpa.ex1.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long					id;
	
	@ManyToMany(mappedBy = "projects")
	private Collection<Employee>	employees;
	
	@Column(name = "NAME")
	private String					name;
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Project [id=" + id + ", name=" + name + "]";
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
	
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
	
		this.id = id;
	}
	
	/**
	 * @return the employees
	 */
	public Collection<Employee> getEmployees() {
	
		return employees;
	}
	
	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(Collection<Employee> employees) {
	
		this.employees = employees;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
	
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
	
		this.name = name;
	}
	
}
