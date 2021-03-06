
package org.dp80.jaxrs.ex1.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer", namespace = "http://www.demo.com/customer")
public class Customer {
	
	private Long	id;
	
	private String	name;
	
	private String	address;
	
	private Date	startDate;
	
	private Boolean	permanent;
	
	/**
	 * @return the address
	 */
	public String getAddress() {
	
		return this.address;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
	
		return this.id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
	
		return this.name;
	}
	
	/**
	 * @return the permanent
	 */
	public Boolean getPermanent() {
	
		return this.permanent;
	}
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
	
		return this.startDate;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
	
		this.address = address;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
	
		this.id = id;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
	
		this.name = name;
	}
	
	/**
	 * @param permanent the permanent to set
	 */
	public void setPermanent(Boolean permanent) {
	
		this.permanent = permanent;
	}
	
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
	
		this.startDate = startDate;
	}
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Customer [id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", startDate=" + this.startDate + ", permanent=" + this.permanent + "]";
	}
	
}
