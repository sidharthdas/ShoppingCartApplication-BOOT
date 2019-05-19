package com.sca.repository;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sca.model.User;


@Repository
@Transactional
public class UserRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public String saveAndFlush(User user) {
		
		getSession().save(user);
		/*getSession().getTransaction().commit();*/
		return "Added Sucessfully!";
	}
	
	public User findOne(Long id) {
		ArrayList<User> user = (ArrayList<User>) getSession().createQuery("from User where userId = ?").setParameter(0, id).list();
	return user.get(0);
	}
}
