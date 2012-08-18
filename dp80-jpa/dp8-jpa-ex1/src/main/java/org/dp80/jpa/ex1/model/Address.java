
package org.dp80.jpa.ex1.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
@Access(AccessType.FIELD)
public class Address {
	
	@Column(name = "STREET")
	private String	street;
	
	@Column(name = "CITY")
	private String	city;
	
	@Column(name = "STATE")
	private String	state;
	
	@Column(name = "ZIP_CODE")
	private String	zip;
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}


	/**
	 * @return the street
	 */
	public String getStreet() {
	
		return street;
	}

	
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
	
		this.street = street;
	}

	
	/**
	 * @return the city
	 */
	public String getCity() {
	
		return city;
	}

	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
	
		this.city = city;
	}

	
	/**
	 * @return the state
	 */
	public String getState() {
	
		return state;
	}

	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
	
		this.state = state;
	}

	
	/**
	 * @return the zip
	 */
	public String getZip() {
	
		return zip;
	}

	
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
	
		this.zip = zip;
	}
}
