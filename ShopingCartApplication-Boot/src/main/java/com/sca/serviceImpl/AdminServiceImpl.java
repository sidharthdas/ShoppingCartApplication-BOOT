package com.sca.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sca.dto.UserSession;
import com.sca.exception.ServiceException;
import com.sca.model.Admin;
import com.sca.model.Apparal;
import com.sca.model.Book;
import com.sca.model.Product;
import com.sca.model.User;
import com.sca.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String addBook(Book book) throws ServiceException {
		if (UserSession.adminUserName.equals("admin")) {
			List<Product> products = getSession().createQuery("FROM Product WHERE prodName = ? ")
					.setParameter(0, book.getProdName()).list();
			if (products.isEmpty()) {
				getSession().save(book);
				return "New item added.";
			}
			return "Item with the name is already in the Prodduct section.";
		}
		throw new ServiceException("Need admin access.");
	}

	@Override
	public String addApparal(Apparal apparal) throws ServiceException {
		if (UserSession.adminUserName.equals("admin")) {
			List<Product> products = getSession().createQuery("FROM Product WHERE prodName = ? ")
					.setParameter(0, apparal.getProdName()).list();
			if (products.isEmpty()) {
				getSession().save(apparal);
				return "New item added.";
			}
			return "Item with the name is already in the Prodduct section.";
		}
		throw new ServiceException("Need admin access.");
	}

	@Override
	public String updateProductPrice(int productId, int price) throws ServiceException {
		if (UserSession.adminUserName.equals("admin")) {

			List<Product> products = getSession().createQuery("FROM Product WHERE productId = ?")
					.setParameter(0, productId).list();
			if (products.get(0) == null) {
				return "no product is associated with id: " + productId;
			}
			products.get(0).setPrice(price);
			getSession().update(products.get(0));
			return "The price is updated for the product - " + products.get(0).getProdName();	
		}
		throw new ServiceException("Need admin access.");
	}

	@Override
	public String deleteProduct() throws ServiceException {
		if (UserSession.adminUserName.equals("admin")) {

		}
		throw new ServiceException("Need admin access.");
	}

	@Override
	public String adminLogin(Admin admin) throws ServiceException {
		// TODO Auto-generated method stub
		List<Admin> admins = getSession().createQuery("FROM Admin where adminUserName = ?")
				.setParameter(0, admin.getAdminUserName()).list();
		if (admins.isEmpty()) {
			throw new ServiceException("Not a admin.");
		} else if (admins.get(0).getAdminPassword().equals(admin.getAdminPassword())) {
			UserSession.adminUserName = admin.getAdminUserName();
			return "logged in sucessfully";
		}
		return "Password didnt match";
	}

	@Override
	public String addAdmin() {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.setAdminUserName("admin");
		admin.setAdminPassword("admin123");
		getSession().save(admin);
		return "Added";
	}

	@Override
	public List<String> totalUsers() throws ServiceException{
		if (UserSession.adminUserName.equals("admin")) {
		// TODO Auto-generated method stub
		List<String> userNames = new ArrayList<>();
		List<User> users = getSession().createQuery("FROM User").list();
		for(User u : users) {
			userNames.add(u.getUserName());
		}
		return userNames;
		}
		throw new ServiceException("Need admin access.");
	}

}
