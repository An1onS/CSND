package org.csnd.account.service;

import org.csnd.account.client.AuthServiceClient;
import org.csnd.account.models.Account;
import org.csnd.account.models.User;
import org.csnd.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	private AuthServiceClient auth;
	private AccountRepository repository;

	@Autowired
	public void setAuth(AuthServiceClient auth) {
		this.auth = auth;
	}

	@Autowired
	public void setRepository(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public Account findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Account create(User user) {
		Account existing = repository.findByName(user.getUsername());
		auth.createUser(user);
		Account account = new Account();
		account.setName(user.getUsername());
		repository.save(account);
		return account;
	}

	@Override
	public void saveChanges(Account changes) {
		if (repository.findByName(changes.getName()) != null)
			repository.save(changes);
	}
}
