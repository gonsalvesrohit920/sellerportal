package com.springau.sellerportal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.AdminService;
import com.springau.sellerportal.utility.PasswordHash;

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
	private AdminService adminService;
	
	@PostMapping("/login")
	public Seller validateAdmin(@RequestBody LoginData data){
		
		data.setPassword(PasswordHash.getMd5Hash(data.getPassword()));
		return adminService.validateAdmin(data);
		
	}
	
	@PostMapping("/validate_session")
	public Seller validateAdminSession(@RequestBody LoginData data) {
		return adminService.validateAdmin(data);
	}

	
	
	@GetMapping
	public List<Seller> getPendingSellerDetails() {
		return sellerService.getPendingSellerDetails();
	}

}
