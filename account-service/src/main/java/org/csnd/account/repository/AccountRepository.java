package org.csnd.account.repository;

import org.csnd.account.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
	Account findByName(String name);
}
