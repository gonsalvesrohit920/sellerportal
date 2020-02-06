package com.springau.serviceTest;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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
import com.springau.sellerportal.model.CategoryQuestion;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.model.ProductImage;
import com.springau.sellerportal.service.ProductService;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	ProductImage productImageP1 = new ProductImage();
	List<ProductImage> list = new ArrayList<>();
	CategoryAnswer cA = new CategoryAnswer();
	List<CategoryAnswer> caList = new ArrayList<>();
	Map<String, CategoryAnswer> questionAnswers = new HashMap<>();
	List<Product> productList = new ArrayList<>();
	List<CategoryQuestion> categoryQuestionList = new ArrayList<>();
	Product p1 = new Product();
	List<Integer> integerList = new ArrayList<>();
	
	List<String> imagelist = new ArrayList<String>();
	
	
	
	@InjectMocks
	ProductService productService;
	
	@Mock
	ProductDAO dao;
	
	@Before
	public void init() {
		
		ProductImage image = new ProductImage();
		image.setImageType("png");
		image.setProductImageData(new byte[] {});
		
		
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
		when(dao.getAllProducts()).thenReturn(productList);
		Assert.assertEquals(productService.getAllProducts(), productList);
	}
	
	@Test
	public void testGetAllProductAttributes() {
		when(dao.getAllProductAttributes(anyString())).thenReturn(categoryQuestionList);
		Assert.assertEquals(productService.getAllProductAttributes("Shoes"), categoryQuestionList);
	}
	
	@Test
	public void testGetSellerProductList() {
		when(dao.getSellerProductList(anyInt())).thenReturn(productList);
		Assert.assertEquals(productService.getSellerProductList(56), productList);
	}
	
	@Test
	public void testAddProduct() {
		when(dao.saveProduct(any(Product.class))).thenReturn(integerList);
		Assert.assertEquals(productService.addProduct(p1), integerList);
	}
	
	@Test
	public void testUpdateProductData() {
		when(dao.updateProductData(any(Product.class))).thenReturn(100);
		Assert.assertEquals(productService.updateProductData(p1), 100);
	}
	
	@Test
	public void testDeleteProduct() {
		when(dao.deleteProduct(anyInt())).thenReturn(100);
		Assert.assertEquals(productService.deleteProduct(100), 100);
	}
	
	@Test
	public void testCheckStatus() {
		when(dao.chechStatus(anyInt())).thenReturn("Pending State");
		Assert.assertEquals(productService.checkStatus(100), "Pending State");
	}
	
}
