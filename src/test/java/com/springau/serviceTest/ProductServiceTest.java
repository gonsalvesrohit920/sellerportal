package com.springau.serviceTest;

import static org.mockito.Mockito.when;

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

import com.springau.sellerportal.dao.ProductDAO;
import com.springau.sellerportal.model.CategoryAnswer;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;
import com.springau.sellerportal.service.ProductService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	ProductImage productImageP1 = new ProductImage();
	List<ProductImage> list = new ArrayList<>();
	CategoryAnswer cA = new CategoryAnswer();
	List<CategoryAnswer> caList = new ArrayList<>();
	Map<String, CategoryAnswer> questionAnswers = new HashMap<>();
	List<Product> productList = new ArrayList<>();
	
	@InjectMocks
	ProductService productService;
	
	@Mock
	ProductDAO dao;
	
	@Before
	public void init() {
		System.out.println("Hello");
		productImageP1.setImageId(1);
		productImageP1.setImageType("jpeg");
		productImageP1.setProductId(1);
		productImageP1.setProductImageData(new byte[100]);
		
		list.add(productImageP1);
		
		cA.setCatId(1);
		cA.setCatqId(1);
		cA.setProductId(1);
		cA.setCatAnswer("6 GB");
		
		questionAnswers.put("RAM", cA);
		
		Product p1 = new Product();
		p1.setProductId(1);
		p1.setName("Redmi Mobile");
		p1.setCategory(1);
		p1.setQuantity(10);
		p1.setSellerId(1);
		p1.setPrice(5000);
		p1.setDecription("Very good Mobile");
		p1.setImages(list);
		p1.setAttributes(caList);
		p1.setQuestionAnswers(questionAnswers);
		
		productList.add(p1);
	}
	
	@Test
	public void testGetAllProducts() {
		
		when(productService.getAllProducts()).thenReturn(productList);
		
		System.out.println("Hello");
		
		Assert.assertEquals(productService.getAllProducts(), productList);
		Assert.assertEquals(productService.getAllProducts(), new ArrayList<Product>());
		
	}
	
	@Test
	public void testAddProduct(Product product) {
		productList.add(product);
		Assert.assertEquals("Adding product to the List", 2, productList.size());
		Assert.assertEquals("Adding product to the List", 3, productList.size());
	}
	
	
	@Test
	public void testGetPanImage() {
		
	}
	
	
	@Test
	public void testGetGstInImage() {
		
	}
	
	@Test
	public void testsavePanImage() {
		
	}
	
	@Test
	public void testSaveGstInImage() {
		
	}

}
