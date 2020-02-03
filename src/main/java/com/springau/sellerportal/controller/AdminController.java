package com.springau.sellerportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.SellerService;

/**
 * @author Shashank Jain
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private SellerService sellerService;
	
	
	@GetMapping
	public List<Seller> getPendingSellerDetails() {
		return sellerService.getPendingSellerDetails();
	}

}
