package com.springau.controllerTest;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springau.sellerportal.controller.SellerController;
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Contact;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.MailService;
import com.springau.sellerportal.service.ProductService;
import com.springau.sellerportal.service.SellerService;
import com.springau.sellerportal.utility.PasswordHash;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class SellerControllerTest {
	
	@InjectMocks
	SellerController sellerController;
	
	
	@Mock
	SellerService service;
	
	@Mock
	ProductService productService;
	
	@Mock
	MailService mailService;
	
	@Mock
	MultipartHttpServletRequest httpServletRequest;
	
	Documents docs;
	
	Documents d2;
	
	Seller s1, nonExistents1, invalids1;
	
	Contact c1;
	
	Contact c2;
	
	Seller s2;
	
	Documents d1;
	
	
	LoginData loginData,loginDataMD5;
	
	Map<String, String> jsonMap = new HashMap<>();
	
	List<String> productImages = new ArrayList<String>();
	
	Seller seller = new Seller();
	Product product = new Product();
	List<Product> productList = new ArrayList<>();
	List<Integer> integerList = new ArrayList<Integer>();
	List<String> categoryList = new ArrayList<>();
	List<CategoryQuestion> categoryQuestionList = new ArrayList<>();
	List<byte[]> byteList = new ArrayList<byte[]>();
	
	@Before
	public void init() {
		
		jsonMap.put("content", "");
		
		s1 = new Seller();
		s1.setId(1);
		s1.setName("abc");
		s1.setEmail("abc@gmail.com");
		s1.setPassword("xyz");
		s1.setValid(true);
		s1.setApplicationStatus("Accepted");
		s1.setExists(true);
		s1.setDocuments(d1);
		s1.setContact(c1);
		
		c1 = new Contact();
		c1.setStreet("xyz");
		c1.setCity("def");
		c1.setPhoneNo("4503874234");
		c1.setPincode(876387);
		
		
		d1 = new Documents();
		d1.setSellerId(1);
		d1.setPanNo("GHQWE4567D");
		d1.setGstInNo("28GHQWE4567D1CS");
		d1.setGstInImageType(".jpeg");
		d1.setPanNo(".jpeg");
		d1.setGstInImage(null);
		d1.setPanImageType(null);
		
		
		loginData = new LoginData();
		loginData.setUsername(s1.getEmail());
		loginData.setPassword(s1.getPassword());
		
		
		loginDataMD5 = new LoginData();
		loginDataMD5.setUsername(s1.getEmail());
		loginDataMD5.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		
		
		s2 = new Seller();
		s2.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		
	}
	
	@Test
	public void testSellerValidateLogin() {
		s2 = new Seller();
		s2.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		when(service.validateLogin(loginDataMD5)).thenReturn(s2);
		Assert.assertEquals(s2.getPassword(), sellerController.loginSeller(loginDataMD5).getPassword());
	}
	
	
	@Test
	public void testSendMail() {
		Assert.assertEquals(sellerController.getResponse(), "Hello");
	}
	
	@Test
	public void testGetFile() {
		Assert.assertEquals(sellerController.getFile(httpServletRequest), "hello");
	}
	
	@Test
	public void testValidateUserSession() {
		when(service.validateLogin(loginDataMD5)).thenReturn(s2);
		Assert.assertEquals(s2.getPassword(), sellerController.validateSellerSession(loginDataMD5).getPassword());
	}
	
	@Test
	public void testGetProductImages() {
		when(productService.getProductImages(anyInt())).thenReturn(productImages);
		Assert.assertEquals(sellerController.getProductImages(1), productImages);
	}
	
	
	@Test
	public void testGetGstInImage() {
		when(service.getGstinImage(anyInt())).thenReturn("");
		Assert.assertEquals("", sellerController.getGstinImage(1).get("content"));
	}
	
	@Test
	public void testGetPanImage() {
		when(service.getPanImage(anyInt())).thenReturn("");
		
		Assert.assertEquals("", sellerController.getPanImage(1).get("content"));
	}
	
	@Test
	public void testValidateSellerSession() {
		when(service.validateLogin(any(LoginData.class))).thenReturn(seller);
		Assert.assertEquals(sellerController.validateSellerSession(loginData), seller);
	}
	
	@Test
	public void testGetAllProducts() {
		when(productService.getAllProducts()).thenReturn(productList);
		Assert.assertEquals(sellerController.getAllProducts(), productList);
	}
	
	@Test
	public void testGetAllCategory() {
		when(productService.getAllProductAttributes(anyString())).thenReturn(categoryQuestionList);
		Assert.assertEquals(sellerController.getAllCategroy("Mobile"), categoryQuestionList);
	}
	
	@Test
	public void testAddProduct() {
		when(productService.addProduct(any(Product.class))).thenReturn(integerList);
		Assert.assertEquals(sellerController.addProduct(product), integerList);
	}
	
	@Test
	public void testGetSellerProductList() {
		when(productService.getSellerProductList(anyInt())).thenReturn(productList);
		Assert.assertEquals(sellerController.getSellerProductList(2), productList);
	}
	
	@Test
	public void testUpdateProductData() {
		when(productService.updateProductData(any(Product.class))).thenReturn(10);
		Assert.assertEquals(sellerController.updateProductData(product), 10);
	}
	
	@Test
	public void testDeleteProductData() {
		when(productService.deleteProduct(anyInt())).thenReturn(1);
		Assert.assertEquals(sellerController.deleteProductData(1), 1);
	}
	
	@Test
	public void testCheckStatus() {
		when(productService.checkStatus(anyInt())).thenReturn("Passed");
		Assert.assertEquals(sellerController.checkStatus(1), "Passed");
	}
	
	@Test
	public void testSignupSeller() {
		when(service.saveSeller(any(Seller.class))).thenReturn(seller);
		Assert.assertEquals(sellerController.signupSeller(seller), seller);
	}
	
	@Test
	public void testGetCategory() {
		when(service.getCategory(anyInt())).thenReturn(categoryList);
		Assert.assertEquals(sellerController.getCategory("1"), categoryList);
	}
	
	@Test
	public void testAddProductImages() throws IOException {
		when(productService.saveProductImages(integerList, byteList)).thenReturn(true);
		Assert.assertEquals(sellerController.addProductImages(httpServletRequest), false);
	}
	

}
