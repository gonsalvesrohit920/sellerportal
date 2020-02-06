package com.springau.serviceTest;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.springau.sellerportal.dao.SellerDAO;
import com.springau.sellerportal.model.Contact;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.SellerService;
import com.springau.sellerportal.utility.PasswordHash;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class SellerServiceTest {
	
	@InjectMocks
	SellerService sellerService;
	
	@Mock
	SellerDAO sellerDAO;
	
	List<String> categoryList = new ArrayList<>();
	
	Documents docs;
	
	Documents d2;
	
	Seller s1, nonExistents1, invalids1;
	
	Contact c1;
	
	Contact c2;
	
	Seller s2;
	
	Documents d1;
	
	
	LoginData loginData;
	
	@Before
	public void init() {
		
		
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
		
		loginData = new LoginData();
		loginData.setUsername(s1.getEmail());
		loginData.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		
		docs = new Documents();
		docs.setGstInImage(new byte[] {});
		docs.setPanImage(new byte[] {});
		docs.setGstInImageType("JPG");
		docs.setPanImageType("JPG");
		
		
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
		
		nonExistents1 = new Seller();
		nonExistents1.setId(1);
		nonExistents1.setName("abc");
		nonExistents1.setEmail("abc@gmail.com");
		nonExistents1.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		nonExistents1.setValid(true);
		nonExistents1.setApplicationStatus("Accepted");
		nonExistents1.setExists(false);
		nonExistents1.setDocuments(d2);
		nonExistents1.setContact(c2);
		
		
		invalids1 = new Seller();
		invalids1.setId(1);
		invalids1.setName("abc");
		invalids1.setEmail("abc@gmail.com");
		invalids1.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		invalids1.setValid(false);
		invalids1.setApplicationStatus("Accepted");
		invalids1.setExists(true);
		invalids1.setDocuments(d2);
		invalids1.setContact(c2);
		
		c2 = new Contact();
		c2.setStreet("wxyz");
		c2.setCity("defg");
		c2.setPhoneNo("4503844234");
		c2.setPincode(876387);
	}
	
	
	
	@Test
	public void testPendingSellerDetails() {
		
		Documents d1 = new Documents();
		d1.setSellerId(1);
		d1.setPanNo("GHQWE4567D");
		d1.setGstInNo("28GHQWE4567D1CS");
		d1.setGstInImageType(".jpeg");
		d1.setPanNo(".jpeg");
		d1.setGstInImage(null);
		d1.setPanImageType(null);
		
		Contact c1 = new Contact();
		c1.setStreet("xyz");
		c1.setCity("def");
		c1.setPhoneNo("4503874234");
		c1.setPincode(876387);
		
		Seller s1 = new Seller();
		s1.setId(1);
		s1.setName("abc");
		s1.setEmail("abc@gmail.com");
		s1.setPassword("xyz");
		s1.setValid(true);
		s1.setApplicationStatus("Accepted");
		s1.setExists(true);
		s1.setDocuments(d1);
		s1.setContact(c1);
		
		Documents d2 = new Documents();
		d1.setSellerId(1);
		d1.setPanNo("GHQWF4567D");
		d1.setGstInNo("28GHQWF4567D1CS");
		d1.setGstInImageType("jpeg");
		d1.setPanNo("jpeg");
		d1.setGstInImage(null);
		d1.setPanImageType(null);
		
		Contact c2 = new Contact();
		c2.setStreet("wxyz");
		c2.setCity("defg");
		c2.setPhoneNo("4503844234");
		c2.setPincode(876387);
		
		Seller s2 = new Seller();
		s1.setId(1);
		s1.setName("abc");
		s1.setEmail("abc@gmail.com");
		s1.setPassword("xyz");
		s1.setValid(true);
		s1.setApplicationStatus("Accepted");
		s1.setExists(true);
		s1.setDocuments(d2);
		s1.setContact(c2);
		
		List<Seller> list = new ArrayList<>();
		
		list.add(s1);
		list.add(s2);
		
		when(sellerService.getPendingSellerDetails()).thenReturn(list);
		
		Assert.assertEquals(sellerService.getPendingSellerDetails(), list);
		//Assert.assertEquals(sellerService.getPendingSellerDetails(), new ArrayList<>());
		}
	
	
	@Test
	public void testGetCategory() {
		when(sellerDAO.getCategory(anyInt())).thenReturn(categoryList);
		Assert.assertEquals(sellerService.getCategory(1), categoryList);
	}
	
	
	@Test
	public void testSavePanImage() {
		when(sellerDAO.savePanImage(anyInt(), any(byte[].class))).thenReturn(true);
		Assert.assertEquals(sellerService.savePanImage(5, new byte[] {}), true);
	}
	
	@Test
	public void testSaveGstinImage() {
		when(sellerDAO.saveGstinImage(anyInt(), any(byte[].class))).thenReturn(true);
		Assert.assertEquals(sellerService.saveGstinImage(5, new byte[] {}), true);
	}
	
	
	@Test
	public void testGetPanImage() {
		when(sellerDAO.getPanImage(anyInt())).thenReturn(docs);
		Assert.assertEquals(sellerService.getPanImage(10), "");
	}
	
	
	@Test
	public void testGetGstinImage() {
		when(sellerDAO.getPanImage(anyInt())).thenReturn(d2);
		Assert.assertEquals(sellerService.getGstinImage(100), "");
	}

	
	@Test
	public void testValidateLogin() {
		when(sellerDAO.getSellerByEmail(any(String.class))).thenReturn(s1);
		Assert.assertEquals(sellerService.validateLogin(loginData), s1);
	}
	
	
	@Test
	public void testValidateLoginNonExistent() {
		when(sellerDAO.getSellerByEmail(any(String.class))).thenReturn(nonExistents1);
		Assert.assertEquals(sellerService.validateLogin(loginData).isExists(), false);
	}
	
	@Test
	public void testValidateLoginInvalid() {
		when(sellerDAO.getSellerByEmail(any(String.class))).thenReturn(s1);
		loginData.setPassword("sklcjxlkj");
		Assert.assertEquals(sellerService.validateLogin(loginData).isValid(), false);
	}
	
	
	@Test
	public void testSaveSeller() {
		
		when(sellerDAO.saveSeller(any())).thenReturn(s1.getId());
		Assert.assertEquals(true, sellerService.saveSeller(s1).isExists());
	}
	
	
	/*
	 * @Test public void testSaveSellerFail() { willThrow(new
	 * Exception()).given(sellerService).saveSeller(s1);
	 * 
	 * Assert.assertEquals(sellerService.saveSeller(s1).isExists(), true); }
	 */

}
