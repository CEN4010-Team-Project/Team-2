package com.geektext.repository;

import org.springframework.data.repository.CrudRepository;

import com.geektext.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
