package org.silverbullit.auditlog;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.silverbullit.auditlog.annotation.Auditable;

@Entity
public class Address extends BaseEntity {

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

	public String getCity() {
		return this.city;
	}

	public String getCountry() {
		return this.country;
	}

	public String getStreet() {
		return this.street;
	}

	public Integer getZip() {
		return this.zip;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setStreet(final String street) {
		this.street = street;
	}

	public void setZip(final Integer zip) {
		this.zip = zip;
	}

}
