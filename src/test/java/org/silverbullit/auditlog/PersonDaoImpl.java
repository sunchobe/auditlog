package org.silverbullit.auditlog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void persist(Person person) {
		person.onPostPersist();
	}
	
	@Override
	public void update(Person person) {
		person.onPreUpdate();;
	}

}
