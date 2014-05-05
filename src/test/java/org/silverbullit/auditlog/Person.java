package org.silverbullit.auditlog;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.silverbullit.auditlog.annotation.Auditable;

@Entity(name = "Person")
public class Person extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Auditable
	@Column
	private String firstname;

	@Column
	@Auditable
	private String lastname;

	@Auditable
	@OneToOne(cascade = { CascadeType.ALL })
	private Address address;

	public Address getAddress() {
		return this.address;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public long getId() {
		return this.id;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}
}
