package org.silverbullit.auditlog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name="Person")
public class Person extends AuditableEntity {

	private static final long serialVersionUID = 1L;

	@Column
	@Auditable
	private String firstname;
	
	@Column
	@Auditable
	private String lastname;
	
	@Transient
	@Auditable
	private Address address;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
