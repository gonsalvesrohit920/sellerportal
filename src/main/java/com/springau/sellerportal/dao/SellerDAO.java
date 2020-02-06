package com.springau.sellerportal.dao;

import java.util.List;

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
	Documents saveSellerDocuments(Documents documents);
	List<Seller> getPendingSellerDetails();
	List<String> getCategory(int sid);
	Documents getPanImage(int sellerId);
	boolean saveGstinImage(int sellerId, byte[] gstinImage);
	boolean savePanImage(int sellerId, byte[] panImage);
	void deleteSeller(int sid);
}
