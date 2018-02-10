package com.geektext.repository;

import org.springframework.data.repository.CrudRepository;

import com.geektext.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

}
