package com.geektext.service;

import java.util.Set;

import com.geektext.domain.User;
import com.geektext.domain.security.PasswordResetToken;
import com.geektext.domain.security.UserRole;

public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
