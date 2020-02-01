package com.springau.sellerportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.ProductService;
import com.springau.sellerportal.service.SellerService;


/**
 * The Class SellerController. to map seller requests
 * @author Rohit Gonsalves
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	
	/** The seller service. */
	@Autowired
	SellerService sellerService;
	
	/** The product service. */
	@Autowired
	ProductService productService;
	
	/**
	 * Gets the response.
	 *@author Rohit Gonsalves
	 * @return the response
	 */
	@GetMapping
	public String getResponse() {
		return "Hello";
	}
	
	/**
	 * Login seller.
	 *
	 * @param loginData the login data
	 * @return the seller Object
	 */
	@PostMapping(path = "/login")
	public Seller loginSeller(@RequestBody LoginData loginData) {
		Seller seller = sellerService.validateLogin(loginData);
		System.out.println(seller.toString());
		return seller;
	}
	
	/**
	 * Gets the all products.
	 *
	 * @return the all products
	 */
	@GetMapping(path = "/products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}

}
