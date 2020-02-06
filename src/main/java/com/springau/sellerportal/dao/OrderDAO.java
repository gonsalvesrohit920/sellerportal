package com.springau.sellerportal.dao;

import java.util.Map;

import com.springau.sellerportal.model.Order;
import com.springau.sellerportal.model.Product;

public interface OrderDAO {
	
	public int placeOrder(Order order);
	public int submitRating(int orderId, int rating);
	public Map<Integer,Product> getOrdersOfSeller(int sellerId);
}
