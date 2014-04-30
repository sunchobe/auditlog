package org.silverbullit.auditlog;

public class Person extends AuditableEntity {

	@Auditable
	private String firstname;
	
	@Auditable
	private String lastname;
	
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
