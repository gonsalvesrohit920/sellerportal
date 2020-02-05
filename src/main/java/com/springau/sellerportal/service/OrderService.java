package com.springau.sellerportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.OrderDAO;
import com.springau.sellerportal.model.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderDAO orderDAO;
	
	public int placeOrder(Order order) {
		return orderDAO.placeOrder(order); 
	}
	
	public int submitRating(int o_id, int rating) {
		return orderDAO.submitRating(o_id, rating);
	}

}
