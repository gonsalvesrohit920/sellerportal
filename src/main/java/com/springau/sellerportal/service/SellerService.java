package com.springau.sellerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.utility.PasswordHash;

/**
 * @author Rohit Gonsalves
 *	Seller Service Implements Business logic for seller
 */
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
	public Seller validateLogin(LoginData login) {
			
		Seller seller = dao.getSellerByEmail(login.getUsername());
		seller.setPassword(PasswordHash.getMd5Hash(seller.getPassword())); // Get MD5 Hash
		if(seller.isExists() && !(seller.getEmail().equals(login.getUsername()) && seller.getPassword().equals(login.getPassword()))) {
			seller.setValid(false);
		}
		return seller;
		
	}
	
	
	/**
	 * Save seller.
	 * Save the Given Seller Data to the database
	 * @param seller the Complete Seller Data
	 * @return the seller
	 */
	public Seller saveSeller(Seller seller) {
		
		int sellerId = dao.saveSeller(seller);
		seller.setId(sellerId);
		seller.getDocuments().setSellerId(sellerId);
		dao.saveSellerDocuments(seller.getDocuments());
		return seller;
		
	}

}
