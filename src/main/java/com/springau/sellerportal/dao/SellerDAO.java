package com.springau.sellerportal.dao;

import java.util.List;

import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.Seller;

public interface SellerDAO {
	
	Seller getSellerByEmail(String email);
	int saveSeller(Seller seller);
	Seller getSellerDetailsByEmail(String email);
	Seller updateSeller(int sellerId, Seller seller);
	Documents saveSellerDocuments(Documents documents);
	List<Seller> getPendingSellerDetails();
	
}
