package org.silverbullit.auditlog;

import java.util.List;

public interface PersonDao {

	void persist(Person person);

	void update(Person person);

	List<Person> findAll();
	
}
