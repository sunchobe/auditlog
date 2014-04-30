package org.silverbullit.auditlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	@AuditionTransaction
	public void updateName(Person person, String firstname, String lastname) {
		person.setFirstname(firstname);
		person.setLastname(lastname);
		
		personDao.persist(person);
	}

}
