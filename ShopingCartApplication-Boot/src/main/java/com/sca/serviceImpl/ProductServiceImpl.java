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
import com.sca.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	


	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<List> getAllProduct(Long userId) throws ServiceException {
		if (userId == null) {
			throw new ServiceException("Authenticatin required.");
		}
		List<List> products = new ArrayList<>();
		List<Book> productBook = getSession().createQuery("FROM Book").list();
		List<Apparal> productApparal = getSession().createQuery("FROM Apparal").list();
		products.add(productBook);
		products.add(productApparal);
		return products;
	}

	@Override
	public Product searchById(Long userId, int productId) throws ServiceException {
		if (userId == null) {
			throw new ServiceException("Authenticatin required.");
		}
		// TODO Auto-generated method stub
		List<Long> count = getSession().createQuery("SELECT COUNT(*) FROM Product").list();
		if (count.get(0) < productId) {
			throw new ServiceException("Product Not found with the product Id: " + productId);
		}
		List<Product> product = getSession().createQuery("FROM Product where productId = ?").setParameter(0, productId)
				.list();
		return product.get(0);
	}

	@Override
	public Product searchByName(Long userId, String prodName) throws ServiceException {
		if (userId == null) {
			throw new ServiceException("Authenticatin required.");
		}
		List<Product> product = getSession().createQuery("from Product where prodName = ?").setParameter(0, prodName)
				.list();
		if (product.isEmpty()) {
			throw new ServiceException("Product " + prodName + " not found.");
		}
		return product.get(0);
	}

	@Override
	public List<Book> catagoryBook(Long userId) throws ServiceException {
		if (userId == null) {
			throw new ServiceException("Authenticatin required.");
		}
		List<Book> books = getSession().createQuery("FROM Book").list();
		return books;
	}

	@Override
	public List<Apparal> catagoryApparal(Long userId) throws ServiceException {
		if (userId == null) {
			throw new ServiceException("Authenticatin required.");
		}
		// TODO Auto-generated method stub
		List<Apparal> apparals = getSession().createQuery("FROM Apparal").list();
		return apparals;
	}

	@Override
	public void autoAddProduct() {

		Book book = new Book();
		Apparal app = new Apparal();

		book.setProdName("C for Computer Science");
		book.setPrice(500);
		book.setAuther("Dennis Ritchie");
		book.setGenre("Programming");
		book.setPublications("unknown");

		app.setProdName("Pant");
		app.setDesign("Manish Malhotra");
		app.setBrand("Something");
		app.setPrice(1000);
		app.setType("Jeans");
		Book book1 = new Book();
		Apparal app1 = new Apparal();

		book1.setProdName("Harry Potter");
		book1.setPrice(600);
		book1.setAuther("J.K Rowling");
		book1.setGenre("Novel");
		book1.setPublications("unknown");

		app1.setProdName("T-Shirt");
		app1.setDesign("Levies Jeans");
		app1.setBrand("Levies");
		app1.setPrice(3999);
		app1.setType("Shirt");

		getSession().save(book);
		getSession().save(app);
		getSession().save(book1);
		getSession().save(app1);

	}

}
