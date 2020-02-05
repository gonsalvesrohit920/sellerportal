package com.springau.sellerportal.dao;

import com.springau.sellerportal.model.Order;

public interface OrderDAO {
	
	public int placeOrder(Order order);
	public int submitRating(int o_id, int rating);
}
