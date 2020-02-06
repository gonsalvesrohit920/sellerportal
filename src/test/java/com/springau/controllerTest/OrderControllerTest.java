package com.springau.controllerTest;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.springau.sellerportal.controller.OrderController;
import com.springau.sellerportal.model.Order;
import com.springau.sellerportal.model.Product;
import com.springau.sellerportal.service.OrderService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	Order order = new Order();
	Product product = new Product();
	Map<Integer, Product> productMap = new HashMap<>();
	
	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderService orderService;
	
	@Test
	public void testPlaceOrder() {
		when(orderService.placeOrder(any(Order.class))).thenReturn(100);
		Assert.assertEquals(orderController.placeOrder(order), 100);
	}
	
	@Test
	public void testGetOrdersOfSeller() {
		when(orderService.getOrdersOfSeller(anyInt())).thenReturn(productMap);
		Assert.assertEquals(orderController.getOrdersOfSeller(100), productMap);
	}
	
	@Test
	public void testSubmitRating() {
		when(orderService.submitRating(anyInt(), anyInt())).thenReturn(100);
		Assert.assertEquals(orderController.submitRating(100, 5), 100);
	}

}
