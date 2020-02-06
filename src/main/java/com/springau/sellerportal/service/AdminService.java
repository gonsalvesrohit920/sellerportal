package com.springau.sellerportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.utility.PasswordHash;

@Service
@PropertySource("classpath:admin.properties")
public class AdminService {
	
	private static final String ADMIN_USERNAME = "admin.username";
	private static final String ADMIN_PASS = "admin.password";
	
	
	@Autowired
	Environment environment;
	
	@Autowired
	SellerDAO dao;
	
	
	public Seller validateAdmin(LoginData data) {
		Seller seller = new Seller();
		
		String username = this.environment.getProperty(ADMIN_USERNAME);
		String password = this.environment.getProperty(ADMIN_PASS);
		
		password = PasswordHash.getMd5Hash(password);
		
		if(!data.getUsername().equals(username) || !data.getPassword().equals(password)) {
			
			seller.setExists(false);
			seller.setValid(false);
		}
		
		seller.setEmail(data.getUsername());
		seller.setPassword(data.getPassword());
		
		return seller;
	}
	public void deleteSeller(int sid) {
		dao.deleteSeller(sid);
	}
}
