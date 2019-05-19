package com.sca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sca.model.User;
import com.sca.model.User;
import com.sca.service.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String testUserController() {
		return "UserController is working";
	}

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable("userId") Long userId) {
		return userService.user(userId);
	}

}
