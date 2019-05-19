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
import com.sca.model.Admin;
import com.sca.model.Apparal;
import com.sca.model.Book;
import com.sca.service.AdminService;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		return "Admin controller is working";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addAdmin() {
		return adminService.addAdmin();
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
		try {
			return new ResponseEntity<String>(adminService.adminLogin(admin), HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "add-book", method = RequestMethod.POST)
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		try {
			return new ResponseEntity<String>(adminService.addBook(book), HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "add-apparal", method = RequestMethod.POST)
	public ResponseEntity<String> addApparal(@RequestBody Apparal apparal) {
		try {
			return new ResponseEntity<String>(adminService.addApparal(apparal), HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "product/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProductPrice(@PathVariable("productId") int productId, @RequestBody int price) {
		try {
			return new ResponseEntity<String>(adminService.updateProductPrice(productId, price), HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String adminLogout() {
		UserSession.adminUserName = null;
		return "Admin is loggedout Sucessfully";
	}
	
	@RequestMapping(value = "all-users", method = RequestMethod.GET)
	public ResponseEntity<List<String>> allUsers(){
		try {
			return new ResponseEntity<List<String>>(adminService.totalUsers(), HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
