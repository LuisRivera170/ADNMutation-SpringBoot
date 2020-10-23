package com.springboot.mutation.api.service;

import com.springboot.mutation.api.entity.User;

public interface IUserService {
	
	public User findByUsername(String username);

}
