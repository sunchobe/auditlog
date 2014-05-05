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
	@Transactional
	public Person find(final long id) {
		return this.personDao.find(id);
	}

	@Transactional
	@Override
	public List<Person> findAll() {
		return this.personDao.findAll();
	}

	@Transactional
	@AuditionTransaction
	@Override
	public void store(final Person person) {
		this.personDao.persist(person);
	}

	@Override
	@AuditionTransaction
	@Transactional
	public void updateName(Person person, final String firstname, final String lastname) {
		person = this.personDao.find(person.getId());
		person.setFirstname(firstname);
		person.setLastname(lastname);
		this.personDao.update(person);
	}
}
