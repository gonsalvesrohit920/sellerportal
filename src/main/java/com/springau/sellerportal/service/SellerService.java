package com.springau.sellerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;

@Service
public class SellerService {
	
	@Autowired
	SellerDAO dao;
	
	
	/**
	 * Validate login.
	 *
	 * @param login the loginData
	 * @return true, if successful
	 */
	public boolean validateLogin(LoginData login) {
			
		Seller seller = dao.getSellerByEmail(login.getUsername());
		
		return (seller.getEmail().equals(login.getUsername()) && seller.getPassword().equals(login.getPassword()));
		
	}

}
