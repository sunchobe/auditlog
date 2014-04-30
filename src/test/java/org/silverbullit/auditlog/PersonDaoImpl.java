package org.silverbullit.auditlog;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void persist(Person person) {
		person.onPostPersist();
	}
	
	@Override
	public void update(Person person) {
		person.onPreUpdate();;
	}

}
