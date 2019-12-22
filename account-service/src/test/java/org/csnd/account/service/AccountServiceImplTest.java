package org.csnd.account.service;

import org.csnd.account.models.Account;
import org.csnd.account.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class AccountServiceImplTest {

	private AccountService service;

	@Autowired
	public void setRepository(AccountService service) {
		this.service = service;
	}

	@Test
	void findByName() {
		assertNotNull(service.findByName("demo"));
	}

	@Test
		//403
	void create() {
		User user = new User();
		user.setUsername("test");
		Account account = service.create(user);
		assertEquals(user.getUsername(), account.getName());
		assertNotNull(account.getLastSeen());
	}

	@Test
	void saveChanges() {
		Account account = service.findByName("demo");
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS");
		account.setLastSeen(time);
		service.saveChanges(account);
		assertEquals(time.format(formatter), service.findByName("demo").getLastSeen().format(formatter));
	}
}