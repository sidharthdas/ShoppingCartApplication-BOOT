package com.sca.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sca.dto.UserDetails;
import com.sca.exception.ServiceException;
import com.sca.model.Cart;
import com.sca.model.User;
import com.sca.model.WishList;
import com.sca.service.UserSignupService;

@Service
@Transactional
public class UserSignupServiceImpl implements UserSignupService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession(); 
	}

	public Boolean checkUserName(String userName) {
		String userNameFromDB = getSession().createQuery("SELECT userName FROM User where userName = ?")
				.setParameter(0, userName).list().toString();
		if (userNameFromDB.equals("[]")) {
			return false;
		}
		return true;
	}


	public Boolean confirmPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public Long addUser(UserDetails userDetail) throws ServiceException {
		if (checkUserName(userDetail.getUserName()) == false) {
			Boolean confirmPass = confirmPassword(userDetail.getPassword(), userDetail.getConfirmPassword());
			if (confirmPass) {

				User user = new User();
				Cart cart = new Cart();
				WishList wishList = new WishList();
				user.setUserName(userDetail.getUserName());
				user.setPassword(userDetail.getPassword());
				user.setCart(cart);
				user.setWishList(wishList);
				//cart.getProducts().add(book);
				getSession().save(user);
				getSession().save(cart);
				getSession().save(wishList);
				
				List<Long>userId = getSession().createQuery("SELECT userId FROM User WHERE userName = ?").setParameter(0, user.getUserName()).list();
				
				return userId.get(0); 
			}

			throw new ServiceException("Password didnt match.");
		}
		throw new ServiceException("UserName Already exist.");
	}

	@Override
	public Long loginUser(User user) throws ServiceException {
		if(checkUserName(user.getUserName())) {
			String passwordFromDB = getSession().createQuery("SELECT password FROM User WHERE userName = ?").setParameter(0, user.getUserName()).list().toString();
			String password = passwordFromDB.substring(1, passwordFromDB.length()-1);
			if(password.equals(user.getPassword())) {
				List<Long> userId = getSession().createQuery("SELECT userId FROM User WHERE userName = ?").setParameter(0, user.getUserName()).list();
				return userId.get(0);
			}
			else {
				throw new ServiceException("Passowrd Didnt match");
			}
		}
		//return 0;
		throw new ServiceException("User not found");
		
	}
	
}
