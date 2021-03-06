package org.silverbullit.auditlog;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-test.xml" })
public class AuditionTransactionHandlerTest {

	@Autowired
	private PersonService personService;

	@Test
	public void testSomething() {
		Address address = new Address();

		address.setStreet("street");
		address.setZip(21035);
		address.setCity("city");
		address.setCountry("country");

		Person person = new Person();
		person.setAddress(address);
		person.setFirstname("firstname");
		person.setLastname("lastname");

		personService.store(person);

		person = personService.findAll().get(0);
		person = personService.find(person.getId());

		personService.updateName(person, "new firstname", "new lastname");
		
		Assert.assertNotNull(personService.find(1));
		
	}
}