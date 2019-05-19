package com.sca.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sca.model.User;
import com.sca.model.User;
import com.sca.repository.UserJpaRepository;
import com.sca.repository.UserRepository;
import com.sca.service.UserService;

@Service
public class UserServiceImpl implements UserService {
 
	@Autowired
	private UserJpaRepository userRepository;

	@Override
	public String addUser(User user) {
		userRepository.saveAndFlush(user);
		return "Added Sucessfully";
	}

	@Override
	public User user(Long userId) {
		User user = userRepository.findOne(userId);
		return user;
	}

}
