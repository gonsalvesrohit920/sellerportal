package com.springau.sellerportal.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.Documents;
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
		try {
		int sellerId = dao.saveSeller(seller);
		seller.setId(sellerId);
		seller.getDocuments().setSellerId(sellerId);
		dao.saveSellerDocuments(seller.getDocuments());
		seller.setExists(false);
		}
		catch (Exception e) {
			seller.setExists(true);
	}
		return seller;
		
	}
	
	public List<Seller> getPendingSellerDetails(){
		return dao.getPendingSellerDetails();
	}


	public List<String> getCategory(int sid) {
		return dao.getCategory(sid);
	}
	
	
public boolean savePanImage(int sellerId, byte[] panImage) {
		
		dao.savePanImage(sellerId, panImage);
		
		return true;
		
	}
	
	public boolean saveGstinImage(int sellerId, byte[] gstinImage) {
		
		System.out.println(gstinImage.length);
		dao.saveGstinImage(sellerId, gstinImage);
		
		return true;
		
	}
	
	

	public String getPanImage(int sellerId) {
		
		String panImage;
		Documents documents = dao.getPanImage(sellerId);
		try {
			panImage = Base64.getEncoder().withoutPadding().encodeToString(documents.getPanImage());
			
		return panImage;
		}
		catch (Exception e) {
			return "";
		}
	}


	public String getGstinImage(int sellerId) {
		String panImage;
		
		Documents documents = dao.getPanImage(sellerId);
		
		try {
			panImage = Base64.getEncoder().withoutPadding().encodeToString(documents.getGstInImage());

			return panImage;
		}
		catch (Exception e) {
			return "";
		}
	}
	
	
	public Seller getSellerByEmail(String email) {
		try {
			return dao.getSellerByEmail(email);
		}
		catch (Exception e) {
			return new Seller();
		}
	}


}
