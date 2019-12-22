package org.csnd.account.repository;

import org.csnd.account.models.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountRepositoryTest {

	private AccountRepository repository;

	@Autowired
	public void setRepository(AccountRepository repository) {
		this.repository = repository;
	}

	@Test
	void findByName() {
		Account demo = repository.findByName("demo");
		assertEquals("FirstName", demo.getFirstName());
	}
}