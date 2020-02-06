package com.springau.controllerTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springau.sellerportal.controller.AdminController;
import com.springau.sellerportal.model.Contact;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.LoginData;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.AdminService;
import com.springau.sellerportal.service.SellerService;
import com.springau.sellerportal.utility.PasswordHash;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private AdminController adminController;
	
	@Mock
	private SellerService sellerService;
	
	@Mock
	private AdminService adminService;
	
	
	LoginData loginData,loginDataMD5;
	Seller s1;
	Contact c1;
	
	Contact c2;
	
	Seller s2;
	
	Documents d1;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
		
		s1 = new Seller();
		s1.setId(1);
		s1.setName("abc");
		s1.setEmail("abc@gmail.com");
		s1.setPassword("xyz");
		s1.setValid(true);
		s1.setApplicationStatus("Accepted");
		s1.setExists(true);
		
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
		loginData.setPassword("xyz");
		
		
		loginDataMD5 = new LoginData();
		loginDataMD5.setUsername(s1.getEmail());
		loginDataMD5.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		
		s2 = new Seller();
		s2.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
		
	}
	
	@Test
	public void testGetPendingSellerDetails() throws Exception {
		
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
		
		
		Documents d2 = new Documents();
		d1.setSellerId(1);
		d1.setPanNo("GHQWF4567D");
		d1.setGstInNo("28GHQWF4567D1CS");
		d1.setGstInImageType(".jpeg");
		d1.setPanNo(".jpeg");
		d1.setGstInImage(null);
		d1.setPanImageType(null);
		
		Contact c2 = new Contact();
		c1.setStreet("wxyz");
		c1.setCity("defg");
		c1.setPhoneNo("4503844234");
		c1.setPincode(876387);
		
		Seller s2 = new Seller();
		s1.setId(1);
		s1.setName("abc");
		s1.setEmail("abc@gmail.com");
		s2.setPassword(PasswordHash.getMd5Hash(s1.getPassword()));
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
		
	}
	
	
	@Test
	public void testAdminLogin() {
		when(sellerService.validateLogin(loginData)).thenReturn(this.s1);
		Assert.assertEquals(adminController.validateAdmin(loginData).getPassword(),
				PasswordHash.getMd5Hash(s1.getPassword()));
	}
	
	@Test
	public void testAdminLoginSession(){
		when(sellerService.validateLogin(loginDataMD5)).thenReturn(s2);
		
		Assert.assertEquals(PasswordHash.getMd5Hash(s1.getPassword()), loginDataMD5.getPassword());
	}
	
	

}
