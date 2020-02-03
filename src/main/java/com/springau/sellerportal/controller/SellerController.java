package com.springau.sellerportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.ProductService;
import com.springau.sellerportal.service.SellerService;


/**
 * The Class SellerController. to map seller requests
 * @author Rohit Gonsalves
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	
	/** The seller service. */
	@Autowired
	SellerService sellerService;
	
	/** The product service. */
	@Autowired
	ProductService productService;
	
	/**
	 * Dummy method.
	 *
	 * @return the response
	 */
	@GetMapping
	public String getResponse() {
		return "Hello";
	}
	
	/**
	 * Login seller.
	 *
	 * @param loginData the login data
	 * @return the seller Object
	 */
	@PostMapping(path = "/login")
	public Seller loginSeller(@RequestBody LoginData loginData) {

		return sellerService.validateLogin(loginData);
	}
	
	/**
	 * Gets the all products.
	 *
	 * @return the all products
	 */
	@GetMapping(path = "/products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	@GetMapping(path = "/products/AddProductView/{Catname}")
	public List<CategoryQuestion> getAllCategroy(@PathVariable("Catname") String categoryname){
		return productService.getAllProductAttributes(categoryname);
	}
	@PostMapping(path = "/products/AddProduct")
	public int AddProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return 0;
	}
	@GetMapping(path="/products/GetProducts/{sellerId}")
	public List<Product> getSellerProductList(@PathVariable("sellerId") int sellerId) {
		List<Product> productList=productService.getSellerProductList(sellerId);
		return productList;
	}
	@PostMapping(path="/product/UpdateProduct")
	public void updateProductData(@RequestBody Product product) {
		System.out.println(product);
		productService.updateProductData(product);
	}
	@DeleteMapping(path="/product/DeleteProduct/{productId}")
	public void deleteProductData(@PathVariable("productId") int productId) {
		productService.deleteProduct(productId);
	}
	
	@GetMapping(path="/product/checkstatus")
	public String checkStatus() {
		return productService.checkStatus();
	}
	
}
