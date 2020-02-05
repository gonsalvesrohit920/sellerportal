package com.springau.sellerportal.service;


import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.utility.PasswordHash;

@Service
@PropertySource("classpath:admin.properties")
public class AdminService {
	
	private static final String ADMIN_USERNAME = "admin.username";
	private static final String ADMIN_PASSWORD = "admin.password";
	
	
	@Autowired
	Environment environment;
	
	@Autowired
	Logger logger;
	
	public Seller validateAdmin(LoginData data) {
		Seller seller = new Seller();
		
		String username = this.environment.getProperty(ADMIN_USERNAME);
		String password = this.environment.getProperty(ADMIN_PASSWORD);
		
		password = PasswordHash.getMd5Hash(password);
		logger.info(password);
		logger.info(data.getPassword());
		
		if(!data.getUsername().equals(username) || !data.getPassword().equals(password)) {
			
			seller.setExists(false);
			seller.setValid(false);
		}
		
		seller.setEmail(data.getUsername());
		seller.setPassword(data.getPassword());
		
		return seller;
	}

}
