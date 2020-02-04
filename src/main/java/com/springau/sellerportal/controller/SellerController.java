package com.springau.sellerportal.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.MailService;
import com.springau.sellerportal.service.ProductService;
import com.springau.sellerportal.service.SellerService;
import com.springau.sellerportal.utility.PasswordHash;


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
	
	@Autowired
	MailService mailService;
	
	/**
	 * Dummy method to test API is working
	 *
	 * @return the response
	 */
	@GetMapping("/send_mail")
	public String getResponse() {
		mailService.crunchifyReadyToSendEmail("gonsalvesrohit920@gmail.com", "MAil Service test", "TEst Mail Service");
		return "Hello";
	}
	
	@PostMapping("/upload")
	public String getFile(MultipartHttpServletRequest httpServletRequest ) {
		
		System.out.println(httpServletRequest.toString());
		Iterator<String>  itr = httpServletRequest.getFileNames();
		
		while(itr.hasNext())
		{
			String dead = itr.next();
			
			System.out.print(httpServletRequest.getFile(dead).getSize());		
		}
		
		return "hello";
	}
	
	
	
	/**
	 * Login seller.
	 *
	 * @param loginData the login data
	 * @return the seller Object
	 */
	@PostMapping(path = "/login")
	public Seller loginSeller(@RequestBody LoginData loginData) {
		
		System.out.println("Login: " + loginData.toString());
		if(!loginData.isValidate())
			{
					loginData.setPassword(PasswordHash.getMd5Hash(loginData.getPassword()));
			}
		return sellerService.validateLogin(loginData);
	}
	
	
	@PostMapping(path = "/validate_session")
	public Seller validateSellerSession(@RequestBody LoginData loginData) {
		System.out.println(loginData.toString());
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
   @DeleteMapping(path="/adminUpdate/{sellerId}")
   public void adminUpdateStatus(@PathVariable("sellerId") int sellerId) {
	   System.out.println(sellerId);
	   productService.updateStatus(sellerId);
   }
    
	
	
	/**
	 * @author Shashank Jain
	 * @param seller
	 * @return
	 */
	@PostMapping(path = "/signup")
	public Seller signupSeller(@RequestBody Seller seller) {
		return sellerService.saveSeller(seller);
	}
	

}
