package com.springau.sellerportal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springau.sellerportal.dao.OrderDAO;
import com.springau.sellerportal.model.Order;
import com.springau.sellerportal.model.Product;

@Service
public class OrderService {
	
	@Autowired
	OrderDAO orderDAO;
	
	public int placeOrder(Order order) {
		return orderDAO.placeOrder(order); 
	}
	
	public int submitRating(int orderId, int rating) {
		return orderDAO.submitRating(orderId, rating);
	}
	public Map<Integer,Product> getOrdersOfSeller(int sellerId){
		return orderDAO.getOrdersOfSeller(sellerId);
	}
}
