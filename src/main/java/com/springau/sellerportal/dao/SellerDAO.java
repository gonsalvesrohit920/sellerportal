package com.springau.sellerportal.dao;

import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.Seller;

/**
 * @author Rohit Gonsalves
 * Seller Interface with Database
 */
public interface SellerDAO {
	
	Seller getSellerByEmail(String email);
	int saveSeller(Seller seller);
	Seller getSellerDetailsByEmail(String email);
	Seller updateSeller(int sellerId, Seller seller);
	Documents saveSellerDocuments(Documents documents);
	
}
