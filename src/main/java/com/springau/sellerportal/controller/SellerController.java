package com.springau.sellerportal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	/** The Constant PAN_IMAGE. */
	private static final String PAN_IMAGE = "panImage";
	
	/** The Constant GST_IMAGE. */
	private static final String GST_IMAGE = "gstImage";
	
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
		mailService.sendEmail("gonsalvesrohit920@gmail.com", "MAil Service test", "TEst Mail Service");
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
	public List<Integer> AddProduct(@RequestBody Product product) {
		
		return productService.addProduct(product);
	}
	@GetMapping(path="/products/GetProducts/{sellerId}")
	public List<Product> getSellerProductList(@PathVariable("sellerId") int sellerId) {
		System.out.print("Hit: " + sellerId);
		List<Product> productList=productService.getSellerProductList(sellerId);
		return productList;
	}
	
	@PostMapping(path = "/product/addProductImages")
	public boolean addProductImages(MultipartHttpServletRequest servletRequest) throws IOException {
		
		Iterator<String> itr = servletRequest.getFileNames();
		
		List<Integer> imageIds = new ArrayList<>();
		List<byte[]> imageDatas = new ArrayList<>();
		
		
		while(itr.hasNext())
		{
			String id = itr.next();
			imageIds.add(Integer.parseInt(id));
			imageDatas.add(servletRequest.getFile(id).getBytes());
			
		}
		
		productService.saveProductImages(imageIds, imageDatas);
		return true;
	}
	
	@PostMapping(path="/product/UpdateProduct")
	public int updateProductData(@RequestBody Product product) {
		System.out.println(product);
		return productService.updateProductData(product);
	}
	@DeleteMapping(path="/product/DeleteProduct/{productId}")
	public int deleteProductData(@PathVariable("productId") int productId) {
		return productService.deleteProduct(productId);
	}
	
	@GetMapping(path="/product/checkstatus/{sellerId}")
	public String checkStatus(@PathVariable("sellerId") int sellerId) {
		return productService.checkStatus(sellerId);
	}
   @PostMapping(path="/product/checkstatus/adminUpdate")
   public void adminUpdateStatus(@RequestBody  String sellerId) {
	   int SellerId = Integer.parseInt(sellerId); 
	   System.out.println(SellerId);
	   productService.updateStatus(SellerId);
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
	
	@GetMapping(path="/category/{sid}")
	public List<String> getCategory(@PathVariable("sid") String sellerId) {
		int sid = Integer.parseInt(sellerId);
		return sellerService.getCategory(sid);
	}
	
	
	@PostMapping(path = "/signup/images/{id}")
	public Seller saveSellerImages(MultipartHttpServletRequest servletRequest,
			@PathVariable("id") int sellerId) throws IOException {
		
		Iterator<String> itr = servletRequest.getFileNames();
		
		while(itr.hasNext())
		{
			System.out.println(itr.next() +  servletRequest.getFile(GST_IMAGE).getSize());
		}
		
		sellerService.savePanImage(sellerId, servletRequest.getFile(PAN_IMAGE).getBytes());
		sellerService.saveGstinImage(sellerId, servletRequest.getFile(GST_IMAGE).getBytes());
		return new Seller();
	}
	
	/**
	 * Gets the pan image.
	 *
	 * @param sellerId the seller id
	 * @return the pan image in string format
	 */
	@GetMapping("/getPanImage/{id}")
	public @ResponseBody Map<String, String> getPanImage(@PathVariable("id")int sellerId) {
		
		Map<String, String> jsonMap = new HashMap<>();

		jsonMap.put("content", sellerService.getPanImage(sellerId));
		
		return jsonMap;
	}
	
	/**
	 * Gets the gstin image.
	 *
	 * @param sellerId the seller id
	 * @return the gstin image
	 */
	@GetMapping("/getGstinImage/{id}")
	public @ResponseBody Map<String, String> getGstinImage(@PathVariable("id")int sellerId) {
		
		Map<String, String> jsonMap = new HashMap<>();

		jsonMap.put("content", sellerService.getGstinImage(sellerId));
		
		return jsonMap;
	}
	
	@GetMapping("/product/getProductImages/{id}")
	public @ResponseBody List<String> getProductImages(@PathVariable("id") int productId){
		
		
		
		return productService.getProductImages(productId);
	}
	

}
