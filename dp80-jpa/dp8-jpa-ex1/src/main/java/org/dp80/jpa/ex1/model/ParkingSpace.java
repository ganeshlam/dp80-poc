
package org.dp80.jpa.ex1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARKING_SPACE")
public class ParkingSpace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long		id;
	
	@OneToOne(mappedBy = "parkingSpace")
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee	employee;
	
	@Column(name = "LOCATION")
	private String		location;
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "ParkingSpace [id=" + id + ", location=" + location + "]";
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
	 * @return the location
	 */
	public String getLocation() {
	
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
	
		this.location = location;
	}
	
	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
	
		return employee;
	}
	
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
	
		this.employee = employee;
	}
	
}
