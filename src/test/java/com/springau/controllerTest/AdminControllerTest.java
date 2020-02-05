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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springau.sellerportal.controller.AdminController;
import com.springau.sellerportal.model.Contact;
import com.springau.sellerportal.model.Documents;
import com.springau.sellerportal.model.Seller;
import com.springau.sellerportal.service.AdminService;
import com.springau.sellerportal.service.SellerService;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private AdminController adminController;
	
	@Mock
	private SellerService sellerService;
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
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
		s1.setDocuments(d1);
		s1.setContact(c1);
		
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
		
		Assert.assertEquals(sellerService.getPendingSellerDetails(), s1);
		
	}
	
	

}
