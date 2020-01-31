package com.springau.sellerportal.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springau.sellerportal.model.LoginData;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getResponse() {
		return "Hello";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public Boolean loginSeller(@RequestBody LoginData loginData) {
		return true;
	}

}
