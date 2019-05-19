package com.sca.service;

import java.util.List;

import com.sca.exception.ServiceException;
import com.sca.model.Apparal;
import com.sca.model.Book;
import com.sca.model.Product;

public interface ProductService {
	
	public List<List> getAllProduct(Long userId) throws ServiceException;
	public Product searchById(Long userId, int productId) throws ServiceException;
	public Product searchByName(Long userId, String prodName) throws ServiceException;
	public List<Book> catagoryBook(Long userId) throws ServiceException;
	public List<Apparal> catagoryApparal(Long userId) throws ServiceException;
	public void autoAddProduct();
}
