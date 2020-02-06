package com.springau.sellerportal.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;

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
    public List<Integer> addProduct(Product product) {
    	return dao.saveProduct(product);
    
    }
    public List<Product> getSellerProductList(int sellerId) {
    	return dao.getSellerProductList(sellerId);
    }
    
    public int updateProductData(Product product) {
    	return dao.updateProductData(product);
    }
    public int deleteProduct(int productId) {
    	return dao.deleteProduct(productId);
    }
	public String checkStatus(int sellerId) {
		return dao.chechStatus(sellerId);
	}
	public void updateStatus(int sellerId) {
		dao.updateStatus(sellerId);
		
	}
	
	public List<String> getProductImages(int productId){
		Map<String, String> jsonMap = new HashMap<>();
		
		List<String> imagesd = new ArrayList<>();
		
		List<ProductImage> images = dao.getProductImages(productId);
		
		for (ProductImage image : images) {
			String imagedata = Base64.getEncoder().withoutPadding().encodeToString(image.getProductImageData());
			imagedata = "data:image/" + image.getImageType().toUpperCase() + ";base64," +  imagedata;
			jsonMap.put(image.getImageId() + "", imagedata);
			
			imagesd.add(imagedata);
		}
		
		return imagesd;
	}
	
	
	public boolean saveProductImages(List<Integer> imageIds, List<byte[]> productImages) {
		
		
		dao.saveProductImages(imageIds, productImages);
		
		return true;
	}
}
