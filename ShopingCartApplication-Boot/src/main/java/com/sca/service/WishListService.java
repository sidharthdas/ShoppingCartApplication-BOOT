package com.sca.service;

import java.util.List;

import com.sca.exception.ServiceException;
import com.sca.model.Product;

public interface WishListService {
	
	public String addToWishList(Long userId,int productId) throws ServiceException;
	public String removeProductFromWishList(Long userId, int productId) throws ServiceException;
	public String removeAllProductFromWishList(Long userId) throws ServiceException;
	public List<List> myWishList(Long userId) throws ServiceException;
 
}
