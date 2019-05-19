package com.sca.service;

import com.sca.dto.UserDetails;
import com.sca.exception.ServiceException;
import com.sca.model.User;

public interface UserSignupService {
	
	public Long addUser(UserDetails userDetail) throws ServiceException;
	public Long loginUser(User user) throws ServiceException;

}
