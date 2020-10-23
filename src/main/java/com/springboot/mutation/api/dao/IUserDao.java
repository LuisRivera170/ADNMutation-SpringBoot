package com.springboot.mutation.api.dao;

import org.springframework.data.repository.CrudRepository;
import com.springboot.mutation.api.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {

	public User findByUsername(String username);
	
}
