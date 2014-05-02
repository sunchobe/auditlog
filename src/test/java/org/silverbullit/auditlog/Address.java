package org.silverbullit.auditlog;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Address extends AuditableEntity {

	private static final long serialVersionUID = 1L;

	@Column
	@Auditable
	private String street;
	
	@Column
	@Auditable
	private Integer zip;
	
	@Column
	@Auditable
	private String city;
	
	@Column
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
