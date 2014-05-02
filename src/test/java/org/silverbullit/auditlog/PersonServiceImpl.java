package org.silverbullit.auditlog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	@AuditionTransaction
	@Transactional
	public void updateName(Person person, String firstname, String lastname) {
		person.setFirstname(firstname);
		person.setLastname(lastname);		
		personDao.update(person);
	}

	@Transactional
	@Override
	public void store(Person person) {
		personDao.persist(person);
	}

	@Transactional
	@Override
	public List<Person> findAll() {
		return personDao.findAll();
	}

}
