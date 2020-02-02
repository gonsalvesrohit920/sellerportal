package com.springau.sellerportal.dao;

import java.util.List;
import java.util.Map;

import com.springau.sellerportal.model.CategoryAnswer;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;

public interface ProductDAO {
	public List<Product> getAllProducts();
	public List<Product> getAllSellerProducts(int sellerId);
	public List<Product> saveProduct(Product product);
	public List<Product> updateProduct(Product product);
	public List<Product> deleteProduct(int productId);
	public List<CategoryAnswer> getProductAttributes(int productId);
	public List<ProductImage> getProductImages(int productId);
	public List<ProductImage> saveProductImages(int productId);
}
