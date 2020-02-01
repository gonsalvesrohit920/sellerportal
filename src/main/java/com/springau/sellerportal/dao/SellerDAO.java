package com.springau.sellerportal.dao;

import com.springau.sellerportal.model.Seller;

public interface SellerDAO {
	
	Seller getSellerByEmail(String email);
	Seller saveSeller(Seller seller);
	Seller getSellerDetails(String email);
	Seller updateSeller(int sellerId, Seller seller);
	
}
