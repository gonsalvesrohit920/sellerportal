package com.springau.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.springau.sellerportal.dao.OrderDAO;
import com.springau.sellerportal.model.Order;
import com.springau.sellerportal.service.OrderService;

import junit.framework.Assert;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
	
	Order order = new Order();
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	OrderDAO orderDao;
	
	@Test
	public void testPlaceOrder() {
		when(orderDao.placeOrder(any(Order.class))).thenReturn(10);
		Assert.assertEquals(orderService.placeOrder(order), 10);
	}
	
	@Test
	public void testSubmitRating() {
		when(orderDao.submitRating(anyInt(), anyInt())).thenReturn(100);
		assertEquals(orderService.submitRating(23, 89), 100);
	}	

}
