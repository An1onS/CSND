package org.csnd.account.client;

import org.csnd.account.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("auth-service")
public interface AuthServiceClient {
	@RequestMapping(method = RequestMethod.POST, value = "/uaa/users/", consumes = MediaType.APPLICATION_JSON_VALUE)
	void createUser(User user);
}
