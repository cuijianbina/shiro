package com.trs.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trs.shiro.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findUser(User user){
		String name = user.getName();
		String password = user.getPassword();
		return userRepository.findByNameAndPassword(name, password);
	}
	
	public User findByUsername(String name){
		return userRepository.findByName(name);
	}

}
