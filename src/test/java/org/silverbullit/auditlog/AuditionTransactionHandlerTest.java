package org.silverbullit.auditlog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context-test.xml" })
public class AuditionTransactionHandlerTest {

	@Test
	public void testSomething() {
		System.out.println("testing");
	}
}