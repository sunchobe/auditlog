package org.silverbullit.auditlog;

import java.util.List;

public interface PersonService {

	void updateName(Person person, String firstname, String lastname);

	void store(Person person);

	List<Person> findAll();
	
}
