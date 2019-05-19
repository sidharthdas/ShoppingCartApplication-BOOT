package com.sca.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sca.exception.ServiceException;
import com.sca.model.Apparal;
import com.sca.model.Book;
import com.sca.model.Product;
import com.sca.model.User;
import com.sca.service.WishListService;

@Service
@Transactional
public class WishListServiceImpl implements WishListService {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String addToWishList(Long userId, int productId) throws ServiceException {
		if (userId == null) {
			throw new ServiceException("Authentication required");
		}
		List<User> users = getSession().createQuery("From User WHERE userId = ?").setParameter(0, userId).list();
		List<Product> products = getSession().createQuery("FROM Product where productId = ?").setParameter(0, productId)
				.list();
		if (users.get(0).getWishList().getProducts().contains(products.get(0))) {
			return "Already in the wishlist.";
		}
		List<Book> book = getSession().createQuery("FROM Book where productId = :productId")
				.setParameter("productId", productId).list();
		if (book.isEmpty()) {
			List<Apparal> apparal = getSession().createQuery("FROM Apparal where productId = :productId")
					.setParameter("productId", productId).list();
			users.get(0).getWishList().getProducts().add(apparal.get(0));
			getSession().update(users.get(0));
			return "Sucessfully added to wishlist.";
		}

		users.get(0).getWishList().getProducts().add(book.get(0));
		getSession().update(users.get(0));
		return "Sucessfully added to wishlist.";
	}

	@Override
	public String removeProductFromWishList(Long userId, int productId) throws ServiceException {
		if (userId == null) {
			throw new ServiceException("Authentication required");
		}
		List<User> users = getSession().createQuery("FROM User WHERE userId = :userId").setParameter("userId", userId).list();
		List<Book> books = getSession().createQuery("FROM Book where productId = :productId").setParameter("productId", productId).list();
		if(books.isEmpty()) {
			List<Apparal> apparals = getSession().createQuery("FROM Apparal where productId = :productId").setParameter("productId", productId).list();
			users.get(0).getWishList().getProducts().remove(apparals.get(0));
			getSession().update(users.get(0));
			return "Removed from the wishlist";
		}
		users.get(0).getWishList().getProducts().remove(books.get(0));
		getSession().update(users.get(0));
		return "removed from the wishlist";
	} 

	@Override
	public String removeAllProductFromWishList(Long userId) throws ServiceException {
		// TODO Auto-generated method stub
		if(userId == null) {
			throw new ServiceException("Authentication required.");
		}
		List<User> users = getSession().createQuery("FROM User WHERE userId = :userId").setParameter("userId", userId).list();
		users.get(0).getWishList().setProducts(null);;
		return "Empty";

	}

	@Override
	public List<List> myWishList(Long userId) throws ServiceException {
		// TODO Auto-generated method stub
		if (userId == null) {
			throw new ServiceException("Authentication required.");
		}
		List<List> products = new ArrayList<List>();
		List<User> users = getSession().createQuery("FROM User WHERE userId = ?").setParameter(0, userId).list();
		System.out.println(users.get(0).getWishList().getProducts());
		products.add((List) users.get(0).getWishList().getProducts());
		
		return products;
	}

}
