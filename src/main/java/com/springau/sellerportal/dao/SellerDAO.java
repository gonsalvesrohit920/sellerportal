package com.springau.sellerportal.dao;

import com.springau.sellerportal.model.Seller;

public interface SellerDAO {
	
	Seller getSellerByEmail(String email);
}
