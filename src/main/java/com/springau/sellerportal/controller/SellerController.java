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

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public String getResponse() {
		return "Hello";
	}
	
	@PostMapping(path = "/login")
	public Seller loginSeller(@RequestBody LoginData loginData) {
		
		return sellerService.validateLogin(loginData);
	}
	
	@GetMapping(path = "/products")
	public List<Product> getAllProducts(){
		
		
		return productService.getAllProducts();
	}

}
