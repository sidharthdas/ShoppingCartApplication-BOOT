package com.sca.service;

import java.util.List;

import com.sca.exception.ServiceException;
import com.sca.model.Admin;
import com.sca.model.Apparal;
import com.sca.model.Book;

public interface AdminService {
	
	public String adminLogin(Admin admin) throws ServiceException;
	
	public String addBook(Book book)  throws ServiceException;
	public String addApparal(Apparal apparal) throws ServiceException;
	public String updateProductPrice(int productId, int price) throws ServiceException;
	public String deleteProduct() throws ServiceException;
	public String addAdmin();
	public List<String> totalUsers() throws ServiceException;

}
