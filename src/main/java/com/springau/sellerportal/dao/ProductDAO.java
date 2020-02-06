package com.springau.sellerportal.dao;

import java.util.List;

import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;

public interface ProductDAO {
	public List<Product> getAllProducts();
	public List<Product> getAllSellerProducts(int sellerId);
	public List<Integer> saveProduct(Product product);
	public int deleteProduct(int productId);
	public List<CategoryQuestion> getAllProductAttributes(String categoryname);
	public List<ProductImage> getProductImages(int productId);
	public List<Product> getSellerProductList(int sellerId);
	public int updateProductData(Product product);
	public String chechStatus(int sellerId);
	public void updateStatus(int sellerId);
	Product getProductById(int productId);
	boolean saveProductImages(List<Integer> productImageIds, List<byte[]> productImages);
}
