package org.silverbullit.auditlog;

import javax.persistence.Entity;

@Entity
public class Address extends AuditableEntity {

	private static final long serialVersionUID = 1L;

	@Auditable
	private String street;
	
	@Auditable
	private Integer zip;
	
	@Auditable
	private String city;
	
	@Auditable
	private String country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
