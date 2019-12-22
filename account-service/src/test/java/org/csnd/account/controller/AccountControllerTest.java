package org.csnd.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.auth.UserPrincipal;
import org.csnd.account.models.Account;
import org.csnd.account.models.User;
import org.csnd.account.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountControllerTest {
	private static final ObjectMapper mapper = new ObjectMapper();
	@InjectMocks
	private AccountController accountController;
	@Mock
	private AccountService accountService;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}

	@Test
	public void createNewAccount() throws Exception {
		final User user = new User();
		user.setUsername("test");
		user.setPassword("password");

		String json = mapper.writeValueAsString(user);
		System.out.println(json);
		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test //REST Client manual test: ok
	public void getAccount() throws Exception {
		final Account account = new Account();
		when(accountService.findByName("demo")).thenReturn(account);
		mockMvc.perform(get("/demo"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("demo"));
	}

	@Test //REST Client manual test: ok
	public void updateAccount() throws Exception {
		final Account account = new Account();
		when(accountService.findByName("demo")).thenReturn(account);
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSS");
		String json = mapper.writeValueAsString(account);
		mockMvc.perform(put("/current").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}
}