package com.springau.sellerportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO dao;
	
	
	public List<Product> getAllProducts(){
		
		return dao.getAllProducts();
	}

}
