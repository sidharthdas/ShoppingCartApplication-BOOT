package com.sca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sca.dto.UserSession;
import com.sca.exception.ServiceException;
import com.sca.model.Product;
import com.sca.service.WishListService;

@RestController
@RequestMapping("/api/wishlist/")
public class WishListController {

	@Autowired
	private WishListService wishListService;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		return "Wishlist controller is working.";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseEntity<String> addProductToWishList(@RequestBody String productId) {
		try {
			return new ResponseEntity<String>(
					wishListService.addToWishList(UserSession.userId, Integer.parseInt(productId)), HttpStatus.OK);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  
	}

	@RequestMapping(value = "mywishlist/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeProductFromWishList(@PathVariable("productId") String productId) {
		try {
			return new ResponseEntity<String>(
					wishListService.removeProductFromWishList(UserSession.userId, Integer.parseInt(productId)),
					HttpStatus.OK);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "mywishlist", method = RequestMethod.DELETE)
	public ResponseEntity<String> emptyWishList() {
		try {
			return new ResponseEntity<String>(wishListService.removeAllProductFromWishList(UserSession.userId),
					HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "mywishlist", method = RequestMethod.GET)
	public ResponseEntity<List<List>> myWishList() {
		try {
			return new ResponseEntity<List<List>>(wishListService.myWishList(UserSession.userId), HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
