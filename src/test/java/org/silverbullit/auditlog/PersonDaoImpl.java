package org.silverbullit.auditlog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void persist(Person person) {
		entityManager.persist(person);
		entityManager.flush();
	}
	
	@Override
	public void update(Person person) {
		entityManager.persist(person);
		entityManager.flush();
	}

	@Override
	public List<Person> findAll() {
		return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
	}

}
