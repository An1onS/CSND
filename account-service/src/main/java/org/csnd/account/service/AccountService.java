package org.csnd.account.service;

import org.csnd.account.models.Account;
import org.csnd.account.models.User;

public interface AccountService {
	Account findByName(String name);

	Account create(User user);

	void saveChanges(Account changes);
}
