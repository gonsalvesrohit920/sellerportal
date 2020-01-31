package com.springau.sellerportal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getResponse() {
		return "Hello";
	}

}
