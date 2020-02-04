package com.springau.sellerportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO dao;
	
	
	public List<Product> getAllProducts(){
		
		return dao.getAllProducts();
	}
    public List<CategoryQuestion> getAllProductAttributes(String categoryname){
    	
    	return dao.getAllProductAttributes(categoryname);
    }
    public void addProduct(Product product) {
    	dao.saveProduct(product);
    
    }
    public List<Product> getSellerProductList(int sellerId) {
    	return dao.getSellerProductList(sellerId);
    }
    
    public void updateProductData(Product product) {
    	dao.updateProductData(product);
    }
    public void deleteProduct(int productId) {
    	dao.deleteProduct(productId);
    }
	public String checkStatus() {
		return dao.chechStatus();
	}
	public void updateStatus(int sellerId) {
		dao.updateStatus(sellerId);
		
	}
}
