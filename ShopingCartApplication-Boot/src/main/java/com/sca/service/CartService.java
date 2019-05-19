package com.sca.service;

import com.sca.dto.MyCart;
import com.sca.exception.ServiceException;

public interface CartService {
	
	public String addProductToCart(Long userId, int prodId) throws ServiceException;
	public String removeAllProductFromCart(Long userId) throws ServiceException;
	public String removeProductFromCart(Long userId, int prodId) throws ServiceException;
	public MyCart viewMyCart(Long userId) throws ServiceException;
	public  String changeQuantityOfProductByNumber(int quantityOfProduct, int productId, Long userId) throws ServiceException;
}
