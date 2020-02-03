package com.springau.sellerportal.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.MailService;
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
	
	@Autowired
	MailService mailService;
	
	/**
	 * Dummy method.
	 *
	 * @return the response
	 */
	@GetMapping
	public String getResponse() {
		mailService.crunchifyReadyToSendEmail("gonsalvesrohit920@gmail.com", "MAil Service test", "TEst Mail Service");
		return "Hello";
	}
	
	@PostMapping("/upload")
	public String getFile(MultipartHttpServletRequest httpServletRequest ) {
		
		System.out.println(httpServletRequest.toString());
		
		
		Iterator<String>  itr = httpServletRequest.getFileNames();
		
		int c =  0;
		
		while(itr.hasNext())
		{
			System.out.println(itr.next());
			
		}
		
		return "hello";
	}
	
	/**
	 * Login seller.
	 *
	 * @param loginData the login data
	 * @return the seller Object
	 */
	@PostMapping(path = "/login")
	public Seller loginSeller(@RequestBody LoginData loginData) {

		return sellerService.validateLogin(loginData);
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
