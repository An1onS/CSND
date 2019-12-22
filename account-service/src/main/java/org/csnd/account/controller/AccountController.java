package org.csnd.account.controller;

import org.csnd.account.models.Account;
import org.csnd.account.models.User;
import org.csnd.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

	private AccountService accountService;

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createNewAccount(@RequestBody User user) {
		return accountService.create(user);
	}

	@RequestMapping(path = "/{userName}")
	public Account getAccount(@PathVariable String userName) {
		return accountService.findByName(userName);
	}

	@RequestMapping(path = "/current", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateAccount(@RequestBody Account account) {
		accountService.saveChanges(account);
	}
}
