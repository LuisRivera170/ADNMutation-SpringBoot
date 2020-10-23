package com.springboot.mutation.api.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.mutation.api.dao.IUserDao;

@Service
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.springboot.mutation.api.entity.User user = findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Error en login: no existe el usuario " + username + " en el sistema");
		}
		
		List<GrantedAuthority> authorities = user.getRole()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}
	
	@Override
	@Transactional(readOnly = true)
	public com.springboot.mutation.api.entity.User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
