package com.springau.sellerportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@GetMapping
	public String getResponse() {
		return "Hello";
	}
	
	@PostMapping(path = "/login")
	public String loginSeller(@RequestBody LoginData loginData) {
		
		System.out.print(loginData.toString());
		return "Hi"; //sellerService.validateLogin(loginData);
	}

}
